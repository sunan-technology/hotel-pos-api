package com.sunan.raw.matrial;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.sunan.model.Category;
import com.sunan.model.Hotel;
import com.sunan.model.RawMatrial;
import com.sunan.model.RecipeRawMatrial;
import com.sunan.model.Recipe;
import com.sunan.model.Units;

@Component
public class RawMatrialMapper {
	
	public RawMatrial getRawMatrialBuilder(RawMatrialDto dto) {
		
		Double gst,exciseDuty;
		if(dto.getTaxType().equals("GST")) {
			 gst= dto.getTaxAmount();
			exciseDuty=0.0;
		}else {
			 gst= 0.0;
				exciseDuty= dto.getTaxAmount();
		}
		return RawMatrial.builder()
				.id(dto.getId())
				.name(dto.getName())
				.purchaseUnit(dto.getPurchaseUnit())
				.purchasePrice(dto.getPurchasePrice())
				.consumptionUnit(dto.getConsumptionUnit())
				.salePrice(dto.getSalePrice())
				.taxType(dto.getTaxType())
				.gst(gst)
				.exciseDuty(exciseDuty)
				.category(new Category(dto.getCategoryId()))
				.normallLoss(dto.getNormallLoss())
				.hsnCode(dto.getHsnCode())
				.ministockLevel(dto.getMinistockLevel())
				.ministocklevelUnit(dto.getMinistocklevelUnit())
				.atperstockLevel(dto.getAtperstockLevel())
				.atperstocklevelUnit(dto.getAtperstocklevelUnit())
				.description(dto.getDescription())
				.closingStockCalculation(dto.getClosingStockCalculation())
				.isPrivate(dto.getIsPrivate())
				.isExpiry(dto.getIsExpiry())
				.isActive(dto.getIsActive())
				.build();
	}
	
	public RawMatrialDto getRowMatrialDtoBuilder(RawMatrial rawmatrial) {
		
		Double taxAmount;
		if(rawmatrial.getTaxType().equals("GST")) {
			taxAmount=rawmatrial.getGst();
		}else {
			taxAmount=rawmatrial.getExciseDuty();
		}
		
		return RawMatrialDto.builder()
				.id(rawmatrial.getId())
				.name(rawmatrial.getName())
				.purchaseUnit(rawmatrial.getPurchaseUnit())
				.purchasePrice(rawmatrial.getPurchasePrice())
				.consumptionUnit(rawmatrial.getConsumptionUnit())
				.salePrice(rawmatrial.getSalePrice())
				.taxType(rawmatrial.getTaxType())
				.taxAmount(taxAmount)
				.categoryId(rawmatrial.getCategory().getId())
				.categoryName(rawmatrial.getCategory().getCategoryName())
				.normallLoss(rawmatrial.getNormallLoss())
				.hsnCode(rawmatrial.getHsnCode())
				.ministockLevel(rawmatrial.getMinistockLevel())
				.ministocklevelUnit(rawmatrial.getMinistocklevelUnit())
				.atperstockLevel(rawmatrial.getAtperstockLevel())
				.atperstocklevelUnit(rawmatrial.getAtperstocklevelUnit())
				.description(rawmatrial.getDescription())
				.closingStockCalculation(rawmatrial.getClosingStockCalculation())
				.isPrivate(rawmatrial.getIsPrivate())
				.isExpiry(rawmatrial.getIsExpiry())
				.isActive(rawmatrial.getIsActive())
				.build();
	}
	
	
public List<RecipeRawMatrial> getRawMatrialRequest(List<RecipeRawMatrialDto> recipeRawMatrialDtos,int recipeId,int hotelId){
	List<RecipeRawMatrial> list = new ArrayList<RecipeRawMatrial>();
	for(RecipeRawMatrialDto dto : recipeRawMatrialDtos) {
		list.add(RecipeRawMatrial.builder()
				.id(dto.getId())
				.name(dto.getName())
				.quantity(dto.getQuantity())
				.units(new Units(dto.getUnitId()))
				.area(dto.getArea())
				.recipe(new Recipe(recipeId))
				.isActive("yes")
				.hotel(new Hotel(hotelId))
				.build());
	}
	return list;
}

public List<RecipeRawMatrialDto> getRawMatrialRequestDto(List<RecipeRawMatrial> recipeRawMatrial){
	List<RecipeRawMatrialDto> list = new ArrayList<RecipeRawMatrialDto>();
	for(RecipeRawMatrial dto :recipeRawMatrial) {
		list.add(RecipeRawMatrialDto.builder()
				.id(dto.getId())
				.name(dto.getName())
				.quantity(dto.getQuantity())
				.unitId(dto.getUnits().getId())
				.area(dto.getArea())
				.build());
	}
	return list;
}

}
