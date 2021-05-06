package com.sunan.delivery.distance;

import org.springframework.stereotype.Component;

import com.sunan.model.DeliveryDistance;

@Component
public class DeliveryDistanceMapper {
	
	
	public DeliveryDistance getDeliveryDistanceBuilder(DeliveryDistanceDto dto) {
		
		return DeliveryDistance.builder()
				.id(dto.getId())
				.km(dto.getKm())
				.price(dto.getPrice())
				.isActive(dto.getIsActive())
				.build();
	}
	
	
	public DeliveryDistanceDto getDeliveryDistanceDtoBuilder(DeliveryDistance DeliveryDistance) {
		
		return DeliveryDistanceDto.builder()
				.id(DeliveryDistance.getId())
				.km(DeliveryDistance.getKm())
				.price(DeliveryDistance.getPrice())
				.isActive(DeliveryDistance.getIsActive())
				.build();
	}

}
