package com.sunan.category;

import org.springframework.stereotype.Component;

import com.sunan.model.Category;

@Component
public class CategoryMapper {
	
	
	public Category getCategoryBuilder(CategoryDto dto) {
		
		return Category.builder()
				.id(dto.getId())
				.categoryName(dto.getCategoryName())
				.vat(dto.getVat())
				.sc(dto.getSc())
				.st(dto.getSt())
				.isActive(dto.getIsActive())
				.build();
	}
	
	
	
	public CategoryDto getCategoryDtoBuilder(Category category) {
		
		
		return CategoryDto.builder()
				.id(category.getId())
				.categoryName(category.getCategoryName())
				.vat(category.getVat())
				.sc(category.getSc())
				.st(category.getSt())
				.isActive(category.getIsActive())
				.build();
	}

}
