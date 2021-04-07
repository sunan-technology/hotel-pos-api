package com.sunan.employee.registration;

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
@RequestMapping(RequestMappingConstants.EMPLOYEE_REGISTRATION)
@Api(value = "Employee Registration profile", description = "Operations related to employee registration")
public class EmployeeRegistrationController {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeRegistrationController.class);

	@Autowired
	private EmployeeRegistrationService employeeRegistrationService;

	@GetMapping()
	public ResponseEntity<?> getAllList(@RequestParam(name = "searchTerm", required = false) String searchTerm,
			@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "id") String sortBy,@RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Fetching list employee details");
		return new ResponseEntity<>(employeeRegistrationService.findActiveList(searchTerm, pageNo, pageSize, sortBy),
				HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable int id,@RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Fetching employee details with id {}", id);
		return new ResponseEntity<>(employeeRegistrationService.getById(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> save(@RequestBody EmployeeRegistrationDto employeeRegistrationDto,@RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Save employee details ");
		return new ResponseEntity<>(employeeRegistrationService.save(employeeRegistrationDto,hotelId), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody EmployeeRegistrationDto employeeRegistrationDto,
			@PathVariable int id,@RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Update employee details by id: {}", id);
		return new ResponseEntity<>(employeeRegistrationService.update(employeeRegistrationDto, id,hotelId), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable int id,@RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Delete employee details by id: {}", id);
		return new ResponseEntity<>(employeeRegistrationService.delete(id), HttpStatus.OK);
	}

}
