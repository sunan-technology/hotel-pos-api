package com.sunan.pos.report;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OverAllReportOneDto {
	
	private String hotelName;
	private String hotelAddress;
	private String contactNo;
	private String email;
	private Date fromDate;
	private Date toDate;
	List<ProductBillReportDto> productBillReportDtos;
	private Double grossTotal;
	private Double cgst;
	private Double sgst;
	private Double discount;
	private Double deliveryCharges;
	private Double parcelCharges;
	private Double loyaltyAmount;
	private Double roundOff;
	private Double grandTotal;
	

}
