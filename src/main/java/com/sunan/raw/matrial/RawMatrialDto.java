package com.sunan.raw.matrial;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RawMatrialDto {

	private int id;

	private String name;

	private String purchaseUnit;

	private String consumptionUnit;

	private Double purchasePrice;

	private Double salePrice;

	private String taxType; // GST/VAT

	private Double taxAmount; // GST(%) /ExciseDuty(amount)

//	private int gst;
//	
//	private Double exciseDuty;

	private int categoryId;

	private String categoryName;

	private Double normallLoss;

	private String hsnCode;

	private Double ministockLevel;

	private String ministocklevelUnit;

	private Double atperstockLevel;

	private String atperstocklevelUnit;

	private String description;

	private String closingStockCalculation; // daily,weekly

	private String isPrivate; // yes/no

	private String isExpiry; // yes/no

	private String isActive;

}
