package com.sunan.RestaurantPOS_BillingInfoKOT;

import java.util.Date;
import java.util.List;

import com.sunan.TempRestaurantPOS_OrderInfoKOT.DishKOTDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantPOSBillingInfoKOTDto {

	private int id;

	private String billNO;

	private String ODN;

	private Date billDate;

	private Double kotDiscountPer;

	private Double KotDiscountAmount;

	private Double grandTotal;

	private Double cash;

	private Double change;

	private String operator;

	private String paymentMode;

	private Double exchangeRate;

	private String currencyCode;

	private String discountReason;

	private int memberId;

	private int loyaltyPoints;

	private Double loyaltyAmount;

	
	private int customerId;

	private String di_Status;

	public List<DishKOTDto> dish;

	private int tableNo;

	private Double totalAmount;

	private String groupName;

	private String ticketNo;

	private String isActive;

}
