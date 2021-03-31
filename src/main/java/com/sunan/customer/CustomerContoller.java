package com.sunan.customer;

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
@RequestMapping(RequestMappingConstants.CUSTOMER)
@Api(value = "Customer profile", description = "Operations related to customer")
public class CustomerContoller {

	private static final Logger logger = LoggerFactory.getLogger(CustomerContoller.class);

	@Autowired
	private CustomerService customerService;

	@GetMapping()
	public ResponseEntity<?> getAllList(@RequestParam(name = "searchTerm", required = false) String searchTerm,
			@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "id") String sortBy,@RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Fetching list customer details");
		return new ResponseEntity<>(customerService.findActiveList(searchTerm, pageNo, pageSize, sortBy),
				HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable int id,@RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Fetching customer details with id {}", id);
		return new ResponseEntity<>(customerService.getById(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> save(@RequestBody CustomerDto customerDto,@RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Save customer details ");
		return new ResponseEntity<>(customerService.save(customerDto), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody CustomerDto customerDto, @PathVariable int id,@RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Update customer details by id: {}", id);
		return new ResponseEntity<>(customerService.update(customerDto, id), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable int id,@RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Delete customer details by id: {}", id);
		return new ResponseEntity<>(customerService.delete(id), HttpStatus.OK);
	}

}
