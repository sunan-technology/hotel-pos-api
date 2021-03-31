package com.sunan.supplierPayment;

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
@RequestMapping(RequestMappingConstants.SUPPLIER_PAYMENT)
@Api(value = "Supplier Payment profile", description = "Operations related to supplier payment")
public class SupplierPaymentController {

	private static final Logger logger = LoggerFactory.getLogger(SupplierPaymentController.class);

	@Autowired
	private SupplierPaymentService supplierPaymentService;

	@PostMapping
	public ResponseEntity<?> save(@RequestBody SupplierPaymentDto supplierPaymentDto,@RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Save supplier payment details ");
		return new ResponseEntity<>(supplierPaymentService.save(supplierPaymentDto), HttpStatus.OK);
	}

}
