package com.sunan.sub.order.type;

import org.springframework.stereotype.Component;

import com.sunan.model.SubOrderType;

@Component
public class SubOrderTypeMapper {
	
	public SubOrderType getSubOrderTypeBuilder(SubOrderTypeDto dto) {
		return SubOrderType.builder()
				.id(dto.getId())
				.name(dto.getName())
				.status(dto.getStatus())
				.isActive(dto.getIsActive())
		             .build();
		
	}
	
	
	public SubOrderTypeDto getSubOrderTypeDtoBuilder(SubOrderType subOrderType) {
		
		return SubOrderTypeDto.builder()
				.id(subOrderType.getId())
				.name(subOrderType.getName())
				.status(subOrderType.getStatus())
				.isActive(subOrderType.getIsActive())
				.build();
	}

}
