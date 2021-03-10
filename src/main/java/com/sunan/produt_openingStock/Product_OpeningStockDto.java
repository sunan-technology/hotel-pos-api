package com.sunan.produt_openingStock;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product_OpeningStockDto {

	private int id;
	private int productId;
	private String productName;
	private int storageTypeId;
	private String storageTypeName;
	private int warehousesId;
	private String warehousesName;
	private int quantity;
	private int hasExpriyDate;
	private Date expiryDate;
	private String isActive;

}
