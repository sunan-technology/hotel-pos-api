package com.sunan.pos.report;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductBillReportDto {
	
	private String categoryName;
	private String dishName;
	private int quantity;
	private Double rate;
	private Double amount;
	

}
