package com.sunan.internal.transfer;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sunan.internal.transfer.join.InternalTransferJoinDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InternalTransferDto {

	private int id;

	private int kitchenId;
	private String kitchenName;

//	private String mrnNo;

	@JsonFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date invoiceDate;

	private String invoiceNo;

//	private Double deliveryChargesInInvoice;
//
//	private int cgst;
//
//	private int sgst;
//
//	private int igst;
//	
//	private Double rawMatrialAmountTotal;
//
//	private String discountType;
//
//	private Double discountInInvoice;
//
//	private int discountPer;
//
//	private Double totalDeliveryCharges;
//
//	private Double totalDiscount;
//
//	private Double taxCollectedAtSource;
//
//	private Double totalTaxCollectedAtSource;
//
//	private String paymentType; // paid,unpaid
//
//	private String taxPayableUnderReverseCharge; // yes,no
//
//	private Double paidAmount;
//	
//
//	private Double subTotal;
//
//	private Double grandTotal;
//	
//
//	@JsonFormat(pattern = "yyyy-MM-dd")
//	@Temporal(TemporalType.DATE)
//	private Date paymentDate;
//
//	private String paymentReferenceNo;
//
//	private String paymentMode; // cash,card,cheque,online,other
//
//	private String bankName;
//
//	private String bankBranch;
//
//	private String ifscCode;
//
//	private String accountNo;
//
//	private String address;
//
//	private String termsAndConditions;
//
//	private String updateInventoryStock;
//
//	private String editable;

	private String isActive;

	List<InternalTransferJoinDto> internalTransferJoinDtos;

}
