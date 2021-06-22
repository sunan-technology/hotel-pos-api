 package com.sunan.order.kot.temp.product;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sunan.model.Category;
import com.sunan.model.Dish;
import com.sunan.model.HotelTable;
import com.sunan.model.Taxes;
import com.sunan.model.TempOrderInfoKOT;
import com.sunan.model.TempOrderedProductKOT;
import com.sunan.order.kot.temp.info.DishKOTDto;
import com.sunan.order.kot.temp.info.TempOrderInfoKOTDto;
import com.sunan.taxes.TaxesRepository;
import com.sunan.utils.Common;

@Component
public class TempOrderedProductKOTMapper {
	
	@Autowired
	private TaxesRepository taxesRepo;
	
	public TempOrderedProductKOT tempOrderedProductKOTBuilder(TempOrderedProductKOTDto dto) {
		Double taxAmount=0.0;
		Optional<Taxes> taxes=taxesRepo.findById(dto.getTaxesId());
		if(taxes.isPresent()) {
			 taxAmount=dto.getAmount()*taxes.get().getTaxPer()/100;
		}
		
		return TempOrderedProductKOT.builder()
				.tempOrderInfoKOT(new TempOrderInfoKOT(dto.getTempRestaurantPOSOrderInfoKOTId()))
				.dish(dto.getDish())
				.rate(dto.getRate())
				.quantity(dto.getQuantity())
				.amount(dto.getAmount())
//				.vatPer(dto.getVatPer())
//				.vatAmount(dto.getVatAmount())
//				.stPer(dto.getStPer())
//				.stAmount(dto.getStAmount())
//				.scPer(dto.getScPer())
//				.scAmount(dto.getScAmount())
				.taxes(new Taxes(dto.getTaxesId()))
				.taxAmount(taxAmount)
				.discountPer(dto.getDiscountPer())
				.discountAmount(dto.getDiscountAmount())
				.totalAmount(dto.getTotalAmount())
				.hotelTable(new HotelTable(dto.getTableNo()))
				.itemStatus(dto.getItemStatus())
				.isActive(dto.getIsActive())
				.build();
	}
	
	
	public TempOrderedProductKOT tempOrderedProductKOT(TempOrderInfoKOTDto dto,DishKOTDto dish,int tempRestaurantPOSOrderInfoKOTId,Double amount,Category category,Dish dishes) {
		
		Double totalAmount=amount+ Common.calculateGST(dish.getRate(), category.getTaxes().getTaxPer()); 

		
		return TempOrderedProductKOT.builder()
				.tempOrderInfoKOT(new TempOrderInfoKOT(tempRestaurantPOSOrderInfoKOTId))
				.dish(dish.getDish())
				.rate(dish.getRate())
				.quantity(dish.getQuantity())
				.amount(amount)
//				.vatPer(category.getTaxes().getTaxPer())
//				.vatAmount(Common.calculateGST(dish.getRate(), category.getVat()))
//				.stPer(category.getSt())
//				.stAmount(Common.calculateGST(dish.getRate(), category.getSt()))
//				.scPer(category.getSc())
//				.scAmount(Common.calculateGST(dish.getRate(), category.getSc()))
				.taxes(new Taxes(dto.getTaxesId()))
				.taxAmount(Common.calculateGST(dish.getRate(), category.getTaxes().getTaxPer()))
				.discountPer(dishes.getDiscount())
				.discountAmount(Common.calculateGST(dish.getRate(), dishes.getDiscount()))
				.totalAmount(totalAmount)
				.hotelTable(new HotelTable(dto.getTableNo()))
				.itemStatus(Common.itemStatus)
				.isActive("yes")
				.build();
	}
	

}
