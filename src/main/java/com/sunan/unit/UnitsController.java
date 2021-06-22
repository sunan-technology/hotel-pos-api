package com.sunan.unit;

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
@RequestMapping(RequestMappingConstants.UNITS)
@Api(value = "Units profile", description = "Operations related to units")
public class UnitsController {

	private static final Logger logger = LoggerFactory.getLogger(UnitsController.class);

	@Autowired
	private UnitsService unitsService;

	@GetMapping()
	public ResponseEntity<?> getAllList(@RequestParam(name = "searchTerm", required = false) String searchTerm,
			@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "id") String sortBy) {
		logger.info("Controller: Fetching list Units details");
		return new ResponseEntity<>(unitsService.findActiveList(searchTerm, pageNo, pageSize, sortBy),
				HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable int id) {
		logger.info("Controller: Fetching unit details with id {}", id);
		return new ResponseEntity<>(unitsService.getById(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> save(@RequestBody UnitsDto unitsDto) {
		logger.info("Controller: Save unit details ");
		return new ResponseEntity<>(unitsService.save(unitsDto), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody UnitsDto unitsDto, @PathVariable int id
			) {
		logger.info("Controller: Update unit details by id: {}", id);
		return new ResponseEntity<>(unitsService.update(unitsDto, id), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable int id,@RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Delete unit details by id: {}", id);
		return new ResponseEntity<>(unitsService.delete(id), HttpStatus.OK);
	}

}
