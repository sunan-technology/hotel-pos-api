package com.sunan.order.kot.temp.info;

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
public class TempOrderInfoKOTDto {

	@NotNull(message = "Id can not be null")
	private int id;

	private String ticketNo;

	private Date billDate;

	private Double grandTotal;

	private int tableNo;

	private String groupName;

	private String operator;

	private String ticketNote;

	private int  waiterId;

	private String kotStatus;

	private String isEditable;

	private String kotType;

	private String isActive;

	public List<DishKOTDto> dish;

//	private Double totalAmount;
	
	//private String isTempInvoiceGenerated;

	

}

