package com.sunan.warehouse;

import org.springframework.stereotype.Component;

import com.sunan.model.WarehouseType;
import com.sunan.model.Warehouses;

@Component
public class WarehousesMapper {
	
	
	public Warehouses getWarehousesBuilder(WarehousesDto dto) {
		
		return Warehouses.builder()
				.id(dto.getId())
				.warehouseName(dto.getWarehouseName())
				.address(dto.getAddress())
				.city(dto.getCity())
				.warehouseType(new WarehouseType(dto.getId()))
				.isActive(dto.getIsActive())
				.build();
	}
	
	
	public WarehousesDto getWarehousesDtoBuilder(Warehouses warehouses) {
		
		return WarehousesDto.builder()
				.id(warehouses.getId())
				.warehouseName(warehouses.getWarehouseName())
				.address(warehouses.getAddress())
				.city(warehouses.getCity())
				.warehouseTypeId(warehouses.getWarehouseType().getId())
				.warehouseTypeName(warehouses.getWarehouseType().getName())
				.isActive(warehouses.getIsActive())
				.build();
	}
	
	

}
