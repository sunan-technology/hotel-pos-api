package com.sunan.purchase;

import org.aspectj.weaver.reflect.DeferredResolvedPointcutDefinition;
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

	
	@GetMapping()
	public ResponseEntity<?> getAllList(@RequestParam(name = "searchTerm", required = false) String searchTerm,
			@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "id") String sortBy,@RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Fetching list purchase details");
		return new ResponseEntity<>(purchaseService.findActiveList(searchTerm, pageNo, pageSize, sortBy,hotelId), HttpStatus.OK);
	}
	
	@GetMapping("/get-all-purchase-join")
	public ResponseEntity<?> getAllPurchaseJoinList(@RequestParam(name = "searchTerm", required = false) String searchTerm,
			@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "id") String sortBy,@RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Fetching list purchase join details");
		return new ResponseEntity<>(purchaseService.findAllPurchaseJoinList(searchTerm, pageNo, pageSize, sortBy,hotelId), HttpStatus.OK);
	}
	
	@GetMapping("/get-available-rawmatrial")
	public ResponseEntity<?> getAllAvailableRawMatrialList(@RequestParam(defaultValue = "warehouseId") int warehouseId,@RequestHeader("hotelId") int hotelId){
		logger.info("Controller: Fetching list available raw matrial details");
		return new ResponseEntity<>(purchaseService.getAllAvailablePurchaseRawMatrial( warehouseId,hotelId), HttpStatus.OK);
	}
	
	@GetMapping("/get-total-quantity-by-rawmatrial")
	public ResponseEntity<?> getTotalQuantityByRawMatrial(@RequestHeader("hotelId") int hotelId,@RequestParam(defaultValue = "warehouseId") int warehouseId){
		logger.info("Controller: Fetching list available raw matrial details");
		return new ResponseEntity<>(purchaseService.getTotalQuantityByRawMatrial(hotelId,warehouseId), HttpStatus.OK);
	}
	
	@GetMapping("/get-rawmatrial-list-by-rawmatrial-id")
	public ResponseEntity<?> getRawMatrialList(@RequestParam(defaultValue = "0", required = false) int rawmatrialId,@RequestHeader("hotelId") int hotelId){
		logger.info("Controller: Fetching list available raw matrial details");
		return new ResponseEntity<>(purchaseService.getRawMatrialList(rawmatrialId,hotelId), HttpStatus.OK);
	}
}
