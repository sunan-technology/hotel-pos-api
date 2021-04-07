package com.sunan.billing.kot.info;

import javax.validation.Valid;

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
@RequestMapping(RequestMappingConstants.RESTAURANTPOSBILLINGINFOKOT)
@Api(value = "Recipe profile", description = "Operations related to recipe")
public class BillingInfoKOTController {
	
	private static final Logger logger = LoggerFactory.getLogger(BillingInfoKOTController.class);
	
	@Autowired
	private BillingInfoKOTService billingInfoKOTService;
	
	
	@PostMapping
	public ResponseEntity<?> save(@Valid @RequestBody BillingInfoKOTDto billingInfoKOTDto,
			@RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Save restaurant order bill details ");
		return new ResponseEntity<>(billingInfoKOTService.save(billingInfoKOTDto,hotelId),
				HttpStatus.OK);
	}

}
