package com.sunan.TempRestaurantPOS_OrderedProductKOT;

import org.springframework.stereotype.Component;

import com.sunan.TempRestaurantPOS_OrderInfoKOT.DishKOTDto;
import com.sunan.TempRestaurantPOS_OrderInfoKOT.TempRestaurantPOSOrderInfoKOTDto;
import com.sunan.model.HotelTable;
import com.sunan.model.TempRestaurantPOSOrderInfoKOT;
import com.sunan.model.TempRestaurantPOSOrderedProductKOT;
import com.sunan.utils.Common;

@Component
public class TempRestaurantPOSOrderedProductKOTMapper {
	
	
	public TempRestaurantPOSOrderedProductKOT tempRestaurantPOSOrderedProductKOTBuilder(TempRestaurantPOSOrderedProductKOTDto dto) {
		
		
		return TempRestaurantPOSOrderedProductKOT.builder()
				.tempRestaurantPOSOrderInfoKOT(new TempRestaurantPOSOrderInfoKOT(dto.getTempRestaurantPOSOrderInfoKOTId()))
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
	
	
	public TempRestaurantPOSOrderedProductKOT tempRestaurantPOSOrderedProductKOT(TempRestaurantPOSOrderInfoKOTDto dto,DishKOTDto dish,int tempRestaurantPOSOrderInfoKOTId,Double amount,Double vatPer,Double vatAmount,Double stPer,Double stAmount,Double scPer,Double scAmount,Double discount,Double discountAmount) {
		return TempRestaurantPOSOrderedProductKOT.builder()
				.tempRestaurantPOSOrderInfoKOT(new TempRestaurantPOSOrderInfoKOT(tempRestaurantPOSOrderInfoKOTId))
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
				.itemStatus(Common.itemStatus)
				.isActive("yes")
				.build();
	}
	

}
