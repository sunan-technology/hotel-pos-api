package com.sunan.unit;

import org.springframework.stereotype.Component;

import com.sunan.model.Units;

@Component
public class UnitsMapper {
	
	public Units getUnitBuilder(UnitsDto dto) {
		
		return Units.builder()
				.id(dto.getId())
				.name(dto.getName())
				.isActive(dto.getIsActive())
				.build();
	}

	
	public UnitsDto getUnitDtoBuilder(Units units) {
		
		return UnitsDto.builder()
				.id(units.getId())
				.name(units.getName())
				.createdDate(units.getCreatedAt())
				.build();
	}
}
