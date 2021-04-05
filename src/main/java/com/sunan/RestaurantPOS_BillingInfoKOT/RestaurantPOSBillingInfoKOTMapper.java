package com.sunan.RestaurantPOS_BillingInfoKOT;

import org.springframework.stereotype.Component;

import com.sunan.model.Customer;
import com.sunan.model.Member;
import com.sunan.model.RestaurantPOSBillingInfoKOT;

@Component
public class RestaurantPOSBillingInfoKOTMapper {
	
	public RestaurantPOSBillingInfoKOT getRestaurantPOSBillingInfoKOTBuilder(RestaurantPOSBillingInfoKOTDto dto,Double roundOff) {
		
		return RestaurantPOSBillingInfoKOT.builder()
				.id(dto.getId())
				.billNO(dto.getBillNO())
				.ODN(dto.getODN())
				.billDate(dto.getBillDate())
				.kotDiscountPer(dto.getKotDiscountPer())
				.KotDiscountAmount(dto.getKotDiscountAmount())
				.grandTotal(dto.getGrandTotal())
				.cash(dto.getCash())
				.change(dto.getChange())
				.operator(dto.getOperator())
				.paymentMode(dto.getPaymentMode())
				.exchangeRate(dto.getExchangeRate())
				.currencyCode(dto.getCurrencyCode())
				.discountReason(dto.getDiscountReason())
				.member(new Member(dto.getMemberId()))
				.loyaltyPoints(dto.getLoyaltyPoints())
				.loyaltyAmount(dto.getLoyaltyAmount())
				.roundOff(roundOff)
				.customer(new Customer(dto.getCustomerId()))
				.di_Status(dto.getDi_Status())
				.isActive(dto.getIsActive())
				.build();
	}
	
	
//	public RestaurantPOSBillingInfoKOTDto getRestaurantPOSBillingInfoKOTDtoBuilder(RestaurantPOSBillingInfoKOT dto) {
//		
//		return RestaurantPOSBillingInfoKOTDto.builder()
//				.id(dto.getId())
//				.billNO(dto.getBillNO())
//				.ODN(dto.getODN())
//				.billDate(dto.getBillDate())
//				.kotDiscountPer(dto.getKotDiscountPer())
//				.KotDiscountAmount(dto.getKotDiscountAmount())
//				.grandTotal(dto.getGrandTotal())
//				.cash(dto.getCash())
//				.change(dto.getChange())
//				.operator(dto.getOperator())
//				.paymentMode(dto.getPaymentMode())
//				.exchangeRate(dto.getExchangeRate())
//				.currencyCode(dto.getCurrencyCode())
//				.discountReason(dto.getDiscountReason())
//				.loyaltyPoints(dto.getLoyaltyPoints())
//				.loyaltyAmount(dto.getLoyaltyAmount())
//				.roundOff(dto.getRoundOff())
//				.di_Status(dto.getDi_Status())
//				.build();
//	}
//	

}
