package com.sunan.purchaseJoin;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseJoinDto {
	
	private int id;
	private int perchaseId;
	private int productId;
	private String productName;
	private int quantity;
	private Double totalAmount;
	private Double price;
	private int storageTypeId;
	private String storageTypeName;
	private int warehousesId;
	private String warehousesName;
	private int hasExpiryDate;
	private Date expiryDate;
	private String isActive;
	
	
	

}
