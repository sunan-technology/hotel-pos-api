package com.sunan.purchaseorder;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseOrderDto {

	private int id;
	private int poNumber;
	private Date date;
	private String taxType;
	private int supplierId;
	private int productId;
	private String productName;
	private int quantity;
	private Double pricePerUnit;
	private Double totalAmount;
	private String terms;
	private Double subTotal;
	private Double grandTotal;
	private Double vatPer;
	private Double addVat;
	private Double vatAmount;
	private Double addVatAmount;
	private String isActive;

}
