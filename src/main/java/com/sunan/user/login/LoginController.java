package com.sunan.user.login;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunan.constants.RequestMappingConstants;
import com.sunan.security.jwt.JwtUtils;
import com.sunan.security.response.JwtResponse;
import com.sunan.security.services.UserDetailsImpl;

import io.swagger.annotations.Api;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(RequestMappingConstants.LOGIN)
@Api(value = "User login profile", description = "Operations related to login")
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	private static final String className = "Login";
	
	
	@Autowired
	AuthenticationManager authenticationManager;
	

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;
	
	@PostMapping
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest){
		
		Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt=jwtUtils.genrateJwtToken(authentication);
		
		UserDetailsImpl userDetails=(UserDetailsImpl) authentication.getPrincipal();
		List<String> roles=userDetails.getAuthorities().stream()
				            .map(item -> item.getAuthority())
				            .collect(Collectors.toList());
		
		return ResponseEntity.ok(new JwtResponse(jwt,userDetails.getId(),userDetails.getUsername(),userDetails.getEmail(),roles));
		
	}
	

}
