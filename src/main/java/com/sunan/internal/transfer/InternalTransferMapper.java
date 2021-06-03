package com.sunan.internal.transfer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.sunan.internal.transfer.join.InternalTransferJoinDto;
import com.sunan.model.Hotel;
import com.sunan.model.InternalTransfer;
import com.sunan.model.InternalTransferJoin;
import com.sunan.model.Kitchen;
import com.sunan.model.RawMatrial;
import com.sunan.model.Units;

@Component
public class InternalTransferMapper {
	
	public InternalTransfer getInternalTransferBuilder(InternalTransferDto dto) {
		return  InternalTransfer.builder()
				.id(dto.getId())
				.kitchen(new Kitchen(dto.getKitchenId()))
				//.mrnNo(dto.getMrnNo())
				.invoiceDate(dto.getInvoiceDate())
				.invoiceNo(dto.getInvoiceNo())
//				.deliveryChargesInInvoice(dto.getDeliveryChargesInInvoice())
//				.cgst(dto.getCgst())
//				.sgst(dto.getSgst())
//				.igst(dto.getIgst())
//				.rawMatrialAmountTotal(dto.getRawMatrialAmountTotal())
//				.discountType(dto.getDiscountType())
//				.discountInInvoice(dto.getDiscountInInvoice())
//				.discountPer(dto.getDiscountPer())
//				.totalDeliveryCharges(dto.getTotalDeliveryCharges())
//				.totalDiscount(dto.getTotalDiscount())
//				.taxCollectedAtSource(dto.getTaxCollectedAtSource())
//				.totalTaxCollectedAtSource(dto.getTotalTaxCollectedAtSource())
//				.paymentType(dto.getPaymentType())
//				.taxPayableUnderReverseCharge(dto.getTaxPayableUnderReverseCharge())
//				.paidAmount(dto.getPaidAmount())
//				.paymentDate(dto.getPaymentDate())
//				.paymentReferenceNo(dto.getPaymentReferenceNo())
//				.paymentMode(dto.getPaymentMode())
//				.bankName(dto.getBankName())
//				.bankBranch(dto.getBankBranch())
//				.ifscCode(dto.getIfscCode())
//				.accountNo(dto.getAccountNo())
//				.address(dto.getAddress())
//				.termsAndConditions(dto.getTermsAndConditions())
//				.updateInventoryStock(dto.getUpdateInventoryStock())
//				.editable(dto.getEditable())
//				.subTotal(dto.getSubTotal())
//				.grandTotal(dto.getGrandTotal())
				.isActive(dto.getIsActive())
				.build();
		
	}
	
	
	public List<InternalTransferJoin> getInternalTransferJoinBuilder(List<InternalTransferJoinDto> internalTransferJoinDto,int internalTransferId,int hotelId){
		List<InternalTransferJoin> list = new ArrayList<InternalTransferJoin>();
		for(InternalTransferJoinDto dto :internalTransferJoinDto) {
			list.add(InternalTransferJoin.builder()
					.id(dto.getId())
					.internalTransfer(new InternalTransfer(internalTransferId))
					.rawMatrialName(dto.getRawMatrialName())
					.quantity(dto.getQuantity())
					//.price(dto.getPrice())
//					.totalAmount(dto.getTotalAmount())
					.units(new Units(dto.getUnitsId()))
//					.cgst(dto.getCgst())
//					.sgst(dto.getSgst())
//					.igst(dto.getIgst())
//					.description(dto.getDescription())
					//.hasExpiryDate(dto.getHasExpiryDate())
					.expiryDate(dto.getExpiryDate())
					.isActive(dto.getIsActive())
					.hotel(new Hotel(hotelId))
					.build());
		}
		return list;
	}
	
	
	public List<InternalTransferJoinDto> getInternalTransferJoinDtoBuilder(List<InternalTransferJoin> internalTransferJoin){
		List<InternalTransferJoinDto> list = new ArrayList<InternalTransferJoinDto>();
		for(InternalTransferJoin dto :internalTransferJoin) {
			list.add(InternalTransferJoinDto.builder()
					.id(dto.getId())
					.rawMatrialName(dto.getRawMatrialName())
					.quantity(dto.getQuantity())
					//.price(dto.getPrice())
					//.totalAmount(dto.getTotalAmount())
					.unitsId(dto.getUnits().getId())
//					.cgst(dto.getCgst())
//					.sgst(dto.getSgst())
//					.igst(dto.getIgst())
//					.description(dto.getDescription())
					//.hasExpiryDate(dto.getHasExpiryDate())
					.expiryDate(dto.getExpiryDate())
					.isActive(dto.getIsActive())
					.build());
		}
		return list;
	}
	
	
	public InternalTransferDto getInternalTransferDtoBuilder(InternalTransfer internalTransfer,List<InternalTransferJoinDto> dto) {
		return InternalTransferDto.builder()
				.id(internalTransfer.getId())
				.kitchenId(internalTransfer.getKitchen().getId())
				.kitchenName(internalTransfer.getKitchen().getKitchenName())
				//.mrnNo(internalTransfer.getMrnNo())
				.invoiceDate(internalTransfer.getInvoiceDate())
				.invoiceNo(internalTransfer.getInvoiceNo())
//				.deliveryChargesInInvoice(internalTransfer.getDeliveryChargesInInvoice())
//				.cgst(internalTransfer.getCgst())
//				.sgst(internalTransfer.getSgst())
//				.igst(internalTransfer.getIgst())
//				.rawMatrialAmountTotal(internalTransfer.getRawMatrialAmountTotal())
//				.discountType(internalTransfer.getDiscountType())
//				.discountInInvoice(internalTransfer.getDiscountInInvoice())
//				.discountPer(internalTransfer.getDiscountPer())
//				.totalDeliveryCharges(internalTransfer.getTotalDeliveryCharges())
//				.totalDiscount(internalTransfer.getTotalDiscount())
//				.taxCollectedAtSource(internalTransfer.getTaxCollectedAtSource())
//				.totalTaxCollectedAtSource(internalTransfer.getTotalTaxCollectedAtSource())
//				.paymentType(internalTransfer.getPaymentType())
//				.taxPayableUnderReverseCharge(internalTransfer.getTaxPayableUnderReverseCharge())
//				.paidAmount(internalTransfer.getPaidAmount())
//				.paymentDate(internalTransfer.getPaymentDate())
//				.paymentReferenceNo(internalTransfer.getPaymentReferenceNo())
//				.paymentMode(internalTransfer.getPaymentMode())
//				.bankName(internalTransfer.getBankName())
//				.bankBranch(internalTransfer.getBankBranch())
//				.ifscCode(internalTransfer.getIfscCode())
//				.accountNo(internalTransfer.getAccountNo())
//				.address(internalTransfer.getAddress())
//				.termsAndConditions(internalTransfer.getTermsAndConditions())
//				.updateInventoryStock(internalTransfer.getUpdateInventoryStock())
//				.editable(internalTransfer.getEditable())
//				.subTotal(internalTransfer.getSubTotal())
//				.grandTotal(internalTransfer.getGrandTotal())
				.isActive(internalTransfer.getIsActive())
				.internalTransferJoinDtos(dto)
				.build();
	}
	
	
	public List<InternalTransferDto> getInternalTransferDto(List<InternalTransfer> internalTransfers){
		List<InternalTransferDto> list=new ArrayList<InternalTransferDto>();
		for(InternalTransfer internalTransfer :internalTransfers) {
			list.add(InternalTransferDto.builder()
					.id(internalTransfer.getId())
					.kitchenId(internalTransfer.getKitchen().getId())
					.kitchenName(internalTransfer.getKitchen().getKitchenName())
					//.mrnNo(internalTransfer.getMrnNo())
					.invoiceDate(internalTransfer.getInvoiceDate())
					.invoiceNo(internalTransfer.getInvoiceNo())
//					.deliveryChargesInInvoice(internalTransfer.getDeliveryChargesInInvoice())
//					.cgst(internalTransfer.getCgst())
//					.sgst(internalTransfer.getSgst())
//					.igst(internalTransfer.getIgst())
//					.discountType(internalTransfer.getDiscountType())
//					.discountInInvoice(internalTransfer.getDiscountInInvoice())
//					.discountPer(internalTransfer.getDiscountPer())
//					.totalDeliveryCharges(internalTransfer.getTotalDeliveryCharges())
//					.totalDiscount(internalTransfer.getTotalDiscount())
//					.taxCollectedAtSource(internalTransfer.getTaxCollectedAtSource())
//					.totalTaxCollectedAtSource(internalTransfer.getTotalTaxCollectedAtSource())
//					.paymentType(internalTransfer.getPaymentType())
//					.taxPayableUnderReverseCharge(internalTransfer.getTaxPayableUnderReverseCharge())
//					.paidAmount(internalTransfer.getPaidAmount())
//					.paymentDate(internalTransfer.getPaymentDate())
//					.paymentReferenceNo(internalTransfer.getPaymentReferenceNo())
//					.paymentMode(internalTransfer.getPaymentMode())
//					.bankName(internalTransfer.getBankName())
//					.bankBranch(internalTransfer.getBankBranch())
//					.ifscCode(internalTransfer.getIfscCode())
//					.accountNo(internalTransfer.getAccountNo())
//					.address(internalTransfer.getAddress())
//					.termsAndConditions(internalTransfer.getTermsAndConditions())
//					.updateInventoryStock(internalTransfer.getUpdateInventoryStock())
//					.editable(internalTransfer.getEditable())
//					.subTotal(internalTransfer.getSubTotal())
//					.grandTotal(internalTransfer.getGrandTotal())
					.isActive(internalTransfer.getIsActive())
					.build());
		}
		
		return list;
	}

}
