package com.sunan.storageType;

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
@RequestMapping(RequestMappingConstants.STORAGETYPE)
@Api(value = "StorageType profile", description = "Operations related to storage type")
public class StorageTypeController {

	private static final Logger logger = LoggerFactory.getLogger(StorageTypeController.class);

	@Autowired
	private StorageTypeService storageTypeService;

	@GetMapping()
	public ResponseEntity<?> getAllList(@RequestParam(name = "searchTerm", required = false) String searchTerm,
			@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "id") String sortBy,@RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Fetching list storage type details");
		return new ResponseEntity<>(storageTypeService.findActiveList(searchTerm, pageNo, pageSize, sortBy),
				HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable int id,@RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Fetching storage type details with id {}", id);
		return new ResponseEntity<>(storageTypeService.getById(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> save(@RequestBody StorageTypeDto storageTypeDto,@RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Save storage type details ");
		return new ResponseEntity<>(storageTypeService.save(storageTypeDto), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody StorageTypeDto storageTypeDto, @PathVariable int id,@RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Update storage type details by id: {}", id);
		return new ResponseEntity<>(storageTypeService.update(storageTypeDto, id), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable int id,@RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Delete storage type details by id: {}", id);
		return new ResponseEntity<>(storageTypeService.delete(id), HttpStatus.OK);
	}

}
