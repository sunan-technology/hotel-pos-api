package com.sunan.variation;

import org.springframework.stereotype.Component;

import com.sunan.model.Hotel;
import com.sunan.model.Variation;

@Component
public class VariationMapper {
	
	public Variation getVariationBuilder(VariationDto dto,int hotelId) {
		
		return Variation.builder()
				.id(dto.getId())
				.name(dto.getName())
				.department(dto.getDepartment())
				.status(dto.getStatus())
				.hotel(new Hotel(hotelId))
				.build();
	}
	
	public VariationDto getVariationDtoBuilder(Variation variation) {
		return VariationDto.builder()
				.id(variation.getId())
				.name(variation.getName())
				.department(variation.getDepartment())
				.status(variation.getStatus())
				.createdDate(variation.createdAt)
				.build();
	}

}
