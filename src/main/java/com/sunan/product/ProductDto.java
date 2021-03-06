package com.sunan.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

	private int id;

	private String productCode;

	private String productName;

	private int categoryId;

	private String categoryName;

	private String description;

	private String unit;

	private Double price;

	private Double reorderPoint;

	private String isActive;

}
