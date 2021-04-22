package com.sunan.inventory.setting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunan.constants.RequestMappingConstants;

import io.swagger.annotations.Api;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(RequestMappingConstants.INVENTORYSETTING)
@Api(value = "Inventory Setting profile", description = "Operations related to inventory setting")
public class InventorySettingController {

	private static final Logger logger = LoggerFactory.getLogger(InventorySettingController.class);

	@Autowired
	private InventorySettingService inventorySettingService;

	@PostMapping
	public ResponseEntity<?> save(@RequestBody InventorySettingDto inventorySettingDto,
			@RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Save inventory setting details ");
		return new ResponseEntity<>(inventorySettingService.save(inventorySettingDto, hotelId), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody InventorySettingDto inventorySettingDto, @PathVariable int id,
			@RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Update inventory setting details by id: {}", id);
		return new ResponseEntity<>(inventorySettingService.update(inventorySettingDto, id, hotelId), HttpStatus.OK);
	}

}
