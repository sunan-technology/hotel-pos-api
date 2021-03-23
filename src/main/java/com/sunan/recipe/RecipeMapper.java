package com.sunan.recipe;

import org.springframework.stereotype.Component;

import com.sunan.model.Dish;
import com.sunan.model.Product;
import com.sunan.model.Recipe;
import com.sunan.model.RecipeJoin;

@Component
public class RecipeMapper {
	
	public Recipe getRecipeBuilder(RecipeDto dto) {
		
		return Recipe.builder()
				.id(dto.getId())
				.recipeName(dto.getRecipeName())
				.dish(new Dish(dto.getDishId()))
				.fixedCost(dto.getFixedCost())
				.discription(dto.getDescription())
				.isActive(dto.getIsActive())
				.build();
	}
	
	
	public RecipeJoin getRecipeJoinBuilder(RecipeDto dto) {
		
		return RecipeJoin.builder()
				.product(new Product(dto.getProductId()))
				.quantity(dto.getQuantity())
				.costPerUnit(dto.getCostPerUnit())
				.totalItemCost(dto.getTotalItemCost())
				.isActive(dto.getIsActive())
				.recipe(new Recipe(dto.getId()))
				.build();
	}
	
		public RecipeJoin getRecipeJoinBuilder(RecipeJoin recipeJoin, RecipeDto dto) {
				
		
			recipeJoin.setProduct(new Product(dto.getProductId()));
			recipeJoin.setQuantity(dto.getQuantity());
			recipeJoin.setCostPerUnit(dto.getCostPerUnit());
			recipeJoin.setTotalItemCost(dto.getTotalItemCost());
			recipeJoin.setIsActive(dto.getIsActive());
			recipeJoin.setRecipe(new Recipe(dto.getId()));
			return recipeJoin;
			}
			
	
	public RecipeDto getRecipeDtoBuilder(Recipe recipe,RecipeJoin recipeJoin) {
		
		return RecipeDto.builder()
				.id(recipe.getId())
				.recipeName(recipe.getRecipeName())
				.dishId(recipe.getDish().getDishId())
				.dishName(recipe.getDish().getDishName())
				.fixedCost(recipe.getFixedCost())
				.description(recipe.getDiscription())
				.isActive(recipe.getIsActive())
				.productId(recipeJoin.getProduct().getId())
				.productName(recipeJoin.getProduct().getProductName())
				.quantity(recipeJoin.getQuantity())
				.costPerUnit(recipeJoin.getCostPerUnit())
				.totalItemCost(recipeJoin.getTotalItemCost())
				.build();
	}
	
	
	public RecipeDto getRecipeJoinDtoBuilder(RecipeJoin recipeJoin) {
		
		return RecipeDto.builder()
				
				.id(recipeJoin.getRecipe().getId())
				.recipeName(recipeJoin.getRecipe().getRecipeName())
				.dishId(recipeJoin.getRecipe().getDish().getDishId())
				.dishName(recipeJoin.getRecipe().getDish().getDishName())
				.fixedCost(recipeJoin.getRecipe().getFixedCost())
				.description(recipeJoin.getRecipe().getDiscription())
				.productId(recipeJoin.getProduct().getId())
				.productName(recipeJoin.getProduct().getProductName())
				.costPerUnit(recipeJoin.getCostPerUnit())
				.totalItemCost(recipeJoin.getTotalItemCost())
				.quantity(recipeJoin.getQuantity())
				.isActive(recipeJoin.getIsActive())
				.build();
	}

}
