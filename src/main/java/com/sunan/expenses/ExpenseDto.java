package com.sunan.expenses;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseDto {

	private int id;

	private Double amount;

	private Date expenseDate;

	private int expenseTypeId;
	
	private String expenseType;

	private String isActive;

}
