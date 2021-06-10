package com.sunan.taxes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@RequestMapping(RequestMappingConstants.TAXES)
@Api(value = "Taxes profile", description = "Operations related to tax")
public class TaxesController {
	private static final Logger logger = LoggerFactory.getLogger(TaxesController.class);
	
	@Autowired
	private TaxesService taxesService;
	
	@GetMapping()
	public ResponseEntity<?> getAllList(@RequestParam(name = "searchTerm", required = false) String searchTerm,
			@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "id") String sortBy) {
		logger.info("Controller: Fetching list tax details");
		return new ResponseEntity<>(taxesService.findActiveList(searchTerm, pageNo, pageSize, sortBy),
				HttpStatus.OK);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable int id) {
		logger.info("Controller: Fetching tax details with id {}", id);
		return new ResponseEntity<>(taxesService.getById(id), HttpStatus.OK);
	}
	
	
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody TaxesDto taxesDto,@RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Save tax details ");
		return new ResponseEntity<>(taxesService.save(taxesDto,hotelId), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody TaxesDto taxesDto, @PathVariable int id,@RequestHeader("hotelId") int hotelId
			) {
		logger.info("Controller: Update tax details by id: {}", id);
		return new ResponseEntity<>(taxesService.update(taxesDto, id,hotelId), HttpStatus.OK);
	}

}
