package com.sunan.TempRestaurantPOS_OrderInfoKOT;

import org.springframework.stereotype.Component;

import com.sunan.model.TempRestaurantPOSOrderInfoKOT;

@Component
public class TempRestaurantPOSOrderedInfoKOTMapper {
	
	
	public TempRestaurantPOSOrderInfoKOT tempRestaurantPOSOrderInfoKOTBuilder (TempRestaurantPOSOrderInfoKOTDto dto) {
		
		return TempRestaurantPOSOrderInfoKOT.builder()
				.id(dto.getId())
				.ticketNo(dto.getTicketNo())
				.billDate(dto.getBillDate())
				.grandTotal(dto.getGrandTotal())
				.tableNo(dto.getTableNo())
				.groupName(dto.getGroupName())
				.operator(dto.getOperator())
				.ticketNote(dto.getTicketNote())
				.waiter(dto.getWaiter())
				.kotStatus(dto.getKotStatus())
				.isEditable(dto.getIsEditable())
				.kotType(dto.getKotType())
				.isActive(dto.getIsActive())
				.build();
		
	}
	
	
	
	public TempRestaurantPOSOrderInfoKOTDto tempRestaurantPOSOrderInfoKOTDtoBuilder(TempRestaurantPOSOrderInfoKOT tempRestaurantPOSOrderInfoKOT) {
		
		
		return TempRestaurantPOSOrderInfoKOTDto.builder()
				.id(tempRestaurantPOSOrderInfoKOT.getId())
				.ticketNo(tempRestaurantPOSOrderInfoKOT.getTicketNo())
				.billDate(tempRestaurantPOSOrderInfoKOT.getBillDate())
				.grandTotal(tempRestaurantPOSOrderInfoKOT.getGrandTotal())
				.tableNo(tempRestaurantPOSOrderInfoKOT.getTableNo())
				.groupName(tempRestaurantPOSOrderInfoKOT.getGroupName())
				.operator(tempRestaurantPOSOrderInfoKOT.getOperator())
				.ticketNote(tempRestaurantPOSOrderInfoKOT.getTicketNote())
				.waiter(tempRestaurantPOSOrderInfoKOT.getWaiter())
				.kotStatus(tempRestaurantPOSOrderInfoKOT.getKotStatus())
				.isEditable(tempRestaurantPOSOrderInfoKOT.getIsEditable())
				.kotType(tempRestaurantPOSOrderInfoKOT.getKotType())
				.isActive(tempRestaurantPOSOrderInfoKOT.getIsActive())
				.build();
	}

}
