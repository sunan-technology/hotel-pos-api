package com.sunan.stock_store;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockStoreDto {

	private int id;

	private int quantity;

	private int dishId;

	private String dishName;
	
	private String isActive;

}
