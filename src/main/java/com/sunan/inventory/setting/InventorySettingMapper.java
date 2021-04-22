package com.sunan.inventory.setting;

import org.springframework.stereotype.Component;

import com.sunan.model.InventorySetting;

@Component
public class InventorySettingMapper {
	
	public InventorySetting getInventorySettingBuilder(InventorySettingDto dto) {
		
		return InventorySetting.builder()
				.id(dto.id)
				.autoConsumption(dto.getAutoConsumption())
				.alertKitchenWhenItemReachesAtPerStockLevel(dto.getAlertKitchenWhenItemReachesAtPerStockLevel())
				.shipBillToConfiguration(dto.getShipBillToConfiguration())
				.revertConsumptionDueToCancelOfOnlineOrder(dto.getRevertConsumptionDueToCancelOfOnlineOrder())
				.enableCessTaxInInvoices(dto.getEnableCessTaxInInvoices())
				.allowUserToRaiseInternalSaleStockAtTheKitchenNegativeLevel(dto.getAllowUserToRaiseInternalSaleStockAtTheKitchenNegativeLevel())
				.putItemAsOutOfStockWhenItemQuantityGetBelowMinimumStockLevel(dto.getPutItemAsOutOfStockWhenItemQuantityGetBelowMinimumStockLevel())
				.setInvoiceAmountInRoundOff(dto.getSetInvoiceAmountInRoundOff())
				.displayPurchasePriceAsInternalSale(dto.getDisplayPurchasePriceAsInternalSale())
				.getNotificationWhenItemReachesAtPerStockLevel(dto.getGetNotificationWhenItemReachesAtPerStockLevel())
				.allowUserRaiseRequestForPurchaseWhenStockAtKitchenIsNegative(dto.getAllowUserToRaiseRequestForPurchaseWhenStockAtKitchenLevelIsNegative())
				.salesInvoiceLabel(dto.getSalesInvoiceLabel())
				.purchaseInvoiceLabel(dto.getPurchaseInvoiceLabel())
				.build();
	}
	
	
	public InventorySettingDto getInventorySettingDtoBuilder(InventorySetting inventorySetting) {
		
		return InventorySettingDto.builder()
				.autoConsumption(inventorySetting.getAutoConsumption())
				.alertKitchenWhenItemReachesAtPerStockLevel(inventorySetting.getAlertKitchenWhenItemReachesAtPerStockLevel())
				.shipBillToConfiguration(inventorySetting.getShipBillToConfiguration())
				.revertConsumptionDueToCancelOfOnlineOrder(inventorySetting.getRevertConsumptionDueToCancelOfOnlineOrder())
				.enableCessTaxInInvoices(inventorySetting.getEnableCessTaxInInvoices())
				.allowUserToRaiseInternalSaleStockAtTheKitchenNegativeLevel(inventorySetting.getAllowUserToRaiseInternalSaleStockAtTheKitchenNegativeLevel())
				.putItemAsOutOfStockWhenItemQuantityGetBelowMinimumStockLevel(inventorySetting.getPutItemAsOutOfStockWhenItemQuantityGetBelowMinimumStockLevel())
				.setInvoiceAmountInRoundOff(inventorySetting.getSetInvoiceAmountInRoundOff())
				.displayPurchasePriceAsInternalSale(inventorySetting.getDisplayPurchasePriceAsInternalSale())
				.getNotificationWhenItemReachesAtPerStockLevel(inventorySetting.getGetNotificationWhenItemReachesAtPerStockLevel())
				.allowUserToRaiseRequestForPurchaseWhenStockAtKitchenLevelIsNegative(inventorySetting.getAllowUserRaiseRequestForPurchaseWhenStockAtKitchenIsNegative())
				.salesInvoiceLabel(inventorySetting.getSalesInvoiceLabel())
				.purchaseInvoiceLabel(inventorySetting.getPurchaseInvoiceLabel())
				.build();
	}

}
