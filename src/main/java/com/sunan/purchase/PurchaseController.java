package com.sunan.purchase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunan.constants.RequestMappingConstants;

import io.swagger.annotations.Api;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(RequestMappingConstants.PURCHASE)
@Api(value = "Purchase profile", description = "Operations related to purchase")
public class PurchaseController {

	private static final Logger logger = LoggerFactory.getLogger(PurchaseController.class);

	@Autowired
	private PurchaseService purchaseService;

	@PostMapping
	public ResponseEntity<?> save(@RequestBody PurchaseDto purchaseDto,@RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Save purchase details ");
		return new ResponseEntity<>(purchaseService.save(purchaseDto,hotelId), HttpStatus.OK);
	}

}
