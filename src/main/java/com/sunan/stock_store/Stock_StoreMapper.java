package com.sunan.stock_store;

import org.springframework.stereotype.Component;

import com.sunan.model.Dish;
import com.sunan.model.Stock_Store;

@Component
public class Stock_StoreMapper {
	
	
	
	public Stock_Store getStock_StoreBuilder(Stock_StoreDto dto) {
		
		return Stock_Store.builder()
				.id(dto.getId())
				.dish(new Dish(dto.getDishId()))
				.quantity(dto.getQuantity())
				.isActive(dto.getIsActive())
				.build();
	}
	
	
	
	public Stock_StoreDto getStock_StoreDtoBuilder(Stock_Store stockStore) {
		
		return Stock_StoreDto.builder()
				.id(stockStore.getId())
				.dishId(stockStore.getDish().getDishId())
				.dishName(stockStore.getDish().getDishName())
				.quantity(stockStore.getQuantity())
				.isActive(stockStore.getIsActive())
				.build();
	}
	
	

}
