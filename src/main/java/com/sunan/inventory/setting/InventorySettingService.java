package com.sunan.inventory.setting;

import java.io.Serializable;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunan.model.Category;
import com.sunan.model.Hotel;
import com.sunan.model.InventorySetting;
import com.sunan.utils.JsonUtils;

@Service
public class InventorySettingService implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(InventorySettingService.class);

	@Autowired
	private InventorySettingRepository inventorySettingRepository;

	@Autowired
	InventorySettingMapper inventorySettingMapper;

	@Autowired
	private JsonUtils utils;
	
	@Transactional
	public String save(InventorySettingDto  inventorySettingDto,int hotelId) {
		
		InventorySetting inventorySetting=inventorySettingMapper.getInventorySettingBuilder(inventorySettingDto);
		inventorySetting.setHotel(new Hotel(hotelId));
		logger.info("Service : saving inventory setting details");
		inventorySettingRepository.save(inventorySetting);
		return utils.objectMapperSuccess(inventorySettingMapper.getInventorySettingDtoBuilder(inventorySetting), "Inventory setting deatils saved successfully");
		
	}
	
	@Transactional
	public String update(InventorySettingDto  inventorySettingDto,int id,int hotelId) {
		logger.info("Service: Update inventory setting details with id {}", id);
		Optional<InventorySetting> optional = inventorySettingRepository.findById(id);
		if (optional.isPresent()) {
			logger.info("Service: inventory setting details found with id {} for update operation", id);
			InventorySetting inventorySetting=inventorySettingMapper.getInventorySettingBuilder(inventorySettingDto);
			inventorySetting.setHotel(new Hotel(hotelId));
			logger.info("Service : updating inventory setting details");
			inventorySettingRepository.save(inventorySetting);
			return utils.objectMapperSuccess(inventorySettingMapper.getInventorySettingDtoBuilder(inventorySetting), "Inventory setting deatils updated successfully");
		}else {
			logger.info("Service: inventory setting details not found with id {} for update operation", id);
			return utils.objectMapperError("Inventory setting Details Not Found !");
		}
		
	}

}
