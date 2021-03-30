package com.sunan.TempRestaurantPOS_OrderedProductKOT;

import org.springframework.stereotype.Component;

import com.sunan.model.TempRestaurantPOSOrderInfoKOT;
import com.sunan.model.TempRestaurantPOSOrderedProductKOT;

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
				.tableNumber(dto.getTableNumber())
				.itemStatus(dto.getItemStatus())
				.isActive(dto.getIsActive())
				.build();
	}
	

}
