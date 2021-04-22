package com.sunan.order.type;

import java.io.Serializable;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunan.model.Hotel;
import com.sunan.model.OrderType;
import com.sunan.utils.JsonUtils;

@Service
public class OrderTypeService implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(OrderTypeService.class);
	
	@Autowired
	private OrderTypeRepository orderTypeRepository;
	
	@Autowired
	OrderTypeMapper orderTypeMapper;
	
	@Autowired
	private JsonUtils utils;
	
	
	@Transactional
	public String save(OrderTypeDto orderTypeDto ,int hotelId) {
		
		OrderType orderType=orderTypeMapper.getOrderTypeBuilder(orderTypeDto);
		orderType.setHotel(new Hotel(hotelId));
		logger.info("Service : saving order type details");
		orderTypeRepository.save(orderType);
		return utils.objectMapperError(orderTypeMapper.getOrderTypeDtoBuilder(orderType), "Order Type saved Successfully");
		
	}
}
