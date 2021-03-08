package com.sunan.stock_store;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Stock_StoreDto {

	private int id;

	private int quantity;

	private int dishId;

	private String dishName;
	
	private String isActive;

}
