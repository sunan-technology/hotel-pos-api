package com.sunan.credit.customer.payment;

import org.springframework.stereotype.Component;

import com.sunan.credit.customer.CreditCustomerBalanceDto;
import com.sunan.model.CreditCustomer;
import com.sunan.model.CreditCustomerPayment;

@Component
public class CreditCustomerPaymentMapper {

	public CreditCustomerPayment getCreditCustomerPaymentBuilder(CreditCustomerPaymentDto dto) {

		return CreditCustomerPayment.builder().id(dto.getId()).date(dto.getDate()).paymentMode(dto.getPaymentMode())
				.creditCustomer(new CreditCustomer(dto.getCreditCustomerId())).amount(dto.getAmount())
				.remark(dto.getRemark()).paymentModeDetails(dto.getPaymentModeDetails()).isActive(dto.getIsActive())
				.build();
	}

	public CreditCustomerPaymentDto getCreditCustomerPaymentDtoBuilder(CreditCustomerPayment creditCustomerPayment) {

		return CreditCustomerPaymentDto.builder().id(creditCustomerPayment.getId())
				.date(creditCustomerPayment.getDate()).paymentMode(creditCustomerPayment.getPaymentMode())
				.creditCustomerId(creditCustomerPayment.getCreditCustomer().getCreditCustomerId())
				.creditCustomerName(creditCustomerPayment.getCreditCustomer().getName())
				.amount(creditCustomerPayment.getAmount()).remark(creditCustomerPayment.getRemark())
				.paymentModeDetails(creditCustomerPayment.getPaymentModeDetails())
				.isActive(creditCustomerPayment.getIsActive()).build();
	}

	public CreditCustomerBalanceDto getCreditCustomerBalanceDtoBuilder(CreditCustomer creditCustomer, Double balance) {
		return CreditCustomerBalanceDto.builder().name(creditCustomer.getName()).address(creditCustomer.getAddress())
				.contactNo(creditCustomer.getContactNo()).balance(balance).build();
	}

}
