package com.sunan.kitchen;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KitchenDto {

	private int id;
	private String kitchenName;
	private String printer;
	private String isActive;
	
}
