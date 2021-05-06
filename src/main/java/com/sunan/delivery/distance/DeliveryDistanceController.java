package com.sunan.delivery.distance;

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
import com.sunan.roles.RolesController;
import com.sunan.roles.RolesDto;

import io.swagger.annotations.Api;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(RequestMappingConstants.DELIVERYDISTANCE)
@Api(value = "Delivery Distance profile", description = "Operations related to delivery distance")
public class DeliveryDistanceController {
	
	private static final Logger logger = LoggerFactory.getLogger(DeliveryDistanceController.class);
	
	
	@Autowired
	private DeliveryDistanceService deliveryDistanceService;
	
	
	@GetMapping()
	public ResponseEntity<?> getAllList(@RequestParam(name = "searchTerm", required = false) String searchTerm,
			@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "id") String sortBy, @RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Fetching list delivery distance details");
		return new ResponseEntity<>(deliveryDistanceService.findActiveList(searchTerm, pageNo, pageSize, sortBy, hotelId),
				HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable int id, @RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Fetching delivery distance details with id {}", id);
		return new ResponseEntity<>(deliveryDistanceService.getById(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> save(@RequestBody DeliveryDistanceDto deliveryDistanceDto, @RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Save delivery distance details ");
		return new ResponseEntity<>(deliveryDistanceService.save(deliveryDistanceDto, hotelId), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody DeliveryDistanceDto deliveryDistanceDto, @PathVariable int id,
			@RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Update delivery distance details by id: {}", id);
		return new ResponseEntity<>(deliveryDistanceService.update(deliveryDistanceDto, id, hotelId), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable int id, @RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Delete delivery distance details by id: {}", id);
		return new ResponseEntity<>(deliveryDistanceService.delete(id), HttpStatus.OK);
	}
	

}
