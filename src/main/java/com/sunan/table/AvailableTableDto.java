package com.sunan.table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AvailableTableDto {
	
	private int id;
	private String tableName;
	private String availableStatus;

}
