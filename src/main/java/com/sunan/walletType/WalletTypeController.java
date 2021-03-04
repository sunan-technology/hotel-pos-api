package com.sunan.walletType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sunan.category.CategoryDto;
import com.sunan.constants.RequestMappingConstants;

import io.swagger.annotations.Api;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(RequestMappingConstants.WALLET_TYPE)
@Api(value = "WalletType profile", description = "Operations related to Wallet type")
public class WalletTypeController {
	
	private static final Logger logger = LoggerFactory.getLogger(WalletTypeController.class);
	
	@Autowired
	private WalletTypeService walletTypeService;
	
	
	@GetMapping()
	public ResponseEntity<?> getAllList(@RequestParam(name = "searchTerm", required = false) String searchTerm,
			@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "id") String sortBy) {
		logger.info("Controller: Fetching list kitchen details");
		return new ResponseEntity<>(walletTypeService.findActiveList(searchTerm, pageNo, pageSize, sortBy),
				HttpStatus.OK);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable int id) {
		logger.info("Controller: Fetching Wallet type details with id {}", id);
		return new ResponseEntity<>(walletTypeService.getById(id), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody WalletTypeDto walletTypeDto) {
		logger.info("Controller: Save Wallet type details ");
		return new ResponseEntity<>(walletTypeService.save(walletTypeDto), HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody WalletTypeDto walletTypeDto, @PathVariable int id) {
		logger.info("Controller: Update Wallet type details by id: {}", id);
		return new ResponseEntity<>(walletTypeService.update(walletTypeDto, id), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) {
		logger.info("Controller: Delete Wallet type details by id: {}", id);
		return new ResponseEntity<>(walletTypeService.delete(id), HttpStatus.OK);
	}

}
