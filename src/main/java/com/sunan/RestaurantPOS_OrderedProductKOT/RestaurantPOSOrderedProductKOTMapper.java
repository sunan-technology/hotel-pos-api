package com.sunan.RestaurantPOS_OrderedProductKOT;

import org.springframework.stereotype.Component;

import com.sunan.TempRestaurantPOS_OrderInfoKOT.DishKOTDto;
import com.sunan.TempRestaurantPOS_OrderInfoKOT.TempRestaurantPOSOrderInfoKOTDto;
import com.sunan.model.RestaurantPOSOrderInfoKOT;
import com.sunan.model.RestaurantPOSOrderedProductKOT;
import com.sunan.utils.Common;

@Component
public class RestaurantPOSOrderedProductKOTMapper {
	
	public RestaurantPOSOrderedProductKOT getRestaurantPOSOrderedProductKOTBuilder(TempRestaurantPOSOrderInfoKOTDto dto,DishKOTDto dish,int restaurantPOSOrderInfoKOTId,Double amount,Double vatPer,Double vatAmount,Double stPer,Double stAmount,Double scPer,Double scAmount,Double discount,Double discountAmount) {
		
		return RestaurantPOSOrderedProductKOT.builder()
				.RestaurantPOSOrderInfoKOT(new RestaurantPOSOrderInfoKOT(restaurantPOSOrderInfoKOTId))
				.dish(dish.getDish())
				.rate(dish.getRate())
				.quantity(dish.getQuantity())
				.amount(amount)
				.vatPer(vatPer)
				.vatAmount(vatAmount)
				.stPer(stPer)
				.stAmount(stAmount)
				.scPer(scPer)
				.scAmount(scAmount)
				.discountPer(discount)
				.discountAmount(discountAmount)
				.totalAmount(dto.getTotalAmount())
				.notes("")
				.itemStatus(Common.itemStatus)
				.isActive(dto.getIsActive())
				.build();
	}

}
