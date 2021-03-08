package com.sunan.warehouse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WarehousesDto {

	private int id;
	private String warehouseName;
	private String address;
	private String city;
	private String isActive;
	private int warehouseTypeId;
	private String warehouseTypeName;

}
