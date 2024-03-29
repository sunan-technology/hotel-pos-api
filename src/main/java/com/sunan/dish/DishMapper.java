package com.sunan.dish;

import org.springframework.stereotype.Component;

import com.sunan.model.Category;
import com.sunan.model.Dish;
import com.sunan.model.Kitchen;
import com.sunan.model.Variation;
@Component
public class DishMapper {
	
	
	public Dish getDishBuilder(DishDto dto)
	{
		return Dish.builder()
				.dishId(dto.getDishId())
				.dishName(dto.getDishName())
				.rate(dto.getRate())
				.nonAcRate(dto.getNonAcRate())
				.hdRate(dto.getHdRate())
				.taRate(dto.getTaRate())
				.status(dto.getStatus())
				.ebRate(dto.getEbRate())
				.zRate(dto.getZRate())
				.uRate(dto.getURate())
				.expressRate(dto.getExpressRate())
				.discount(dto.getDiscount())
				.barcode(dto.getBarcode())
				.isActive(dto.getIsActive())
				.variation(new Variation(dto.getVariationId()))
				.category(new Category(dto.getCategoryId()))
				.kitchen(new Kitchen(dto.getKitchenId()))
				.build();
	}
	
	
	public DishDto getDishDtoBuilder(Dish dish)
	{
		return DishDto.builder()
				.dishId(dish.getDishId())
				.dishName(dish.getDishName())
				.rate(dish.getRate())
				.nonAcRate(dish.getNonAcRate())
				.hdRate(dish.getHdRate())
				.taRate(dish.getTaRate())
				.ebRate(dish.getEbRate())
				.status(dish.getStatus())
				.zRate(dish.getZRate())
				.uRate(dish.getURate())
				.expressRate(dish.getExpressRate())
				.discount(dish.getDiscount())
				.barcode(dish.getBarcode())
				.isActive(dish.getIsActive())
				.variationId(dish.getVariation().getId())
				.variationName(dish.getVariation().getName())
				.categoryId(dish.getCategory().getId())
				.categoryName(dish.getCategory().getCategoryName())
				.kitchenId(dish.getKitchen().getId())
				.kitchenName(dish.getKitchen().getKitchenName())
				.build();
	}

}
