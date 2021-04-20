package com.sunan.unit;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UnitsDto {
	
	private int id;
	private String name;
	private Date createdDate;
	private String isActive;

}
