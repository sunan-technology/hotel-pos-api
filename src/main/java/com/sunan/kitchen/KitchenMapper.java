package com.sunan.kitchen;

import org.springframework.stereotype.Component;

import com.sunan.model.Kitchen;

@Component
public class KitchenMapper {
	
	
	public Kitchen getKitchenBuilder(KitchenDto dto) {
		return Kitchen.builder()
				.id(dto.getId())
				.kitchenName(dto.getKitchenName())
				.printer(dto.getPrinter())
				.isActive(dto.getIsActive())
				.build();
	}
	
	public KitchenDto getKitchenDtoBuilder(Kitchen kitchen) {
		
		return KitchenDto.builder()
				.id(kitchen.getId())
				.kitchenName(kitchen.getKitchenName())
				.printer(kitchen.getPrinter())
				.isActive(kitchen.getIsActive())
				.build();
	}

}
