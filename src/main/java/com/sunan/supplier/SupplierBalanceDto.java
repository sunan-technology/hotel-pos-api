package com.sunan.supplier;

import com.sunan.supplier.SupplierDto.SupplierDtoBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SupplierBalanceDto {
	
	private int supplierId;
	private String supplierName;
	private String address;
	private String city;
	private String contactNo;
	private Double balance;

}
