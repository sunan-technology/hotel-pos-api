package com.sunan.biling.kot.product;

import org.springframework.stereotype.Component;

import com.sunan.model.Category;
import com.sunan.model.Dish;
import com.sunan.model.HotelTable;
import com.sunan.billing.kot.info.BillingInfoKOTDto;
import com.sunan.model.BillingInfoKOT;
import com.sunan.model.OrderedProductBillKOT;
import com.sunan.order.kot.temp.info.DishKOTDto;
import com.sunan.utils.Common;

@Component
public class OrderedProductBillKOTMapper {

	public OrderedProductBillKOT getOrderedProductBillKOTBuilder(
			BillingInfoKOTDto dto, DishKOTDto dishDto, int restaurantPOSBillingInfoKOTId, Double amount,
			Category category, Dish dish)

	{
		return OrderedProductBillKOT.builder()
				.billingInfoKOT(new BillingInfoKOT(restaurantPOSBillingInfoKOTId))
				.dish(dishDto.getDish())
				.rate(dishDto.getRate())
				.quantity(dishDto.getQuantity())
				.amount(amount)
				.vatPer(category.getVat())
				.vatAmount(Common.calculateGST(dishDto.getRate(), category.getVat()))
				.stPer(category.getSt())
				.stAmount(Common.calculateGST(dishDto.getRate(), category.getSt()))
				.scPer(category.getSc())
				.scAmount(Common.calculateGST(dishDto.getRate(), category.getSc()))
				.discountPer(dish.getDiscount())
				.discountAmount(Common.calculateGST(dishDto.getRate(), dish.getDiscount()))
				.totalAmount(dto.getTotalAmount())
				.hotelTable(new HotelTable(dto.getTableNo()))
				.groupName(dto.getGroupName())
				.ticketNo(dto.getTicketNo())
				.isActive(dto.getIsActive())
				.rCost(null)
				.build();

	}
}
