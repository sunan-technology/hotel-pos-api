package com.sunan.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginService.class);

	public boolean loginUser(LoginDto loginDto) {
		if(loginDto.userName.equalsIgnoreCase("admin")&& loginDto.password.equalsIgnoreCase("admin")) {
			logger.info("login Successful");
			return true;
		}
		logger.info("login Failed");
		return false;
	}
	
}
