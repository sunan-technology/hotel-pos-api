package com.sunan.purchase;

import java.util.Date;
import java.util.List;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sunan.purchase.join.PurchaseJoinDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseDto {

	/*
	 * private int id; private String invoiceNo; private Date invoiceDate; private
	 * String purchasetype; private String remarks; private int supplierId;
	 * 
	 * private int productId; private String productName; private int warehousesId;
	 * private String warehousesName; private int quantity; private Double
	 * pricePerUnit; private Double totalAmount; private int hasExpiryDate; private
	 * Date expriyDate;
	 * 
	 * private Double subTotal; private Double discount; private Double
	 * freightCharges; private Double otherCharges; private Double previousDue;
	 * private Double total; private Double grandTotal; private Double totalPayment;
	 * private String isActive;
	 */

	private int id;

	private String invoiceNo;

	@JsonFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date invoiceDate;

	private String purchaseType;

	private int supplierId;
	
	private String supplierName;

	private Double subTotal;

	private Double discountPer;

	private Double discount;
	
	private String poNumber;


	private Double rawMatrialAmountTotal;

	private Double roundOff;

	private Double grandTotal;

	

	private String gstNo;

	private Double deliveryChargesInInvoice;

	private Double totalDeliveryCharges;

	private Double totalDiscount;

	private String discountType;

	private String paymentType; // paid,unpaid

	private String paymentMode; // cash,card,cheque,online,other

	private Double paidAmount;

	@JsonFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date paymentDate;

	private String paymentReferenceNo;

	private Double taxCollectedAtSource;
	
	private Double totalTaxCollectedAtSource;
	

//	private int cgst;
//
//	private int sgst;
//
//	private int igst;
	
	private int taxesId;
	
	private String taxName;

	private String isActive;
	
	List<PurchaseJoinDto> purchaseJoinDtos;

}
