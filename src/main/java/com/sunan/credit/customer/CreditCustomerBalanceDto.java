package com.sunan.credit.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreditCustomerBalanceDto {

//	private int accountNo;
	private String name;
	private String address;
	private String contactNo;
	private Double balance;
}
