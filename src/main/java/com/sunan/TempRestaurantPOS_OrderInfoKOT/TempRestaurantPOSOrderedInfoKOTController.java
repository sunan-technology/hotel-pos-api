package com.sunan.TempRestaurantPOS_OrderInfoKOT;

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
@RequestMapping(RequestMappingConstants.TEMPRESTAURANTPOSORDEREDINFOKOT)
@Api(value = "Temp Restaurant Order info  profile", description = "Operations related to Temp Restaurant Order")
public class TempRestaurantPOSOrderedInfoKOTController {

	private static final Logger logger = LoggerFactory.getLogger(TempRestaurantPOSOrderedInfoKOTController.class);

	@Autowired
	private TempRestaurantPOSOrderInfoKOTService tempRestaurantPOSOrderInfoKOTService;

	@PostMapping
	public ResponseEntity<?> save(@RequestBody TempRestaurantPOSOrderInfoKOTDto tempRestaurantPOSOrderInfoKOTDto,
			@RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Save temp restaurant order details ");
		return new ResponseEntity<>(tempRestaurantPOSOrderInfoKOTService.save(tempRestaurantPOSOrderInfoKOTDto),
				HttpStatus.OK);
	}
}
