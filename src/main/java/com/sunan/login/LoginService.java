package com.sunan.login;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sunan.utils.JsonUtils;

@Service
public class LoginService {

	private static final Logger logger = LoggerFactory.getLogger(LoginService.class);

	String jwt = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzdXBlcmFkbWluQGdtYWlsLmNvbSIsIlNlc3Npb25JRCI6MSwiaWF0IjoxNjE3NzE2OTc1LCJleHAiOjE2MTc4MDMzNzV9.yHvDTia_NNAo1EgCmCaDA6fh3Ghy36-8Jr7hAaVW7dXgq08s1JHxQTRH724UTUxS5RMTgRtxutxVRlCWhitJQg";
	Long userId = 1L;
	String userName = "Admin";
	String email = "admin@gmail.com";
	List<String> roles = Arrays.asList("Admin, Accountant");
	List<HotelInfo> hotelInfos = Arrays.asList(new HotelInfo(1, "Hotel 1"), new HotelInfo(2, "Hotel 2"));
	@Autowired
	private JsonUtils utils;

	public ResponseEntity<?> loginUser(LoginDto loginDto) {
//		if(true) {
//			 logger.warn("Returning HTTP 400 Bad Request");
//			throw new BadRequestException("User Not found");
//		}
		if (loginDto.userName.equalsIgnoreCase("admin") && loginDto.password.equalsIgnoreCase("admin")) {
			logger.info("login Successful Welcome {}", loginDto.userName);
			return ResponseEntity.ok(new JwtResponse(jwt, userId, userName, email, roles, hotelInfos));
		}
		logger.info("login Failed");
		return new ResponseEntity<String>(utils.objectMapperError("Login Failed!"), HttpStatus.UNAUTHORIZED);
	}

}
