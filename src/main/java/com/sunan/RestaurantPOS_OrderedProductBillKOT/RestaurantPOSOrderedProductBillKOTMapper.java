package com.sunan.RestaurantPOS_OrderedProductBillKOT;

import org.springframework.stereotype.Component;

import com.sunan.RestaurantPOS_BillingInfoKOT.RestaurantPOSBillingInfoKOTDto;
import com.sunan.TempRestaurantPOS_OrderInfoKOT.DishKOTDto;
import com.sunan.model.Category;
import com.sunan.model.Dish;
import com.sunan.model.HotelTable;
import com.sunan.model.RestaurantPOSBillingInfoKOT;
import com.sunan.model.RestaurantPOSOrderedProductBillKOT;
import com.sunan.utils.Common;

@Component
public class RestaurantPOSOrderedProductBillKOTMapper {

	public RestaurantPOSOrderedProductBillKOT getRestaurantPOSOrderedProductBillKOTBuilder(
			RestaurantPOSBillingInfoKOTDto dto, DishKOTDto dishDto, int restaurantPOSOrderInfoKOTId, Double amount,
			Category category, Dish dish)

	{
		return RestaurantPOSOrderedProductBillKOT.builder()
				.restaurantPOSBillingInfoKOT(new RestaurantPOSBillingInfoKOT(restaurantPOSOrderInfoKOTId))
				.dish(dishDto.getDish()).rate(dishDto.getRate()).quantity(dishDto.getQuantity()).amount(amount)
				.vatPer(category.getVat()).vatAmount(Common.calculateGST(dishDto.getRate(), category.getVat()))
				.stPer(category.getSt()).stAmount(Common.calculateGST(dishDto.getRate(), category.getSt()))
				.scPer(category.getSc()).scAmount(Common.calculateGST(dishDto.getRate(), category.getSc()))
				.discountPer(dish.getDiscount())
				.discountAmount(Common.calculateGST(dishDto.getRate(), dish.getDiscount()))
				.totalAmount(dto.getTotalAmount()).hotelTable(new HotelTable(dto.getTableNo()))
				.groupName(dto.getGroupName()).ticketNo(dto.getTicketNo()).isActive(dto.getIsActive()).rCost(null)
				.build();

	}
}
