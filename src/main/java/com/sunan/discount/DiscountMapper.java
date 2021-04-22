package com.sunan.discount;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.sunan.model.Discount;
import com.sunan.model.DiscountOrderType;
import com.sunan.model.Hotel;

@Component
public class DiscountMapper {
	
	public Discount getDiscountBuilder(DiscountDto dto) {
		
		return Discount.builder()
				.id(dto.getId())
				.discountOn(dto.getDiscountOn())
				.title(dto.getTitle())
				.type(dto.getType())
				.amount(dto.getAmount())
				.description(dto.getDescription())
				.termsAndConditions(dto.getTermsAndConditions())
				.applicableOn(dto.getApplicableOn())
				.fromDate(dto.getFromDate())
				.toDate(dto.getToDate())
				.from(dto.getFrom())
				.to(dto.getTo())
				.validation(dto.getValidation())
				.days(dto.getDays())
				.status(dto.getStatus())
				.isActive(dto.getIsActive())
				.build();
	}
	
	public DiscountDto getDiscountDtoBuilder(Discount discount,List<DiscountOrderTypeDto> discountOrderType1) {
		
		return DiscountDto.builder()
				.id(discount.getId())
				.discountOn(discount.getDiscountOn())
				.title(discount.getTitle())
				.type(discount.getType())
				.amount(discount.getAmount())
				.description(discount.getDescription())
				.termsAndConditions(discount.getTermsAndConditions())
				.orderType(discountOrderType1)
				.applicableOn(discount.getApplicableOn())
				.fromDate(discount.getFromDate())
				.toDate(discount.getToDate())
				.from(discount.getFrom())
				.to(discount.getTo())
				.validation(discount.getValidation())
				.days(discount.getDays())
				.status(discount.getStatus())
				.isActive(discount.getIsActive())
				.build();
	}
	
	
	
	
	public List<DiscountOrderType> getDiscountOrderTypeBuilder(List<DiscountOrderTypeDto> list2,int discountId,int hotelId) {
		
		
		List<DiscountOrderType> list = new ArrayList<DiscountOrderType>();
		for(DiscountOrderTypeDto dto : list2) {
			list.add(DiscountOrderType.builder()
					.discountOrderType(dto.getDiscountOrderType())
					.discount(new Discount(discountId))
					.isActive("yes")
					.hotel(new Hotel(hotelId))
					.build());
			
		}
		return list;	
	}
	
	public List<DiscountOrderTypeDto> getDiscountOrderTypeDtoBuilder(List<DiscountOrderType> discountOrderType){
		
		List<DiscountOrderTypeDto> list=new ArrayList<DiscountOrderTypeDto>();
		for(DiscountOrderType dto :discountOrderType) {
			list.add(DiscountOrderTypeDto.builder()
					.id(dto.getId())
					.discountOrderType(dto.getDiscountOrderType())
					.build());
		}
		return list;
		
	}

}
