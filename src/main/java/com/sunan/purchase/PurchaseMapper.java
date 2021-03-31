package com.sunan.purchase;

import org.springframework.stereotype.Component;

import com.sunan.model.PerchaseJoin;
import com.sunan.model.Product;
import com.sunan.model.Purchase;
import com.sunan.model.StorageType;
import com.sunan.model.Supplier;
import com.sunan.model.Warehouses;
import com.sunan.purchaseJoin.PurchaseJoinDto;
import com.sunan.utils.Common;

@Component
public class PurchaseMapper {
	
	public Purchase getPerchaseBuilder(PurchaseDto dto) {
		
		return Purchase.builder()
				.id(dto.getId())
				.invoiceNo(dto.getInvoiceNo())
				.date(dto.getInvoiceDate())
				.purchaseType(dto.getPurchasetype())
				.remarks(dto.getRemarks())
				.supplier(new Supplier(dto.getSupplierId()))
				.subTotal(dto.getSubTotal())
				.discount(dto.getDiscount())
				.discountPer(Common.defaultValue)
				.paymentDue(Common.defaultValue)
				.previousDue(dto.getPreviousDue())
				.freightCharges(dto.getFreightCharges())
				.otherCharges(dto.getOtherCharges())
				.total(dto.getTotal())
				.roundOff(dto.getRoundOff())
				.grandTotal(dto.getGrandTotal())
				.totalPayment(dto.getTotalPayment())
				.isActive(dto.getIsActive())
				.build();
	}
	
	
	public PurchaseDto getPurchaseDtoBuilder(Purchase purchase,PerchaseJoin perchaseJoin) {
		
		
		
		return PurchaseDto.builder()
				.id(purchase.getId())
				.invoiceNo(purchase.getInvoiceNo())
				.invoiceDate(purchase.getDate())
				.remarks(purchase.getRemarks())
				.supplierId(purchase.getSupplier().getSupplierId())
				.productId(perchaseJoin.getProduct().getId())
				.productName(perchaseJoin.getProduct().getProductName())
				.storageTypeId(perchaseJoin.getStorageType().getId())
				.storageTypeName(perchaseJoin.getStorageType().getName())
				.warehousesId(perchaseJoin.getWarehouses().getId())
				.warehousesName(perchaseJoin.getWarehouses().getWarehouseName())
				.quantity(perchaseJoin.getQuantity())
				.pricePerUnit(perchaseJoin.getPrice())
				.totalAmount(perchaseJoin.getTotalAmount())
				.hasExpiryDate(perchaseJoin.getHasExpiryDate())
				.expriyDate(perchaseJoin.getExpiryDate())
				.subTotal(purchase.getSubTotal())
				.discount(purchase.getDiscount())
				.freightCharges(purchase.getFreightCharges())
				.otherCharges(purchase.getOtherCharges())
				.previousDue(purchase.getPreviousDue())
				.total(purchase.getTotal())
				.roundOff(purchase.getRoundOff())
				.grandTotal(purchase.getGrandTotal())
				.totalPayment(purchase.getTotalPayment())
				.build();
	}
	
	public PerchaseJoin getPerchaseJoin(PurchaseDto purchaseDto,int purchaseId) {
		
		return PerchaseJoin.builder()
				.purchase(new Purchase(purchaseId))
				.product(new Product(purchaseDto.getId()))
				.quantity(purchaseDto.getQuantity())
				.price(purchaseDto.getPricePerUnit())
				.totalAmount(purchaseDto.getTotalAmount())
				.storageType(new StorageType(purchaseDto.getStorageTypeId()))
				.warehouses(new Warehouses(purchaseDto.getWarehousesId()))
				.hasExpiryDate(purchaseDto.getHasExpiryDate())
				.expiryDate(purchaseDto.getExpriyDate())
				.isActive("yes")
				.build();
	}
	
	
	public PerchaseJoin getPerchaseJoinBuilder(PurchaseJoinDto dto)
	{
		return PerchaseJoin.builder()
				.purchase(new Purchase(dto.getPerchaseId()))
				.product(new Product(dto.getProductId()))
				.quantity(dto.getQuantity())
				.price(dto.getPrice())
				.totalAmount(dto.getTotalAmount())
				.storageType(new StorageType(dto.getStorageTypeId()))
				.warehouses(new Warehouses(dto.getWarehousesId()))
				.hasExpiryDate(dto.getHasExpiryDate())
				.expiryDate(dto.getExpiryDate())
				.isActive("yes")
				.build();
	}
	
	

}
