package com.sunan.warehouse.type;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseTypeDto {

	private int id;
	private String name;
	private String isActive;

}
