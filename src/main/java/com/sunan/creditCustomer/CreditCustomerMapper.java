package com.sunan.creditCustomer;

import org.springframework.stereotype.Component;

import com.sunan.model.CreditCustomer;

@Component
public class CreditCustomerMapper {
	
	public CreditCustomer getCreditCustomerBuilder(CreditCustomerDto dto) {
		
		return CreditCustomer.builder()
				.creditCustomerId(dto.getCreditCustomerId())
				.name(dto.getName())
				.contactNo(dto.getContactNo())
				.address(dto.getAddress())
				.registerationDate(dto.getRegisterationDate())
				.isActive(dto.getIsActive())
				.build();
	}
	
	public CreditCustomerDto getCreditCustomerDtoBuilder(CreditCustomer creditCustomer) {
		
		return CreditCustomerDto.builder()
				.creditCustomerId(creditCustomer.getCreditCustomerId())
				.name(creditCustomer.getName())
				.contactNo(creditCustomer.getContactNo())
				.address(creditCustomer.getAddress())
				.registerationDate(creditCustomer.getRegisterationDate())
				.isActive(creditCustomer.getIsActive())
				.build();
	}

}
