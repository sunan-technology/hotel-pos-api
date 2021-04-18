package com.sunan.pos.report;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OverAllReportDto {

	private String hotelName;

	private String hotelAddress;

	private String contactNo;

	private String email;

	private Date fromDate;

	private Date toDate;

	private Double saleByOprator;

	private Double cash;

	private Double wallet;

	private Double card;

	private Double dineIn;

}
