package com.sunan.order.type;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sunan.constants.RequestMappingConstants;
import com.sunan.discount.DiscountDto;

import io.swagger.annotations.Api;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(RequestMappingConstants.ORDERTYPE)
@Api(value = "OrderType profile", description = "Operations related to order type")
public class OrderTypeController {
	
	private static final Logger logger = LoggerFactory.getLogger(OrderTypeController.class);
	
	@Autowired
	private OrderTypeService orderTypeService;
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody OrderTypeDto orderTypeDto , @RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Save order type details ");
		return new ResponseEntity<>(orderTypeService.save(orderTypeDto, hotelId), HttpStatus.OK);
	}


	@GetMapping()
	public ResponseEntity<?> getAllList(@RequestParam(name = "searchTerm", required = false) String searchTerm,
			@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "id") String sortBy,@RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Fetching list order type details");
		return new ResponseEntity<>(orderTypeService.findActiveList(searchTerm, pageNo, pageSize, sortBy,hotelId),
				HttpStatus.OK);
	}
}
