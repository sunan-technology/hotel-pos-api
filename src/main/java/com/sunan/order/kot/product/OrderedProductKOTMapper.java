package com.sunan.order.kot.product;

import org.springframework.stereotype.Component;

import com.sunan.model.Category;
import com.sunan.model.Dish;
import com.sunan.model.Hotel;
import com.sunan.model.OrderInfoKOT;
import com.sunan.model.OrderedProductKOT;
import com.sunan.model.TempOrderedProductKOT;
import com.sunan.order.kot.temp.info.DishKOTDto;
import com.sunan.order.kot.temp.info.TempOrderInfoKOTDto;
import com.sunan.utils.Common;

@Component
public class OrderedProductKOTMapper {
	
//	public OrderedProductKOT getRestaurantPOSOrderedProductKOTBuilder(TempOrderInfoKOTDto dto,DishKOTDto dish,int restaurantPOSOrderInfoKOTId,Double amount,Double vatPer,Double vatAmount,Double stPer,Double stAmount,Double scPer,Double scAmount,Double discount,Double discountAmount) {
//		
//		return OrderedProductKOT.builder()
//				.OrderInfoKOT(new OrderInfoKOT(restaurantPOSOrderInfoKOTId))
//				.dish(dish.getDish())
//				.rate(dish.getRate())
//				.quantity(dish.getQuantity())
//				.amount(amount)
//				.vatPer(vatPer)
//				.vatAmount(vatAmount)
//				.stPer(stPer)
//				.stAmount(stAmount)
//				.scPer(scPer)
//				.scAmount(scAmount)
//				.discountPer(discount)
//				.discountAmount(discountAmount)
//				.totalAmount(dto.getTotalAmount())
//				.notes("")
//				.itemStatus(Common.itemStatus)
//				.isActive(dto.getIsActive())
//				.build();
//	}

	public OrderedProductKOT getRestaurantPOSOrderedProductKOTBuilder(TempOrderInfoKOTDto dto,DishKOTDto dish,int tempOrderInfoKOTId,Double amount,Category category,Dish dishes) {
		
		Double totalAmount=amount+ Common.calculateGST(dish.getRate(), category.getSc())+Common.calculateGST(dish.getRate(), category.getSt())+Common.calculateGST(dish.getRate(), category.getVat());
		
		return OrderedProductKOT.builder()
				.orderInfoKOT(new OrderInfoKOT(tempOrderInfoKOTId))
				.dish(dish.getDish())
				.rate(dish.getRate())
				.quantity(dish.getQuantity())
				.amount(amount)
				.vatPer(category.getVat())
				.vatAmount(Common.calculateGST(dish.getRate(), category.getVat()))
				.stPer(category.getSt())
				.stAmount(Common.calculateGST(dish.getRate(), category.getSt()))
				.scPer(category.getSc())
				.scAmount(Common.calculateGST(dish.getRate(), category.getSc()))
				.discountPer(dishes.getDiscount())
				.discountAmount(Common.calculateGST(dish.getRate(), dishes.getDiscount()))
				.totalAmount(totalAmount)
				.notes("")
				.itemStatus(Common.itemStatus)
				.isActive("yes")
				.build();
	}
	
	
	public OrderedProductKOT getOrderedProductKOT(TempOrderedProductKOT dto,int orderInfoKot) {
		
		return OrderedProductKOT.builder()
				.orderInfoKOT(new OrderInfoKOT(orderInfoKot))
				.dish(dto.getDish())
				.rate(dto.getRate())
				.quantity(dto.getQuantity())
				.amount(dto.getAmount())
				.vatPer(dto.getVatPer())
				.vatAmount(dto.getVatAmount())
				.stPer(dto.getStPer())
				.stAmount(dto.getStAmount())
				.scPer(dto.getScPer())
				.scAmount(dto.getScAmount())
				.discountPer(dto.getDiscountPer())
				.discountAmount(dto.getDiscountAmount())
				.totalAmount(dto.getTotalAmount())
				.notes(null)
				.itemStatus(Common.itemStatus)
				.isActive(dto.getIsActive())
				.hotel(new Hotel(dto.getHotel().getId()))
				.build();
	}
	
}
