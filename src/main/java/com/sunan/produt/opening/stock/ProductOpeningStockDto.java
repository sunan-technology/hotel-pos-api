package com.sunan.produt.opening.stock;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductOpeningStockDto {

	private int id;
	private int productId;
	private String productName;
	@NotNull
	@Min(1)
	private int storageTypeId;
	private String storageTypeName;
	@NotNull
	@Min(1)
	private int warehousesId;
	private String warehousesName;
	private int quantity;
	private int hasExpriyDate;
	private Date expiryDate;
	private String isActive;

}
