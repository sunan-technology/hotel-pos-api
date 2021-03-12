package com.sunan.supplier;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SupplierDto {

	private int supplierId;

	private String supplierName;

	private String address;

	private String city;

	private String state;

	private String zipcode;

	private String contactNo;

	private String emailId;

	private String remarks;

	private String tin;

	private String stNo;

	private String cst;

	private String pan;

	private String accountName;

	private String accountNumber;

	private String bank;

	private String branch;

	private String ifscCode;

	private Double openingBalance;

	private String openingBalanceType;

	private String isActive;

}
