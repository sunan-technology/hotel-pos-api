package com.sunan.kitchen;

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
@RequestMapping(RequestMappingConstants.KITCHEN)
@Api(value = "Kitchen profile", description = "Operations related to Kitchen")
public class KitchenController {
	
	private static final Logger logger = LoggerFactory.getLogger(KitchenController.class);
	
	@Autowired
	private KitchenService kitchenService;
	
	
	
	@GetMapping()
	public ResponseEntity<?> getAllList(@RequestParam(name = "searchTerm", required = false) String searchTerm,
			@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "id") String sortBy) {
		logger.info("Controller: Fetching list kitchen details");
		return new ResponseEntity<>(kitchenService.findActiveList(searchTerm, pageNo, pageSize, sortBy),
				HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable int id) {
		logger.info("Controller: Fetching kitchen details with id {}", id);
		return new ResponseEntity<>(kitchenService.getById(id), HttpStatus.OK);
	}
	
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody KitchenDto kitchenDto) {
		logger.info("Controller: Save kitchen details ");
		return new ResponseEntity<>(kitchenService.save(kitchenDto), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody KitchenDto kitchenDto, @PathVariable int id) {
		logger.info("Controller: Update kitchen details by id: {}", id);
		return new ResponseEntity<>(kitchenService.update(kitchenDto, id), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) {
		logger.info("Controller: Delete kitchen details by id: {}", id);
		return new ResponseEntity<>(kitchenService.delete(id), HttpStatus.OK);
	}
	


}
