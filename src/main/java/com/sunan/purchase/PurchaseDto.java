package com.sunan.purchase;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseDto {
	
	private int id;
	private String invoiceNo;
	private Date invoiceDate;
	private String purchasetype;
	private String remarks;
	private int supplierId;
	
	private int productId;
	private String productName;
	private int storageTypeId;
	private String storageTypeName;
	private int warehousesId;
	private String warehousesName;
	private int quantity;
	private Double pricePerUnit;
	private Double totalAmount;
	private int hasExpiryDate;
	private Date expriyDate;
	
	private Double subTotal;
	private Double discount;
	private Double freightCharges;
	private Double otherCharges;
	private Double previousDue;
	private Double total;
	private Double roundOff;
	private Double grandTotal;
	private Double totalPayment;
		private String isActive;

}
