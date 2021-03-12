package com.sunan.supplier;

import org.springframework.stereotype.Component;

import com.sunan.model.Supplier;

@Component
public class SupplierMapper {
	
	public Supplier getSupplierBuilder(SupplierDto dto) {
		
		return Supplier.builder()
				.supplierId(dto.getSupplierId())
				.supplierName(dto.getSupplierName())
				.address(dto.getAddress())
				.city(dto.getCity())
				.state(dto.getState())
				.zipcode(dto.getZipcode())
				.contactNo(dto.getContactNo())
				.emailId(dto.getEmailId())
				.remarks(dto.getRemarks())
				.tin(dto.getTin())
				.stNo(dto.getStNo())
				.cst(dto.getCst())
				.pan(dto.getPan())
				.accountName(dto.getAccountName())
				.accountNumber(dto.getAccountNumber())
				.bank(dto.getBank())
				.branch(dto.getBranch())
				.ifscCode(dto.getIfscCode())
				.openingBalance(dto.getOpeningBalance())
				.openingBalanceType(dto.getOpeningBalanceType())
				.isActive(dto.getIsActive())
				.build();
	}
	
	public SupplierDto getSupplierDtoBuilder(Supplier supplier) {
		
		return SupplierDto.builder()
				.supplierId(supplier.getSupplierId())
				.supplierName(supplier.getSupplierName())
				.address(supplier.getAddress())
				.city(supplier.getCity())
				.state(supplier.getState())
				.zipcode(supplier.getZipcode())
				.contactNo(supplier.getContactNo())
				.emailId(supplier.getEmailId())
				.remarks(supplier.getRemarks())
				.tin(supplier.getTin())
				.stNo(supplier.getStNo())
				.cst(supplier.getCst())
				.pan(supplier.getPan())
				.accountName(supplier.getAccountName())
				.accountNumber(supplier.getAccountNumber())
				.bank(supplier.getBank())
				.branch(supplier.getBranch())
				.ifscCode(supplier.getIfscCode())
				.openingBalance(supplier.getOpeningBalance())
				.openingBalanceType(supplier.getOpeningBalanceType())
				.isActive(supplier.getIsActive())
				.build();
	}
	

}
