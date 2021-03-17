package com.sunan.supplierPayment;

import org.springframework.stereotype.Component;

import com.sunan.model.Supplier;
import com.sunan.model.SupplierLedger;

@Component
public class SupplierPaymentMapper {
	
	
	public SupplierLedger getSupplierPaymentBuilder(SupplierPaymentDto dto) {
		
		return SupplierLedger.builder()
				.supplier(new Supplier(dto.getSupplierId()))
				.ledgerNo(dto.getTransactionNo())
				.date(dto.getTransactionDate())
				.name(dto.getPaymentMode())
				.debit(dto.getAmount())
				.credit(0.0)
				.label("payment")
				.isActive("yes")
				.build();
	}
	
	
	public SupplierPaymentDto getSupplierPaymentDtoBuilder(SupplierLedger supplierLedger) {
		
		return SupplierPaymentDto.builder()
				.supplierId(supplierLedger.getSupplier().getSupplierId())
			    .transactionNo(supplierLedger.getLedgerNo())
			    .transactionDate(supplierLedger.getDate())
			    .paymentMode(supplierLedger.getName())
			    .amount(supplierLedger.getDebit())
				.build();
	}

}
