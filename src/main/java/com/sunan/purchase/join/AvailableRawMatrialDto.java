package com.sunan.purchase.join;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AvailableRawMatrialDto {
	
	private int rawMatrialId;
	private String rawMatrialName;
	List<PurchaseDetailsDto> purchaseDetailsDto;

}
