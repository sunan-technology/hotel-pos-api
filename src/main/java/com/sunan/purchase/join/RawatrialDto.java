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
public class RawatrialDto {
	
	private int purchaseJoinId;
	private String rawmatrialName;
	private Date purchaseDate;
	private Date expriryDate;
	private int quantity;

}
