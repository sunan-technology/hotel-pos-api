package com.sunan.taxes;

import org.springframework.stereotype.Component;

import com.sunan.model.Taxes;

@Component
public class TaxesMapper {
	
	public Taxes getTaxesBuilder(TaxesDto dto) {
		
		return Taxes.builder()
				.id(dto.getId())
				.taxName(dto.getTaxName())
				.taxPer(dto.getTaxPer())
				.isActive(dto.getIsActive())
				.build();
	}
	
	
	public TaxesDto getTaxesDtoBuilder(Taxes taxes) {
		
		return TaxesDto.builder()
				.id(taxes.getId())
				.taxName(taxes.getTaxName())
				.taxPer(taxes.getTaxPer())
				.isActive(taxes.getIsActive())
				.build();
	}
	

}
