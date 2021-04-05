package com.sunan.creditCustomerLedger;

import org.springframework.stereotype.Component;

import com.sunan.constants.DefaultConstantValues;
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
				.credit(creditCustomerPaymentDto.getAmount())
				.creditCustomer(new CreditCustomer(creditCustomerPaymentDto.getCreditCustomerId()))
				.date(creditCustomerPaymentDto.getDate())
				.debit(DefaultConstantValues.DEFAULT_DOUBLE_VALUE)
				.isActive(DefaultConstantValues.ACTIVE_YES)
				.label(DefaultConstantValues.PAYMENT)
				.ledgerNo(createLadgerNo(creditCustomerPaymentId, creditCustomerPaymentDto.getCreditCustomerId()))
				.id(creditCustomerPaymentDto.getId())
				.build();
	}
	
	private String createLadgerNo(int creditCustomerPaymentId, int creditCustomerId) {	
		String ledgerNo = "C " + creditCustomerPaymentId + " T " + creditCustomerId;
		return ledgerNo;
	}
}
