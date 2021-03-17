package com.sunan.customer;

import org.springframework.stereotype.Component;

import com.sunan.model.Customer;

@Component
public class CustomerMapper {
	
	
	public Customer getCustomerBuilder(CustomerDto dto) {
		
		return Customer.builder()
				.id(dto.getId())
				.name(dto.getName())
				.address(dto.getAddress())
				.contactNo(dto.getContactNo())
				.email(dto.getEmail())
				.birthDate(dto.getBirthDate())
				.anniversaryDate(dto.getAnniversaryDate())
				.registrationDate(dto.getRegistrationDate())
				.isActive(dto.getIsActive())
				.build();
	}
	
	
	public CustomerDto getCustomerDtoBuilder(Customer customer) {
		
		return CustomerDto.builder()
				.id(customer.getId())
				.name(customer.getName())
				.address(customer.getAddress())
				.contactNo(customer.getContactNo())
				.email(customer.getEmail())
				.birthDate(customer.getBirthDate())
				.anniversaryDate(customer.getAnniversaryDate())
				.registrationDate(customer.getRegistrationDate())
				.isActive(customer.getIsActive())
				.build();
	}

}
