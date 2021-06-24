 package com.sunan.security.web;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.sunan.security.jwt.AuthEntryPointJwt;
import com.sunan.security.jwt.AuthTokenFilter;
import com.sunan.security.jwt.MyAccessDeniedHandler;
import com.sunan.security.services.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
		// securedEnabled = true,
		// jsr250Enabled = true,
		prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	UserDetailsServiceImpl userDetailsService;

	@Autowired
	private AuthEntryPointJwt unauthorizedHandler;

	@Autowired
	AuthEntryPointJwt authEntryPointJwt;

	@Autowired
	MyAccessDeniedHandler myAccessDeniedHandler;
	
	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}

	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		List<String> roles = Arrays.asList("ROLE_SUPERADMIN", "ROLE_ADMIN", "ROLE_ACCOUNTANT", "ROLE_MANAGER"
				);
		
		http.cors().and().csrf().disable().exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
				.accessDeniedHandler(myAccessDeniedHandler).and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				// .authorizeRequests().antMatchers("/api/auth/**").permitAll()
				.authorizeRequests().antMatchers("/**").permitAll()
				// .antMatchers("/api/test/**").permitAll()
				// .antMatchers("/api/users/**").hasAnyRole("ADMIN","MODERATOR")
				 .antMatchers("/api/**").hasAnyRole(roles.stream().toArray(String[]::new))
				.anyRequest().authenticated().and().httpBasic().authenticationEntryPoint(authEntryPointJwt);

		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	}

}
