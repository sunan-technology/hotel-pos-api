package com.sunan.order.kot.temp.product;

import org.springframework.stereotype.Component;

import com.sunan.model.Category;
import com.sunan.model.Dish;
import com.sunan.model.HotelTable;
import com.sunan.model.TempOrderInfoKOT;
import com.sunan.model.TempOrderedProductKOT;
import com.sunan.order.kot.temp.info.DishKOTDto;
import com.sunan.order.kot.temp.info.TempOrderInfoKOTDto;
import com.sunan.utils.Common;

@Component
public class TempOrderedProductKOTMapper {
	
	
	public TempOrderedProductKOT tempOrderedProductKOTBuilder(TempOrderedProductKOTDto dto) {
		
		
		return TempOrderedProductKOT.builder()
				.tempOrderInfoKOT(new TempOrderInfoKOT(dto.getTempRestaurantPOSOrderInfoKOTId()))
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
				.hotelTable(new HotelTable(dto.getTableNo()))
				.itemStatus(dto.getItemStatus())
				.isActive(dto.getIsActive())
				.build();
	}
	
	
	public TempOrderedProductKOT tempOrderedProductKOT(TempOrderInfoKOTDto dto,DishKOTDto dish,int tempRestaurantPOSOrderInfoKOTId,Double amount,Category category,Dish dishes) {
		return TempOrderedProductKOT.builder()
				.tempOrderInfoKOT(new TempOrderInfoKOT(tempRestaurantPOSOrderInfoKOTId))
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
				.totalAmount(dto.getTotalAmount())
				.hotelTable(new HotelTable(dto.getTableNo()))
				.itemStatus(Common.itemStatus)
				.isActive("yes")
				.build();
	}
	

}
