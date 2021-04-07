package com.sunan.warehouse.type;

import org.springframework.stereotype.Component;

import com.sunan.model.WarehouseType;

@Component
public class WarehouseTypeMapper {
	
	
	public WarehouseType getWarehouseTypeBuilder(WarehouseTypeDto dto) {
		 
		return WarehouseType.builder()
				.id(dto.getId())
				.name(dto.getName())
				.isActive(dto.getIsActive())
				.build();
	}
	
	
	public WarehouseTypeDto getWarehouseTypeDtoBuilder(WarehouseType warehouseType) {
		
		return WarehouseTypeDto.builder()
				.id(warehouseType.getId())
				.name(warehouseType.getName())
				.isActive(warehouseType.getIsActive())
				.build();
	}

}
