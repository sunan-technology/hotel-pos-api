package com.sunan.order.kot.temp.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TempOrderedProductKOTDto {

	private int id;

	private int tempRestaurantPOSOrderInfoKOTId;

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

	private int tableNo;

	private String itemStatus;

	private String isActive;

}
