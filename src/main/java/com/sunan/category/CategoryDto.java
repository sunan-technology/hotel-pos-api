package com.sunan.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

	private int id;
	private String categoryName;
	private double vat;
	private double sc;
	private double st;
	private String isActive;

}
