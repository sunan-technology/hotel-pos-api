package com.sunan.purchaseorder.join;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseOrderJoinDto {

	private int id;

	private int purchaseOrderId;

	private int productId;

	private String productName;

	private int quantity;

	private Double pricePerUnit;

	private Double amount;

	private String isActive;

}
