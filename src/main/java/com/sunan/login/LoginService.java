package com.sunan.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunan.utils.JsonUtils;

@Service
public class LoginService {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginService.class);
	
	@Autowired
	private JsonUtils utils;

	public boolean loginUser(LoginDto loginDto) {
		if(loginDto.userName.equalsIgnoreCase("admin")&& loginDto.password.equalsIgnoreCase("admin")) {
			logger.info("login Successful Welcome"+loginDto.userName);
			return true;
		}
		logger.info("login Failed");
		return false;
	}
	
}
