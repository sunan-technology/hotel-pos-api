package com.sunan.order.kot.temp.info;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@RequestMapping(RequestMappingConstants.TEMPRESTAURANTPOSORDEREDINFOKOT)
@Api(value = "Temp Restaurant Order info  profile", description = "Operations related to Temp Restaurant Order")
public class TempOrderedInfoKOTController {

	private static final Logger logger = LoggerFactory.getLogger(TempOrderedInfoKOTController.class);

	@Autowired
	private TempOrderInfoKOTService tempOrderInfoKOTService;

	@PostMapping
	public ResponseEntity<?> save(@Valid @RequestBody TempOrderInfoKOTDto tempOrderInfoKOTDto,
			@RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Save temp restaurant order details ");
		return new ResponseEntity<>(tempOrderInfoKOTService.save(tempOrderInfoKOTDto, hotelId), HttpStatus.OK);
	}

	@PostMapping("/update-product-quantity")
	public ResponseEntity<?> updateProductQuantity(@RequestParam int dishId, @RequestParam int quantity,
			@RequestParam int ticketId, @RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: update product quantity details ");

		return new ResponseEntity<>(tempOrderInfoKOTService.updateProductQuantity(quantity, ticketId, hotelId, dishId),
				HttpStatus.OK);
	}

	@PostMapping("/change-table")
	public ResponseEntity<?> changeTable(@RequestParam int ticketId, @RequestParam int tableId,
			@RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: change table  ");

		return new ResponseEntity<>(tempOrderInfoKOTService.changeTable(tableId, ticketId, hotelId), HttpStatus.OK);

	}
	
	@PostMapping("/add-dish-quantity")
	public ResponseEntity<?> addDishQuantity(@RequestParam int ticketId,@RequestParam int dishId,@RequestParam int quantity,@RequestHeader("hotelId") int hotelId){
		logger.info("Controller : add dish quantity");
		return new ResponseEntity<>(tempOrderInfoKOTService.addDishQuantity(dishId, ticketId,quantity, hotelId), HttpStatus.OK);
		
	}
	
	@PostMapping("/remove-dish-quantity")
	public ResponseEntity<?> removeDishQuantity(@RequestParam int ticketId,@RequestParam int dishId, @RequestParam int quantity,@RequestHeader("hotelId") int hotelId){
		logger.info("Controller : remove dish quantity");
		return new ResponseEntity<>(tempOrderInfoKOTService.removeDishQuantity(dishId, ticketId, hotelId,quantity), HttpStatus.OK);
		
	}
	
	@PutMapping("/remove-product")
	public ResponseEntity<?> removeProduct(@RequestParam int ticketId,@RequestParam int dishId,@RequestHeader("hotelId") int hotelId){
		logger.info("Controller : remove product");
		return new ResponseEntity<>(tempOrderInfoKOTService.removeProduct(dishId, ticketId, hotelId), HttpStatus.OK);
		
	}
	
	@PutMapping("/remove-temp-order")
	public ResponseEntity<?> removeTempOrder(@RequestParam int ticketId){
		logger.info("Controller : remove temp order");
		return new ResponseEntity<>(tempOrderInfoKOTService.removeTempOrder(ticketId), HttpStatus.OK);
		
	}
}
