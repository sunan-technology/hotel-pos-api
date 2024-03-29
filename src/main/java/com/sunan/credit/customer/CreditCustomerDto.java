package com.sunan.credit.customer;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreditCustomerDto {

	private int creditCustomerId;

	private String name;

	private String contactNo;

	private String address;

	private Date registerationDate;

	private String isActive;

}
