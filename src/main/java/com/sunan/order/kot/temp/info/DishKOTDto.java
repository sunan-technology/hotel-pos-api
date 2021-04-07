package com.sunan.order.kot.temp.info;

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
public class DishKOTDto {
	
	@NotNull(message = "dish id is not present")
	@Min(1)
	private int dishId;
	private String dish;
	private Double rate;
	private int quantity;
	@NotNull(message = "category id is not present")
	@Min(1)
	private int categoryId;

}
