package com.sunan.creditCustomerPayment;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreditCustomerPaymentDto {

	private int id;

	private Date date;

	private String paymentMode;

	private int creditCustomerId;

	private String creditCustomerName;

	private Double amount;

	private String remark;

	private String paymentModeDetails;

	private String isActive;

}
