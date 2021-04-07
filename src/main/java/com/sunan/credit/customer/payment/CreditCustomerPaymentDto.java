package com.sunan.credit.customer.payment;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

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

	@NotNull
	@Min(1)
	private int creditCustomerId;

	private String creditCustomerName;

	private Double amount;

	private String remark;

	private String paymentModeDetails;

	private String isActive;

}
