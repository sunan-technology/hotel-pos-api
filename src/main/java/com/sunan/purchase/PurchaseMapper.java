package com.sunan.purchase;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.sunan.model.Hotel;
import com.sunan.model.PerchaseJoin;
import com.sunan.model.Purchase;
import com.sunan.model.RawMatrial;
import com.sunan.model.Supplier;
import com.sunan.model.Units;
import com.sunan.model.Warehouses;
import com.sunan.purchase.join.AvailableRawMatrialDto;
import com.sunan.purchase.join.PurchaseDetailsDto;
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
				.poNumber(dto.getPoNumber())
				.purchaseType(dto.getPurchaseType())
				
				.rawMatrialAmountTotal(dto.getRawMatrialAmountTotal())
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
	
	
	public PurchaseDto getPurchaseDtoBuilder(Purchase purchase,List<PurchaseJoinDto> purchaseJoinDto) {
		
		
		
		return PurchaseDto.builder()
				.id(purchase.getId())
				.invoiceNo(purchase.getInvoiceNo())
				.invoiceDate(purchase.getDate())
				.supplierId(purchase.getSupplier().getSupplierId())
				.subTotal(purchase.getSubTotal())
				.discountPer(purchase.getDiscountPer())
				.poNumber(purchase.getPoNumber())
				.purchaseType(purchase.getPurchaseType())
				.discount(purchase.getDiscount())
				.rawMatrialAmountTotal(purchase.getRawMatrialAmountTotal())
				.roundOff(purchase.getRoundOff())
				.grandTotal(purchase.getGrandTotal())
				.updateInventoryStock(purchase.getUpdateInventoryStock())
				.gstNo(purchase.getGstNo())
				.deliveryChargesInInvoice(purchase.getDeliveryChargesInInvoice())
				.totalDeliveryCharges(purchase.getTotalDeliveryCharges())
				.totalDiscount(purchase.getTotalDiscount())
				.discountType(purchase.getDiscountType())
				.paymentType(purchase.getPaymentType())
				.paymentMode(purchase.getPaymentMode())
				.paidAmount(purchase.getPaidAmount())
				.paymentDate(purchase.getPaymentDate())
				.paymentReferenceNo(purchase.getPaymentReferenceNo())
				.taxCollectedAtSource(purchase.getTaxCollectedAtSource())
				.totalTaxCollectedAtSource(purchase.getTotalTaxCollectedAtSource())
				.cgst(purchase.getCgst())
				.sgst(purchase.getSgst())
				.igst(purchase.getIgst())
				.isActive(purchase.getIsActive())
				.purchaseJoinDtos(purchaseJoinDto)
				.build();
	}
	
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
					.rawMatrial(new RawMatrial(dto.getRawmatrialId()))
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
					.rawMatrial(new RawMatrial(dto.getRawmatrialId()))
					.hotel(new Hotel(hotelId))
					.isActive(dto.getIsActive())
					.build());
		}
		
		return list;
		
	}
	
	
	public List<PurchaseJoinDto> getPurchaseJoinDtoBuilder(List<PerchaseJoin> purchaseJoin){
		List<PurchaseJoinDto> list=new ArrayList<PurchaseJoinDto>();
		
		for(PerchaseJoin dto : purchaseJoin) {
			list.add(PurchaseJoinDto.builder()
					.id(dto.getId())
					.perchaseId(dto.getPurchase().getId())
					.rawmatrialId(dto.getRawMatrial().getId())
					.rawMatrialName(dto.getRawMatrialName())
					.quantity(dto.getQuantity())
					.price(dto.getPrice())
					.unitsId(dto.getUnits().getId())
					.totalAmount(dto.getTotalAmount())
					.cgst(dto.getCgst())
					.sgst(dto.getSgst())
					.igst(dto.getIgst())
					.description(dto.getDescription())
				    .warehousesId(dto.getWarehouses().getId())
					.hasExpiryDate(dto.getHasExpiryDate())
					.expiryDate(dto.getExpiryDate())
					.rawmatrialId(dto.getRawMatrial().getId())
					.isActive(dto.getIsActive())
					.build());
			
		}
		return list;
	}
	
	public PurchaseJoinDto getPurchaseJoinDto(PerchaseJoin dto) {
		
		
		return PurchaseJoinDto.builder()
				.id(dto.getId())
				.perchaseId(dto.getPurchase().getId())
				.rawmatrialId(dto.getRawMatrial().getId())
				.rawMatrialName(dto.getRawMatrialName())
				.quantity(dto.getQuantity())
				.price(dto.getPrice())
				.unitsId(dto.getUnits().getId())
				.totalAmount(dto.getTotalAmount())
				.cgst(dto.getCgst())
				.sgst(dto.getSgst())
				.igst(dto.getIgst())
				.description(dto.getDescription())
			    .warehousesId(dto.getWarehouses().getId())
				.hasExpiryDate(dto.getHasExpiryDate())
				.expiryDate(dto.getExpiryDate())
				.rawmatrialId(dto.getRawMatrial().getId())
				.isActive(dto.getIsActive())
				.build();
	}
	
	
	public List<PurchaseDetailsDto> getPurchaseDetailsDtoBuilder(List<PerchaseJoin> perchaseJoin){
		List<PurchaseDetailsDto> list=new ArrayList<PurchaseDetailsDto>();
		
		for(PerchaseJoin dto :perchaseJoin) {
			list.add(PurchaseDetailsDto.builder()
					.purchaseId(dto.getPurchase().getId())
					.purchaseDate(dto.getPurchase().getDate())
					.quantity(dto.getQuantity())
					.build());
		}
		
		return list;
	}
	
	
	List<AvailableRawMatrialDto> getAvailableRawMatrialDto(List<PerchaseJoin> perchaseJoin,List<PurchaseDetailsDto> purchaseDetailsDto){
		List<AvailableRawMatrialDto> list=new ArrayList<AvailableRawMatrialDto>();
		for(PerchaseJoin dto :perchaseJoin) {
			list.add(AvailableRawMatrialDto.builder()
					.rawMatrialId(dto.getId())
					.rawMatrialName(dto.getRawMatrialName())
					.purchaseDetailsDto(purchaseDetailsDto)
					.build());
		}
		return list;
	}
	
	
	List<AvailableRawMatrialDto> getAvailableRawMatrialDtoBulder(List<PerchaseJoin> perchaseJoin){
        List<PurchaseDetailsDto> list1=new ArrayList<PurchaseDetailsDto>();
		
//		for(PerchaseJoin dto :perchaseJoin) {
//			list1.add(PurchaseDetailsDto.builder()
//					.purchaseId(dto.getPurchase().getId())
//					.purchaseDate(dto.getPurchase().getDate())
//					.quantity(dto.getQuantity())
//					.build());
//		}
		
		
		
		List<AvailableRawMatrialDto> list=new ArrayList<AvailableRawMatrialDto>();
		for(PerchaseJoin dto :perchaseJoin) {
			
			list.add(AvailableRawMatrialDto.builder()
					.rawMatrialId(dto.getId())
					.rawMatrialName(dto.getRawMatrialName())
					.purchaseDetailsDto(list1)
					.build());
		}
		return list;
	}
	
}
