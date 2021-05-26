package com.sunan.purchase.join;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseDetailsDto {
	
	private int purchaseId;
	private Date purchaseDate;
	private int quantity;

}
