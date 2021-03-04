package com.sunan.expenseType;

import org.springframework.stereotype.Component;

import com.sunan.model.ExpenseType;

@Component
public class ExpenseTypeMapper {
	
	
	public ExpenseType getExpenseTypeBuilder(ExpenseTypeDto dto) {
		
		return ExpenseType.builder()
				.id(dto.getId())
				.name(dto.getName())
				.isActive(dto.getIsActive())
				.build();
	}
	
	public ExpenseTypeDto getExpenseTypeDtoBuilder(ExpenseType expenseType) {
		
		return ExpenseTypeDto.builder()
				.id(expenseType.getId())
				.name(expenseType.getName())
				.isActive(expenseType.getIsActive())
				.build();
	}

}
