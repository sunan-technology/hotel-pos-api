package com.sunan.discount;

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
@RequestMapping(RequestMappingConstants.DISCOUNT)
@Api(value = "Discount profile", description = "Operations related to discount")
public class DiscountController {

	private static final Logger logger = LoggerFactory.getLogger(DiscountController.class);

	@Autowired
	private DiscountService discountService;

	@GetMapping()
	public ResponseEntity<?> getAllList(@RequestParam(name = "searchTerm", required = false) String searchTerm,
			@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "id") String sortBy, @RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Fetching list discount details");
		return new ResponseEntity<>(discountService.findActiveList(searchTerm, pageNo, pageSize, sortBy, hotelId),
				HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable int id, @RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Fetching discount details with id {}", id);
		return new ResponseEntity<>(discountService.getById(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> save(@RequestBody DiscountDto discountDto, @RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Save discount details ");
		return new ResponseEntity<>(discountService.save(discountDto, hotelId), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody DiscountDto discountDto, @PathVariable int id,
			@RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Update discount details by id: {}", id);
		return new ResponseEntity<>(discountService.update(discountDto, id, hotelId), HttpStatus.OK);
	}

}
