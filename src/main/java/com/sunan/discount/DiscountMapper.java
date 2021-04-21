package com.sunan.discount;

import org.springframework.stereotype.Component;

import com.sunan.model.Discount;

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
				.orderType(dto.getOrderType())
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
	
	public DiscountDto getDiscountDtoBuilder(Discount discount) {
		
		return DiscountDto.builder()
				.id(discount.getId())
				.discountOn(discount.getDiscountOn())
				.title(discount.getTitle())
				.type(discount.getType())
				.amount(discount.getAmount())
				.description(discount.getDescription())
				.termsAndConditions(discount.getTermsAndConditions())
				.orderType(discount.getOrderType())
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

}
