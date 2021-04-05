package com.sunan.RestaurantPOS_OrderedProductKOT;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantPOSOrderedProductKOTDto {

	private int id;

	private int restaurantPOSOrderInfoKOTId;

	private String dish;

	private Double rate;

	private int quantity;

	private Double amount;

	private Double vatPer;

	private Double vatAmount;

	private Double stPer;

	private Double stAmount;

	private Double scPer;

	private Double scAmount;

	private Double discountPer;

	private Double discountAmount;

	private Double totalAmount;

	private String tableNumber;

	private String itemStatus;

	private String isActive;

}
