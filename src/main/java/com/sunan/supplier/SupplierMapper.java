package com.sunan.supplier;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.sunan.model.Supplier;
import com.sunan.model.SupplierLedger;
import com.sunan.supplier.ledger.SupplierLedgerDto;

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
	
	public SupplierLedger getSupplierLedgerBuilder(SupplierLedgerDto dto) {
		
		return SupplierLedger.builder()
				.id(dto.getId())
				.date(dto.getDate())
				.name(dto.getName())
				.ledgerNo(dto.getLedgerNo())
				.label(dto.getLabel())
				.debit(dto.getDebit())
				.credit(dto.getCredit())
				.supplier(new Supplier(dto.getSupplierId()))
				.build();
	}
	
	public SupplierLedger getSupplierLedger(SupplierDto dto,int supplierId,Double opengingBalance) {
		return SupplierLedger.builder()
				.date(new Date())
				.name(dto.getSupplierName())
				.ledgerNo(null)
				.label("OpeningBalance")
				.debit(0.0)
				.credit(opengingBalance)
				.supplier(new Supplier(supplierId))
				.isActive(dto.getIsActive())
				.build();
	}
	
	public SupplierBalanceDto getSupplierBalanceDtoBuilder(Supplier supplier, Double balance) {
		
		return SupplierBalanceDto.builder()
				.supplierId(supplier.getSupplierId())
				.supplierName(supplier.getSupplierName())
				.address(supplier.getAddress())
				.city(supplier.getCity())
				.contactNo(supplier.getContactNo())
				.balance(balance)
				.build();
	}
	

}
