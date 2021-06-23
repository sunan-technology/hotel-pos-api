package com.sunan.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;





public class AuthTokenFilter extends OncePerRequestFilter {
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String jwt= parseJwt(request);
		
		if(request.getServletPath().toLowerCase().toString().contains("/api/")) {
			
			if (jwt == null) {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST,
						"Error: Pass the authorization token value in header");
				return;
			}
			if (!jwtUtils.validateJwtToken(jwt)) { 
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
						"Error: Pass the unauthorization token value in header");
				return;
			}
			
			String hotelId=request.getHeader("hotelId");
			if (hotelId == null) {
				response.sendError(HttpServletResponse.SC_FORBIDDEN, "Error: Pass the session value in header");
				return;
			}
			
			try {
				String username= jwtUtils.getUsernameFromJwtToken(jwt);
				UserDetails userDetails=userDetailsServiceImpl.loadUserByUsername(username);
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,
						null, userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authentication);
				}catch (Exception e) {
					logger.error("Cannot set user authentication: {}", e);
				}
			
		}
		
		filterChain.doFilter(request, response);
		
	}
	
	
	public String parseJwt(HttpServletRequest request) {
		String headerAuth=request.getHeader("Authorization");
		
		if(StringUtils.hasText(headerAuth) && headerAuth.startsWith("Barrer") ) {
			return headerAuth.substring(7, headerAuth.length());
		}
		return null;
	}

}
