package com.sunan.credit.customer.ledger;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreditCustomerLedgerDto {

	private int id;
	private Date date;
	private String ledgerNo;
	private String label;
	private Double debit;
	private Double credit;
	private int creditCustomerId;
	private String isActive;

}
