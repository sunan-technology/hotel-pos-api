package com.sunan.expenseType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseTypeDto {
	
	private int id;
	private String name;
	private String isActive;


}
