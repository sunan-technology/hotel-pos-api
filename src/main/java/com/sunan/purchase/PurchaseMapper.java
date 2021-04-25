package com.sunan.purchase;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.sunan.model.Hotel;
import com.sunan.model.PerchaseJoin;
import com.sunan.model.Purchase;
import com.sunan.model.Supplier;
import com.sunan.model.Units;
import com.sunan.model.Warehouses;
import com.sunan.purchase.join.PurchaseJoinDto;
import com.sunan.utils.Common;

@Component
public class PurchaseMapper {
	
	public Purchase getPerchaseBuilder(PurchaseDto dto,Double roundOff) {
		
		return Purchase.builder()
				.id(dto.getId())
				.invoiceNo(dto.getInvoiceNo())
				.date(dto.getInvoiceDate())
				.supplier(new Supplier(dto.getSupplierId()))
				.subTotal(dto.getSubTotal())
				.discountPer(Common.defaultValue)
				.discount(dto.getDiscount())
				.total(dto.getTotal())
				.roundOff(roundOff)
				.grandTotal(dto.getGrandTotal())
				.updateInventoryStock(dto.getUpdateInventoryStock())
				.gstNo(dto.getGstNo())
				.deliveryChargesInInvoice(dto.getDeliveryChargesInInvoice())
				.totalDeliveryCharges(dto.getTotalDeliveryCharges())
				.totalDiscount(dto.getTotalDiscount())
				.discountType(dto.getDiscountType())
				.paymentType(dto.getPaymentType())
				.paymentMode(dto.getPaymentMode())
				.paidAmount(dto.getPaidAmount())
				.paymentDate(dto.getPaymentDate())
				.paymentReferenceNo(dto.getPaymentReferenceNo())
				.taxCollectedAtSource(dto.getTaxCollectedAtSource())
				.totalTaxCollectedAtSource(dto.getTotalTaxCollectedAtSource())
				.cgst(dto.getCgst())
				.sgst(dto.getSgst())
				.igst(dto.getIgst())
				.isActive(dto.getIsActive())
				.build();
	}
	
	
//	public PurchaseDto getPurchaseDtoBuilder(Purchase purchase,PerchaseJoin perchaseJoin) {
//		
//		
//		
//		return PurchaseDto.builder()
//				.id(purchase.getId())
//				.invoiceNo(purchase.getInvoiceNo())
//				.invoiceDate(purchase.getDate())
//				.remarks(purchase.getRemarks())
//				.supplierId(purchase.getSupplier().getSupplierId())
//				.productId(perchaseJoin.getProduct().getId())
//				.productName(perchaseJoin.getProduct().getProductName())
//				.warehousesId(perchaseJoin.getWarehouses().getId())
//				.warehousesName(perchaseJoin.getWarehouses().getWarehouseName())
//				.quantity(perchaseJoin.getQuantity())
//				.pricePerUnit(perchaseJoin.getPrice())
//				.totalAmount(perchaseJoin.getTotalAmount())
//				.hasExpiryDate(perchaseJoin.getHasExpiryDate())
//				.expriyDate(perchaseJoin.getExpiryDate())
//				.subTotal(purchase.getSubTotal())
//				.discount(purchase.getDiscount())
//				.freightCharges(purchase.getFreightCharges())
//				.otherCharges(purchase.getOtherCharges())
//				.previousDue(purchase.getPreviousDue())
//				.total(purchase.getTotal())
//				.grandTotal(purchase.getGrandTotal())
//				.totalPayment(purchase.getTotalPayment())
//				.build();
//	}
	
//	public PerchaseJoin getPerchaseJoin(PurchaseDto purchaseDto,int purchaseId) {
//		
//		return PerchaseJoin.builder()
//				.purchase(new Purchase(purchaseId))
//				.product(new Product(purchaseDto.getProductId()))
//				.quantity(purchaseDto.getQuantity())
//				.price(purchaseDto.getPricePerUnit())
//				.totalAmount(purchaseDto.getTotalAmount())
//				.warehouses(new Warehouses(purchaseDto.getWarehousesId()))
//				.hasExpiryDate(purchaseDto.getHasExpiryDate())
//				.expiryDate(purchaseDto.getExpriyDate())
//				.isActive("yes")
//				.build();
//	}
//	
	
//	public PerchaseJoin getPerchaseJoinBuilder(PurchaseJoinDto dto,int purchaseId)
//	{
//		return PerchaseJoin.builder()
//				.purchase(new Purchase(dto.getPerchaseId()))
//				.product(new Product(dto.getProductId()))
//				.quantity(dto.getQuantity())
//				.price(dto.getPrice())
//				.totalAmount(dto.getTotalAmount())
//				.warehouses(new Warehouses(dto.getWarehousesId()))
//				.hasExpiryDate(dto.getHasExpiryDate())
//				.expiryDate(dto.getExpiryDate())
//				.isActive("yes")
//				.build();
//	}
//	
//	
	
	
	public List<PerchaseJoin> getPurchaseJoin(List<PurchaseJoinDto> purchaseJoinDto,int purchaseId,int hotelId){
		
		List<PerchaseJoin> list=new ArrayList<PerchaseJoin>();
		for(PurchaseJoinDto dto : purchaseJoinDto) {
			
			list.add(PerchaseJoin.builder()
					.id(dto.getId())
					.purchase(new Purchase(purchaseId))
					.rawMatrialName(dto.getRawMatrialName())
					.quantity(dto.getQuantity())
					.price(dto.getPrice())
					.units(new Units(dto.getUnitsId()))
					.totalAmount(dto.getTotalAmount())
					.cgst(dto.getCgst())
					.sgst(dto.getSgst())
					.igst(dto.getIgst())
					.description(dto.getDescription())
					.warehouses(new Warehouses(dto.getWarehousesId()))
					.hasExpiryDate(dto.getHasExpiryDate())
					.expiryDate(dto.getExpiryDate())
					.hotel(new Hotel(hotelId))
					.isActive(dto.getIsActive())
					.build());
		}
		
		return list;
		
	}

}
