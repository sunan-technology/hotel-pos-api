package com.sunan.order.kot.temp.info;

import org.springframework.stereotype.Component;

import com.sunan.model.HotelTable;
import com.sunan.model.TempOrderInfoKOT;
import com.sunan.model.Waiter;

@Component
public class TempOrderedInfoKOTMapper {
	
	
	public TempOrderInfoKOT tempOrderInfoKOTBuilder (TempOrderInfoKOTDto dto) {
		
		return TempOrderInfoKOT.builder()
				.id(dto.getId())
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
	
	
	
	public TempOrderInfoKOTDto tempOrderInfoKOTDtoBuilder(TempOrderInfoKOT tempOrderInfoKOT) {
		
		
		return TempOrderInfoKOTDto.builder()
				.id(tempOrderInfoKOT.getId())
				.ticketNo(tempOrderInfoKOT.getTicketNo())
				.billDate(tempOrderInfoKOT.getBillDate())
				.grandTotal(tempOrderInfoKOT.getGrandTotal())
				.tableNo(tempOrderInfoKOT.getHotelTable().getTableNo())
				.groupName(tempOrderInfoKOT.getGroupName())
				.operator(tempOrderInfoKOT.getOperator())
				.ticketNote(tempOrderInfoKOT.getTicketNote())
				.waiterId(tempOrderInfoKOT.getWaiter().getId())
				.kotStatus(tempOrderInfoKOT.getKotStatus())
				.isEditable(tempOrderInfoKOT.getIsEditable())
				.kotType(tempOrderInfoKOT.getKotType())
				.isActive(tempOrderInfoKOT.getIsActive())
				.build();
	}

}
