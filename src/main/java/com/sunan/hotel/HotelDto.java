package com.sunan.hotel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HotelDto {

	private int id;

	private String hotelName;

	private String address1;

	private String address2;

	private String address3;

	private String contactNo;

	private String email;

	private String tin;

	private String stNo;

	private String cin;

	private String logo;

	private String baseCurrency;

	private String currencyCode;

	private String ticketFooterMessage;

	private String showLogo;

	private String startBillNo;

	private String isActive;

}
