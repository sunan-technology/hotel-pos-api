package com.sunan.recipe;

import java.util.List;

import com.sunan.raw.matrial.RecipeRawMatrialDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecipeDto {

	private int id;
	private String recipeName;
	private int dishId;
	private String dishName;
	private Double fixedCost;
	private String description;
	private int productId;
	private String productName;
	private int quantity;
	private Double costPerUnit;
	private Double totalItemCost;
	private String isActive;
	List<RecipeRawMatrialDto> recipeRawMatrialDtos;

}
