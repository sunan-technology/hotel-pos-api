 package com.sunan.taxes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
 @Builder
 @AllArgsConstructor
 @NoArgsConstructor
public class TaxesDto {

	private int id;
	private String taxName;
	private Double taxPer;
	private String isActive;
}
