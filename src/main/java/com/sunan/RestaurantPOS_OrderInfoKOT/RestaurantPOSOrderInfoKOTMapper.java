package com.sunan.RestaurantPOS_OrderInfoKOT;

import org.springframework.stereotype.Component;

import com.sunan.TempRestaurantPOS_OrderInfoKOT.TempRestaurantPOSOrderInfoKOTDto;
import com.sunan.model.HotelTable;
import com.sunan.model.RestaurantPOSOrderInfoKOT;

@Component
public class RestaurantPOSOrderInfoKOTMapper {
	
	public RestaurantPOSOrderInfoKOT getRestaurantPOSOrderInfoKOTBuilder(TempRestaurantPOSOrderInfoKOTDto dto) {
		return RestaurantPOSOrderInfoKOT.builder()
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
