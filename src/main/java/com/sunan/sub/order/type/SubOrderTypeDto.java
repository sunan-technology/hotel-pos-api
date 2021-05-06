package com.sunan.sub.order.type;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubOrderTypeDto {

	private int id;

	private String name;

	private String status;
	
	private String isActive;
}
