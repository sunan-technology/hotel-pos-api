package com.sunan.inventory.setting;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InventorySettingDto {

	public int id;

	private String autoConsumption;

	private String alertKitchenWhenItemReachesAtPerStockLevel;

	private String shipBillToConfiguration;

	private String revertConsumptionDueToCancelOfOnlineOrder;

	private String enableCessTaxInInvoices;

	private String allowUserToRaiseInternalSaleStockAtTheKitchenNegativeLevel;

	private String putItemAsOutOfStockWhenItemQuantityGetBelowMinimumStockLevel;

	private String setInvoiceAmountInRoundOff;

	private String displayPurchasePriceAsInternalSale;

	private String getNotificationWhenItemReachesAtPerStockLevel;

	private String allowUserToRaiseRequestForPurchaseWhenStockAtKitchenLevelIsNegative;

	private String salesInvoiceLabel;

	private String purchaseInvoiceLabel;

}
