package com.sunan.table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HotelTableDto {
	
	private int id;
	private String  tableNo;
	private String status;  //active, deactive
	private String tableType; //Ac, nonAc
	private String floorNo;
	private String isActive;

}
