package com.sunan.category;

import org.springframework.stereotype.Component;

import com.sunan.model.Category;
import com.sunan.model.Taxes;

@Component
public class CategoryMapper {
	
	
	public Category getCategoryBuilder(CategoryDto dto) {
		
		return Category.builder()
				.id(dto.getId())
				.categoryName(dto.getCategoryName())
				.taxes(new Taxes(dto.getTaxId()))
//				.vat(dto.getVat())
//				.sc(dto.getSc())
//				.st(dto.getSt())
				.isActive(dto.getIsActive())
				.build();
	}
	
	
	
	public CategoryDto getCategoryDtoBuilder(Category category) {
		
		
		return CategoryDto.builder()
				.id(category.getId())
				.categoryName(category.getCategoryName())
				.taxId(category.getTaxes().getId())
				.taxName(category.getTaxes().getTaxName())
//				.vat(category.getVat())
//				.sc(category.getSc())
//				.st(category.getSt())
				.isActive(category.getIsActive())
				.build();
	}

}
