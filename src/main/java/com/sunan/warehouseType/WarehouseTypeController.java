package com.sunan.warehouseType;

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
@RequestMapping(RequestMappingConstants.WAREHOUSE_TYPE)
@Api(value = "Warehouse Type profile", description = "Operations related to Warehouse type")
public class WarehouseTypeController {

	private static final Logger logger = LoggerFactory.getLogger(WarehouseTypeController.class);

	@Autowired
	private WarehouseTypeService warehouseTypeService;

	@GetMapping()
	public ResponseEntity<?> getAllList(@RequestParam(name = "searchTerm", required = false) String searchTerm,
			@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "id") String sortBy,@RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Fetching list warehouse type details");
		return new ResponseEntity<>(warehouseTypeService.findActiveList(searchTerm, pageNo, pageSize, sortBy),
				HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable int id,@RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Fetching Warehouse type details with id {}", id);
		return new ResponseEntity<>(warehouseTypeService.getById(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> save(@RequestBody WarehouseTypeDto warehouseTypeDto,@RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Save Warehouse type details ");
		return new ResponseEntity<>(warehouseTypeService.save(warehouseTypeDto), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody WarehouseTypeDto warehouseTypeDto, @PathVariable int id) {
		logger.info("Controller: Update Warehouse type details by id: {}", id);
		return new ResponseEntity<>(warehouseTypeService.update(warehouseTypeDto, id), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) {
		logger.info("Controller: Delete warehouse type details by id: {}", id);
		return new ResponseEntity<>(warehouseTypeService.delete(id), HttpStatus.OK);
	}

}
