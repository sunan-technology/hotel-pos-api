package com.sunan.discount;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DiscountOrderTypeDto {
	private int id;

	private String discountOrderType;

	private int discountId;

	private String discountTitle;

	private String isActive;

}
