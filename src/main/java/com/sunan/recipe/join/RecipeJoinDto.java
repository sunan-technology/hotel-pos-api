package com.sunan.recipe.join;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecipeJoinDto {

	private int recipeId;
	private int productId;
	private int quantity;
	private Double costPerUnit;
	private Double totalItemCost;
	private String isActive;

}
