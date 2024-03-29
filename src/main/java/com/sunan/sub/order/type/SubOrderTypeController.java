package com.sunan.sub.order.type;

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
@RequestMapping(RequestMappingConstants.SUBORDERTYPE)
@Api(value = "Sub Order type profile", description = "Operations related to sub order type")
public class SubOrderTypeController {
	private static final Logger logger = LoggerFactory.getLogger(SubOrderTypeController.class);

	@Autowired
	private SubOrderTypeService subOrderTypeService;

	@GetMapping()
	public ResponseEntity<?> getAllList(@RequestParam(name = "searchTerm", required = false) String searchTerm,
			@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "id") String sortBy, @RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Fetching list sub order type details");
		return new ResponseEntity<>(subOrderTypeService.findActiveList(searchTerm, pageNo, pageSize, sortBy, hotelId),
				HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable int id, @RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Fetching sub order type details with id {}", id);
		return new ResponseEntity<>(subOrderTypeService.getById(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> save(@RequestBody SubOrderTypeDto subOrderTypeDto, @RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Save sub order type details ");
		return new ResponseEntity<>(subOrderTypeService.save(subOrderTypeDto, hotelId), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody SubOrderTypeDto subOrderTypeDto, @PathVariable int id,
			@RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Update sub order type details by id: {}", id);
		return new ResponseEntity<>(subOrderTypeService.update(subOrderTypeDto, id, hotelId), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable int id, @RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Delete sub order type details by id: {}", id);
		return new ResponseEntity<>(subOrderTypeService.delete(id), HttpStatus.OK);
	}

}
