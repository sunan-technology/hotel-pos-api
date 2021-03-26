package com.sunan.TempRestaurantPOS_OrderInfoKOT;

import java.io.Serializable;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunan.model.TempRestaurantPOSOrderInfoKOT;
import com.sunan.utils.JsonUtils;

@Service
public class TempRestaurantPOSOrderInfoKOTService implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(TempRestaurantPOSOrderInfoKOTService.class);
	
	@Autowired
	private TempRestaurantPOSOrderInfoKOTRepository tempRestaurantPOSOrderInfoKOTRepository;
	
	@Autowired
	TempRestaurantPOSOrderedInfoKOTMapper tempRestaurantPOSOrderedInfoKOTMapper;
	
	@Autowired
	private JsonUtils utils;
	
	@Transactional
	public String save(TempRestaurantPOSOrderInfoKOTDto tempRestaurantPOSOrderInfoKOTDto) {
		
		TempRestaurantPOSOrderInfoKOT tempRestaurantPOSOrderInfoKOT = tempRestaurantPOSOrderedInfoKOTMapper.tempRestaurantPOSOrderInfoKOTBuilder(tempRestaurantPOSOrderInfoKOTDto);
		 tempRestaurantPOSOrderInfoKOTRepository.save(tempRestaurantPOSOrderInfoKOT);
		 
		return null;
	}
	
	

}
