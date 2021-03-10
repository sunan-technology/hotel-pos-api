package com.sunan.produt_openingStock;

import org.springframework.stereotype.Component;

import com.sunan.model.Product;
import com.sunan.model.Product_OpeningStock;
import com.sunan.model.StorageType;
import com.sunan.model.Warehouses;

@Component
public class Product_OpeningStockMapper {
	
	public Product_OpeningStock getProductOpeningStockBuilder(Product_OpeningStockDto dto) {
		
		return Product_OpeningStock.builder()
				.id(dto.getId())
				.product(new Product(dto.getProductId()))
				.storageType(new StorageType(dto.getStorageTypeId()))
				.warehouses(new Warehouses(dto.getWarehousesId()))
				.quantity(dto.getQuantity())
				.hasExpiryDate(dto.getHasExpriyDate())
				.expiryDate(dto.getExpiryDate())
				.isActive(dto.getIsActive())
				.build();
	}
	
	
	public Product_OpeningStockDto getProductOpeningStockDtoBuilder(Product_OpeningStock productOpeningStock) {
		
		return Product_OpeningStockDto.builder()
				.id(productOpeningStock.getId())
				.productId(productOpeningStock.getProduct().getId())
				.productName(productOpeningStock.getProduct().getProductName())
				.storageTypeId(productOpeningStock.getStorageType().getId())
				.storageTypeName(productOpeningStock.getStorageType().getName())
				.warehousesId(productOpeningStock.getWarehouses().getId())
				.warehousesName(productOpeningStock.getWarehouses().getWarehouseName())
				.quantity(productOpeningStock.getQuantity())
				.hasExpriyDate(productOpeningStock.getHasExpiryDate())
				.expiryDate(productOpeningStock.getExpiryDate())
				.isActive(productOpeningStock.getIsActive())
				.build();
	}

}
