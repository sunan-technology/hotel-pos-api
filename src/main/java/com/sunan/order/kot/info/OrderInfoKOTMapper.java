package com.sunan.order.kot.info;

import org.springframework.stereotype.Component;

import com.sunan.model.HotelTable;
import com.sunan.model.OrderInfoKOT;
import com.sunan.order.kot.temp.info.TempOrderInfoKOTDto;

@Component
public class OrderInfoKOTMapper {
	
	public OrderInfoKOT getOrderInfoKOTBuilder(TempOrderInfoKOTDto dto) {
		return OrderInfoKOT.builder()
				.ticketNo(dto.getTicketNo())
				.billDate(dto.getBillDate())
				.grandTotal(dto.getGrandTotal())
				.hotelTable(new HotelTable(dto.getTableNo()))
				.groupName(dto.getGroupName())
				.operator(dto.getOperator())
				.ticketNote(dto.getTicketNote())
				.waiter(dto.getWaiter())
				.kotStatus(dto.getKotStatus())
				.isEditable(dto.getIsEditable())
				.kotType(dto.getKotType())
				.isTempInvoiceGenerated(dto.getIsTempInvoiceGenerated())
				.isActive(dto.getIsActive())
				.build();
	}

}
