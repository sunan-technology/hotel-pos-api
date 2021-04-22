package com.sunan.order.type;

import org.springframework.stereotype.Component;

import com.sunan.model.OrderType;
@Component
public class OrderTypeMapper {
	
	public OrderType getOrderTypeBuilder(OrderTypeDto dto) {
		
		return OrderType.builder()
				.id(dto.getId())
				.orderType(dto.getOrderType())
				.isActive(dto.getIsActive())
				.build();
	}
	
	public OrderTypeDto getOrderTypeDtoBuilder(OrderType orderType) {
		
		return OrderTypeDto.builder()
				.id(orderType.getId())
				.orderType(orderType.getOrderType())
				.isActive(orderType.getIsActive())
				.build();
	}

}
