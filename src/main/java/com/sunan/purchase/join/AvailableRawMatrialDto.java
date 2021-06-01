package com.sunan.purchase.join;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class AvailableRawMatrialDto {
	
	private Long quantity;
	private String rawMatrialName;
	private int rawMatrialId;
	public AvailableRawMatrialDto(Long quantity, String rawMatrialName, int rawMatrialId) {
		super();
		this.quantity = quantity;
		this.rawMatrialName = rawMatrialName;
		this.rawMatrialId = rawMatrialId;
	}
	
	
	
	
	
	
	

}
