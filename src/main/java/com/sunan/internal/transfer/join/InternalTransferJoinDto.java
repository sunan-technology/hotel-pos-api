package com.sunan.internal.transfer.join;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InternalTransferJoinDto {

	private int id;

//	private int internalTransferId;
	private int purchaseJoinId;
	
//	private int rawMatrialId;
	
	private String rawMatrialName;

	private int quantity;

//	private Double price;
//
//	private Double totalAmount;
//
	private int unitsId;

//	private int cgst;
//
//	private int sgst;
//
//	private int igst;
//
//	private String description;

//	private int hasExpiryDate;

	private Date expiryDate;

	private String isActive;

}
