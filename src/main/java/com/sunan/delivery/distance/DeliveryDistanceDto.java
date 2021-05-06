package com.sunan.delivery.distance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryDistanceDto {

	private int id;

	private int km;

	private Double price;

	private String isActive;

}
