package com.sunan.dish;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DishDto {
	
	private int dishId;
	private String dishName;
	private double rate;
	private double nonAcRate;
	private double hdRate;
	private double taRate;
	private double ebRate;
	private double zRate;
	private double uRate;
	private double expressRate;
	private double discount;
	private String barcode;
	private String isActive;
	
	private int categoryId;
	private String categoryName;
	
	private int kitchenId;
	private String kitchenName;

}
