package com.sunan.order.kot.info;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderInfoKOTDto {

	private int id;

	private String ticketNo;

	private Date billDate;

	private Double grandTotal;

	private int tableNo;

	private String groupName;

	private String operator;

	private String ticketNote;

	private String waiter;

	private String kotStatus;

	private String isEditable;

	private String kotType;
	
	

	private String isActive;

}
