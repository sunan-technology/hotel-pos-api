package com.sunan.creditCustomerLedger;

import org.springframework.stereotype.Component;

import com.sunan.creditCustomerPayment.CreditCustomerPaymentDto;
import com.sunan.model.CreditCustomer;
import com.sunan.model.CreditCustomerLedger;

@Component
public class CreditCustomerLedgerMapper {
	
	public CreditCustomerLedger getCreditCustomerLedgerBuilder(CreditCustomerLedgerDto dto)
	{
		return CreditCustomerLedger.builder()
				.id(dto.getId())
				.date(dto.getDate())
				.ledgerNo(dto.getLedgerNo())
				.label(dto.getLabel())
				.debit(dto.getDebit())
				.credit(dto.getCredit())
				.creditCustomer(new CreditCustomer(dto.getCreditCustomerId()))
				.isActive(dto.getIsActive())
				.build();
	}

	public CreditCustomerLedger getCreditCustomerLedgerBuilder(CreditCustomerPaymentDto creditCustomerPaymentDto, int creditCustomerPaymentId)
	{
		return CreditCustomerLedger.builder()
//				.id(dto.getId())
//				.date(dto.getDate())
//				.ledgerNo(dto.getLedgerNo())
//				.label(dto.getLabel())
//				.debit(dto.getDebit())
//				.credit(dto.getCredit())
//				.creditCustomer(new CreditCustomer(dto.getCreditCustomerId()))
//				.isActive(dto.getIsActive())
				.build();
	}
}
