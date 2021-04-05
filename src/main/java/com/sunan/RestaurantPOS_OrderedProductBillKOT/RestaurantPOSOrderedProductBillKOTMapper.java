package com.sunan.RestaurantPOS_OrderedProductBillKOT;

import org.springframework.stereotype.Component;

import com.sunan.RestaurantPOS_BillingInfoKOT.RestaurantPOSBillingInfoKOTDto;
import com.sunan.TempRestaurantPOS_OrderInfoKOT.DishKOTDto;
import com.sunan.model.HotelTable;
import com.sunan.model.RestaurantPOSBillingInfoKOT;
import com.sunan.model.RestaurantPOSOrderedProductBillKOT;

@Component
public class RestaurantPOSOrderedProductBillKOTMapper {
	
	public RestaurantPOSOrderedProductBillKOT getRestaurantPOSOrderedProductBillKOTBuilder(RestaurantPOSBillingInfoKOTDto dto,DishKOTDto dish,int restaurantPOSOrderInfoKOTId,Double amount,Double vatPer,Double vatAmount,Double stPer,Double stAmount,Double scPer,Double scAmount,Double discount,Double discountAmount)

	{
	return RestaurantPOSOrderedProductBillKOT.builder()
			.restaurantPOSBillingInfoKOT(new RestaurantPOSBillingInfoKOT(restaurantPOSOrderInfoKOTId))
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
			.hotelTable(new HotelTable(dto.getTableNo()))
			.groupName(dto.getGroupName())
			.ticketNo(dto.getTicketNo())
			.isActive(dto.getIsActive())
			.rCost(null)
			.build();
	
	}
}
