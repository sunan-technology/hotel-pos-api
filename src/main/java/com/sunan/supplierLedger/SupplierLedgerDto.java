package com.sunan.supplierLedger;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SupplierLedgerDto {

	private int id;

	private Date date;

	private String name;

	private String ledgerNo;

	private String label;

	private Double debit;

	private Double credit;

	//private String partyId;
	
	private int supplierId;
	

	private String isActive;

}
