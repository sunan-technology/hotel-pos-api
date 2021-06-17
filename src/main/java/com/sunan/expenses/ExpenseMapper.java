package com.sunan.expenses;

import org.springframework.stereotype.Component;

import com.sunan.model.Expense;
import com.sunan.model.ExpenseType;

@Component
public class ExpenseMapper {
	
	public Expense getExpenseBuilder(ExpenseDto dto) {
		
		return Expense.builder()
				.id(dto.getId())
				.amount(dto.getAmount())
				.expenseDate(dto.getExpenseDate())
				.expenseType(new ExpenseType(dto.getExpenseTypeId()))
				.isActive(dto.getIsActive())
				.build();
	}
	
	
	public ExpenseDto getExpenseDtoBuilder(Expense dto) {
		return ExpenseDto.builder()
				.id(dto.getId())
				.amount(dto.getAmount())
				.expenseDate(dto.getExpenseDate())
				.expenseTypeId(dto.getExpenseType().getId())
				.expenseType(dto.getExpenseType().getName())
				.isActive(dto.getIsActive())
				.build();
	}

}
