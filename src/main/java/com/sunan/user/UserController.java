package com.sunan.user;

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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sunan.constants.RequestMappingConstants;

import io.swagger.annotations.Api;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(RequestMappingConstants.USER)
@Api(value = "User profile", description = "Operations related to user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@GetMapping()
	public ResponseEntity<?> getAllList(@RequestParam(name = "searchTerm", required = false) String searchTerm,
			@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "id") String sortBy,@RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Fetching list user details");
		return new ResponseEntity<>(userService.findActiveList(searchTerm, pageNo, pageSize, sortBy),
				HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable int id,@RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Fetching user details with id {}", id);
		return new ResponseEntity<>(userService.getById(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> save(@RequestBody UserDto userDto,@RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Save user details ");
		return new ResponseEntity<>(userService.save(userDto,hotelId), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody UserDto userDto,
			@PathVariable int id,@RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Update user details by id: {}", id);
		return new ResponseEntity<>(userService.update(userDto, id,hotelId), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable int id,@RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Delete user details by id: {}", id);
		return new ResponseEntity<>(userService.delete(id), HttpStatus.OK);
	}
	
	@GetMapping("/check-employee-isexists")
	public ResponseEntity<?> checkEmployeeExists(@PathVariable int id,@RequestHeader("hotelId") int hotelId){
		logger.info("Controller: checking user details");
		
		return new ResponseEntity<>(userService.checkEmployeeExist(id, hotelId),
				HttpStatus.OK);
	}

}
