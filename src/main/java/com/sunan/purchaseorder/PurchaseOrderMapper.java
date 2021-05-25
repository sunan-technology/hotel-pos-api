package com.sunan.purchaseorder;

import org.springframework.stereotype.Component;

import com.sunan.model.PurchaseOrder;
import com.sunan.model.PurchaseOrderJoin;
import com.sunan.model.Supplier;

@Component
public class PurchaseOrderMapper {
	
	
	public PurchaseOrder getPurchaseOrderBuilder(PurchaseOrderDto dto) {
		
		return PurchaseOrder.builder()
				.id(dto.getId())
				.date(dto.getDate())
				.poNumber(dto.getPoNumber())
				.supplier(new Supplier(dto.getSupplierId()))
				.terms(dto.getTerms())
				.subTotal(dto.getSubTotal())
				.vatPer(dto.getVatPer())
				.vatAmount(dto.getVatAmount())
				.addVat(dto.getAddVat())
				.addVatAmount(dto.getAddVatAmount())
				.grandTotal(dto.getGrandTotal())
				.taxType(dto.getTaxType())
				.isActive(dto.getIsActive())
				.build();
	}
	
	public PurchaseOrderJoin getPurchaseOrderJoin(PurchaseOrderDto dto, int PurchaseOrderId) {
		
		
		return PurchaseOrderJoin.builder()
				.purchaseOrder(new PurchaseOrder(PurchaseOrderId))
				.quantity(dto.getQuantity())
				.pricePerUnit(dto.getPricePerUnit())
				.amount(dto.getTotalAmount())
				.isActive("yes")
				.build();
	}

	
	/*
	 * public PurchaseOrderJoin getPurchaseOrderJoinBuilder(PurchaseOrderJoinDto
	 * dto) {
	 * 
	 * return PurchaseOrderJoin.builder() .id(dto.getId()) .purchaseOrder(new
	 * PurchaseOrder(dto.getPurchaseOrderId())) .product(new
	 * Product(dto.getProductId())) .quantity(dto.getQuantity())
	 * .pricePerUnit(dto.getPricePerUnit()) .amount(dto.getAmount())
	 * .isActive("yes") .build(); }
	 */
	
	public PurchaseOrderDto getPurchaseOrderDtoBuilder(PurchaseOrder purchaseOrder,PurchaseOrderJoin purchaseOrderJoin) {
		
		return PurchaseOrderDto.builder()
				.id(purchaseOrder.getId())
				.poNumber(purchaseOrder.getPoNumber())
				.date(purchaseOrder.getDate())
				.supplierId(purchaseOrder.getSupplier().getSupplierId())
				.terms(purchaseOrder.getTerms())
				.subTotal(purchaseOrder.getSubTotal())
				.vatPer(purchaseOrder.getVatPer())
				.vatAmount(purchaseOrder.getVatAmount())
				.addVat(purchaseOrder.getAddVat())
				.addVatAmount(purchaseOrder.getAddVatAmount())
				.taxType(purchaseOrder.getTaxType())
				.grandTotal(purchaseOrder.getGrandTotal())
				.isActive(purchaseOrder.getIsActive())
				.quantity(purchaseOrderJoin.getQuantity())
				.pricePerUnit(purchaseOrderJoin.getPricePerUnit())
				.totalAmount(purchaseOrderJoin.getAmount())
				.build();
	}
}
