package com.sunan.waiter;

import org.springframework.stereotype.Component;

import com.sunan.model.Waiter;

@Component
public class WaiterMapper {
	
	public Waiter getWaiterBuilder(WaiterDto dto) {
		
		return Waiter.builder()
				.id(dto.getId())
				.name(dto.getName())
				.mobileNo(dto.getMobileNo())
				.email(dto.getEmail())
				.adharCard(dto.getAdharCard())
				.address(dto.getAddress())
				.city(dto.getCity())
				.state(dto.getState())
				.isActive(dto.getIsActive())
				.build();
	}
	
	
	public WaiterDto getWaiterDtoBuilder(Waiter waiter) {
		
		return WaiterDto.builder()
				.id(waiter.getId())
				.name(waiter.getName())
				.mobileNo(waiter.getMobileNo())
				.email(waiter.getEmail())
				.adharCard(waiter.getAdharCard())
				.address(waiter.getAddress())
				.city(waiter.getCity())
				.state(waiter.getState())
				.build();
	}

}
