package com.sunan.order.kot.info;

import org.springframework.stereotype.Component;

import com.sunan.model.Hotel;
import com.sunan.model.HotelTable;
import com.sunan.model.OrderInfoKOT;
import com.sunan.model.TempOrderInfoKOT;
import com.sunan.model.Waiter;
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
				.waiter(new Waiter(dto.getWaiterId()))
				.kotStatus(dto.getKotStatus())
				.isEditable(dto.getIsEditable())
				.kotType(dto.getKotType())
				.isActive(dto.getIsActive())
				.build();
	}

	public OrderInfoKOT getOrderInfoKOT(TempOrderInfoKOT dto) {
		return OrderInfoKOT.builder()
				.ticketNo(dto.getTicketNo())
				.billDate(dto.getBillDate())
				.grandTotal(dto.getGrandTotal())
				.hotelTable(new HotelTable(dto.getHotelTable().getId()))
				.groupName(dto.getGroupName())
				.operator(dto.getOperator())
				.ticketNote(dto.getTicketNote())
				.waiter(dto.getWaiter())
				.kotStatus(dto.getKotStatus())
				.isEditable(dto.getIsEditable())
				.kotType(dto.getKotType())
				.isTempInvoiceGenerated(null)
				.isActive(dto.getIsActive())
				.waiter(dto.getWaiter())
				.hotel(new Hotel(dto.getHotel().getId()))
				.build();
	}
	
}
