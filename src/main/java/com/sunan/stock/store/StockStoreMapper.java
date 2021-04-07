package com.sunan.stock.store;

import org.springframework.stereotype.Component;

import com.sunan.model.Dish;
import com.sunan.model.StockStore;

@Component
public class StockStoreMapper {
	
	
	
	public StockStore getStock_StoreBuilder(StockStoreDto dto) {
		
		return StockStore.builder()
				.id(dto.getId())
				.dish(new Dish(dto.getDishId()))
				.quantity(dto.getQuantity())
				.isActive(dto.getIsActive())
				.build();
	}
	
	
	
	public StockStoreDto getStock_StoreDtoBuilder(StockStore stockStore) {
		
		return StockStoreDto.builder()
				.id(stockStore.getId())
				.dishId(stockStore.getDish().getDishId())
				.dishName(stockStore.getDish().getDishName())
				.quantity(stockStore.getQuantity())
				.isActive(stockStore.getIsActive())
				.build();
	}
	
	

}
