package com.sunan.hotel;

import org.springframework.stereotype.Component;

import com.sunan.model.Hotel;

@Component
public class HotelMapper {
	
	public Hotel getHotelBuilder(HotelDto dto) {
		
		return Hotel.builder()
				.address1(dto.getAddress1())
				.address2(dto.getAddress2())
				.address3(dto.getAddress3())
				.baseCurrency(dto.getBaseCurrency())
				.cin(dto.getCin())
				.contactNo(dto.getContactNo())
				.currencyCode(dto.getCurrencyCode())
				.email(dto.getEmail())
				.hotelName(dto.getHotelName())
				.id(dto.getId())
				.isActive(dto.getIsActive())
				.logo(dto.getLogo())
				.showLogo(dto.getShowLogo())
				.startBillNo(dto.getStartBillNo())
				.stNo(dto.getStNo())
				.ticketFooterMessage(dto.getTicketFooterMessage())
				.tin(dto.getTin())
				.build();
	}
	
	public HotelDto getHotelDtoBuilder(Hotel hotel) {
		return HotelDto.builder()
				.address1(hotel.getAddress1())
				.address2(hotel.getAddress2())
				.address3(hotel.getAddress3())
				.baseCurrency(hotel.getBaseCurrency())
				.cin(hotel.getCin())
				.contactNo(hotel.getContactNo())
				.currencyCode(hotel.getCurrencyCode())
				.email(hotel.getEmail())
				.hotelName(hotel.getHotelName())
				.id(hotel.getId())
				.isActive(hotel.getIsActive())
				.logo(hotel.getLogo())
				.showLogo(hotel.getShowLogo())
				.startBillNo(hotel.getStartBillNo())
				.stNo(hotel.getStNo())
				.ticketFooterMessage(hotel.getTicketFooterMessage())
				.tin(hotel.getTin())
				.build();
	}

}
