package com.sunan.product;

import org.springframework.stereotype.Component;

import com.sunan.model.Category;
import com.sunan.model.Product;

@Component
public class ProductMapper {
	
	public Product getProductBuilder(ProductDto dto) {
		
		return Product.builder()
				.id(dto.getId())
				.productCode(dto.getProductCode())
				.productName(dto.getProductName())
				.category(new Category(dto.getCategoryId()))
				.description(dto.getDescription())
				.unit(dto.getUnit())
				.price(dto.getPrice())
				.reorderPoint(dto.getReorderPoint())
				.isActive(dto.getIsActive())
				.build();
	}

	public ProductDto getProductDtoBuilder(Product product) {
		
		return ProductDto.builder()
				.id(product.getId())
				.productCode(product.getProductCode())
				.productName(product.getProductName())
				.categoryId(product.getCategory().getId())
				.categoryName(product.getCategory().getCategoryName())
				.description(product.getDescription())
				.unit(product.getUnit())
				.price(product.getPrice())
				.reorderPoint(product.getReorderPoint())
				.isActive(product.getIsActive())
				.build();
	}
	
	
}
