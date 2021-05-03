package com.sunan.internal.transfer;

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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sunan.constants.RequestMappingConstants;

import io.swagger.annotations.Api;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(RequestMappingConstants.INTERNALTRANSFER)
@Api(value = "Internal Transfer/sale  profile", description = "Operations related to internal transfer")
public class InternalTranferController {
	
	private static final Logger logger = LoggerFactory.getLogger(InternalTranferController.class);
	
	
	@Autowired
	private InternalTransferService internalTransferService;
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody InternalTransferDto internalTransferDto,@RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Save internal transfer details ");
		return new ResponseEntity<>(internalTransferService.save(internalTransferDto,hotelId), HttpStatus.OK);
	}
	
	
//	@GetMapping("/{id}")
//	public ResponseEntity<?> getById(@PathVariable int id,@RequestHeader("hotelId") int hotelId) {
//		logger.info("Controller: Fetching internal transfer details with id {}", id);
//		return new ResponseEntity<>(internalTransferService.getById(id), HttpStatus.OK);
//	}
	

	@GetMapping()
	public ResponseEntity<?> getAllList(@RequestParam(name = "searchTerm", required = false) String searchTerm,
			@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "id") String sortBy,@RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Fetching list internal transfer details");
		return new ResponseEntity<>(internalTransferService.findActiveList(searchTerm, pageNo, pageSize, sortBy,hotelId), HttpStatus.OK);
	}

}
