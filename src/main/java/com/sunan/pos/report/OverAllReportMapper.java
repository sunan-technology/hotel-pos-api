package com.sunan.pos.report;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.sunan.model.Hotel;

@Component
public class OverAllReportMapper {

	public OverAllReportDto getOverAllReportDtoMapper(Hotel hotel, Double cash, Double wallet, Double card,
			Double dineIn, Double saleByOprator, Date fromDate, Date toDate) {
		return OverAllReportDto.builder().card(card).cash(cash).contactNo(hotel.getContactNo()).dineIn(dineIn)
				.email(hotel.getEmail()).fromDate(fromDate).hotelAddress(hotel.getAddress1())
				.hotelName(hotel.getHotelName()).saleByOprator(saleByOprator).toDate(toDate).wallet(wallet).build();
	}
	
	
	public CurrentStockDto getCurrentStockDto(int quantity,String rawMatrialName) {
		return CurrentStockDto.builder()
				.rawMatrialName(rawMatrialName)
				.quantity(quantity)
				.build();
	}
	
	
	
}
