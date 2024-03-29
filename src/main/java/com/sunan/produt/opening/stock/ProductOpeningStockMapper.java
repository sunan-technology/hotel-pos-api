package com.sunan.produt.opening.stock;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.sunan.model.Hotel;
import com.sunan.model.ProductOpeningStock;
import com.sunan.model.Warehouses;

@Component
public class ProductOpeningStockMapper {

	public ProductOpeningStock getProductOpeningStockBuilder(ProductOpeningStockDto dto) {

		return ProductOpeningStock.builder(
				).id(dto.getId())
				.warehouses(new Warehouses(dto.getWarehousesId()))
				.quantity(dto.getQuantity())
				.hasExpiryDate(dto.getHasExpriyDate()).expiryDate(dto.getExpiryDate())
				.isActive(dto.getIsActive()).build();
	}

	public List<ProductOpeningStock> getProductOpeningStockBuilder(List<ProductOpeningStockDto> openingStockDtos, int productId, int hotelId) {
		List<ProductOpeningStock> list = new ArrayList<ProductOpeningStock>();
		for (ProductOpeningStockDto dto : openingStockDtos) {
			list.add(ProductOpeningStock.builder()
					.id(dto.getId())
					.warehouses(new Warehouses(dto.getWarehousesId()))
					.quantity(dto.getQuantity())
					.hasExpiryDate(dto.getHasExpriyDate())
					.expiryDate(dto.getExpiryDate())
					.isActive(dto.getIsActive())
					.hotel(new Hotel(hotelId))
					.build());
		}
		return list;
	}

	public ProductOpeningStockDto getProductOpeningStockDtoBuilder(ProductOpeningStock productOpeningStock) {

		return ProductOpeningStockDto.builder().id(productOpeningStock.getId())
				.warehousesId(productOpeningStock.getWarehouses().getId())
				.warehousesName(productOpeningStock.getWarehouses().getWarehouseName())
				.quantity(productOpeningStock.getQuantity()).hasExpriyDate(productOpeningStock.getHasExpiryDate())
				.expiryDate(productOpeningStock.getExpiryDate()).isActive(productOpeningStock.getIsActive()).build();
	}

}
