package com.sunan.supplierPayment;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SupplierPaymentDto {

	private int supplierId;
	private String transactionNo;
	private Date transactionDate;
	private String paymentMode;
	private Double amount;
	private String paymentModeDetails;
	private String remarks;

}
