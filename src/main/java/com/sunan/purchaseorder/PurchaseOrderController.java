package com.sunan.purchaseorder;

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
@RequestMapping(RequestMappingConstants.PURCHASE_ORDER)
@Api(value = "Purchase order profile", description = "Operations related to purchase order")
public class PurchaseOrderController {

	private static final Logger logger = LoggerFactory.getLogger(PurchaseOrderController.class);

	@Autowired
	private PurchaseOrderService purchaseOrderService;

	@PostMapping
	public ResponseEntity<?> save(@RequestBody PurchaseOrderDto purchaseOrderDto,@RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Save purchase order details ");
		return new ResponseEntity<>(purchaseOrderService.save(purchaseOrderDto,hotelId), HttpStatus.OK);
	}

}
