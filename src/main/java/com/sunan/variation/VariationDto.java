package com.sunan.variation;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VariationDto {
	private int id;

	private String name;

	private String status;
	
	private Date createdDate;

}
