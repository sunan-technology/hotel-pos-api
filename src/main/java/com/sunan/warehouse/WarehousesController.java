package com.sunan.warehouse;

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
@RequestMapping(RequestMappingConstants.WAREHOUSES)
@Api(value = "Warehouses profile", description = "Operations related to warehouses")
public class WarehousesController {

	private static final Logger logger = LoggerFactory.getLogger(WarehousesController.class);

	@Autowired
	private WarehousesService warehousesService;

	@GetMapping()
	public ResponseEntity<?> getAllList(@RequestParam(name = "searchTerm", required = false) String searchTerm,
			@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "id") String sortBy) {
		logger.info("Controller: Fetching list warehouses details");
		return new ResponseEntity<>(warehousesService.findActiveList(searchTerm, pageNo, pageSize, sortBy),
				HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable int id) {
		logger.info("Controller: Fetching warehouse details with id {}", id);
		return new ResponseEntity<>(warehousesService.getById(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> save(@RequestBody WarehousesDto warehousesDto) {
		logger.info("Controller: Save warehouse details ");
		return new ResponseEntity<>(warehousesService.save(warehousesDto), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody WarehousesDto warehousesDto, @PathVariable int id) {
		logger.info("Controller: Update warehouse details by id: {}", id);
		return new ResponseEntity<>(warehousesService.update(warehousesDto, id), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) {
		logger.info("Controller: Delete warehouse details by id: {}", id);
		return new ResponseEntity<>(warehousesService.delete(id), HttpStatus.OK);
	}

}
