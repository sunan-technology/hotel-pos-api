package com.sunan.login;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.sunan.constants.RequestMappingConstants;
import io.swagger.annotations.Api;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(RequestMappingConstants.LOGIN)
@Api(value = "Login User profile", description = "Operations related to Login User Profile")
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	LoginService loginService;
	
	@PostMapping
	public ResponseEntity<?> loginUser(@RequestBody LoginDto loginDto) {
		logger.info("Request for login user data");
		if(loginService.loginUser(loginDto)) {
			return new ResponseEntity<>("Login successful ", HttpStatus.OK);
		}else {
			return new ResponseEntity<>("Login Failed", HttpStatus.UNAUTHORIZED);
		}
		
	}
}
