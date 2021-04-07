package com.sunan.biling.kot.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderedProductBillKOTDto {

	private int id;

	private int restaurantPOSBillingInfoKOTId;

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

	private String tableNo;

	private String groupName;

	private String ticketNo;

	private Double rCost;

}
