package com.sunan.TempRestaurantPOS_OrderInfoKOT;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TempRestaurantPOSOrderInfoKOTDto {

	@NotNull(message = "Id can not be null")
	private int id;

	private String ticketNo;

	private Date billDate;

	private Double grandTotal;

	private String tableNo;

	private String groupName;

	private String operator;

	private String ticketNote;

	private String waiter;

	private String kotStatus;

	private String isEditable;

	private String kotType;

	private String isActive;

	public List<DishKOTDto> dish;

	private Double totalAmount;

	

}

