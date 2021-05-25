package com.sunan.order.type;

import java.io.Serializable;
import java.util.List;
import java.util.function.Function;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sunan.discount.DiscountDto;
import com.sunan.discount.DiscountOrderTypeDto;
import com.sunan.model.Discount;
import com.sunan.model.DiscountOrderType;
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
	
	@Transactional
	public String findActiveList(String searchTerm, Integer pageNo, Integer pageSize, String sortBy, int hotelId) {
		logger.info("Service: Fetching list of order type details ");
		PageRequest pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<OrderType> pagedResult = null;

		pagedResult = orderTypeRepository.findByIsActiveAndHotel("yes", pageable, new Hotel(hotelId));

		Page<OrderTypeDto> page = pagedResult.map(new Function<OrderType, OrderTypeDto>() {
			@Override
			public OrderTypeDto apply(OrderType entity) {
				
				OrderTypeDto dto = orderTypeMapper.getOrderTypeDtoBuilder(entity);
				return dto;
			}
		});
		logger.info("Service: Fetching list of order type details, total records: {}", page.getTotalElements());
		return utils.objectMapperSuccess(page, "All Acive order type list.");
	}
}
