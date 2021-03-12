package com.sunan.creditCustomerPayment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunan.constants.RequestMappingConstants;

import io.swagger.annotations.Api;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(RequestMappingConstants.CREDITCUSTOMERPAYMENT)
@Api(value = "Credit Customer Payment profile", description = "Operations related to credit customer payment")
public class CreditCustomerPaymentController {

	private static final Logger logger = LoggerFactory.getLogger(CreditCustomerPaymentController.class);

	@Autowired
	private CreditCustomerPaymentService creditCustomerPaymentService;

	@PostMapping
	public ResponseEntity<?> save(@RequestBody CreditCustomerPaymentDto creditCustomerPaymentDto) {
		logger.info("Controller: Save credit customer payment details ");
		return new ResponseEntity<>(creditCustomerPaymentService.save(creditCustomerPaymentDto), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getCreditCustomerBalance(@PathVariable int id){
		
		logger.info("Controller: Fetching credit customer balance details with id {}", id);
		return new ResponseEntity<>(creditCustomerPaymentService.getCreditCustomerBalanceByCustomerId(id),HttpStatus.OK);
		
	}
	
   

}
