package com.sunan.pos.report;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SupplierReportDto {
	
	private String supplierName;
	private String supplierContact;
	private Date purchaseDate;
	private Double purchaseAmount;
	private String invoiceNo;
	

}

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class SupplierMatrialReportDto{
	
	private String rawMatrialName;
	private int quantity;
	private Date expiryDate;
}
