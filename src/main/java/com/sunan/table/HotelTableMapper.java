package com.sunan.table;

import org.springframework.stereotype.Component;

import com.sunan.model.HotelTable;

@Component
public class HotelTableMapper {
	
	
	public HotelTable getTableBuilder(HotelTableDto dto) {
		
		return HotelTable.builder()
				.tableNo(dto.getTableNo())
				.status(dto.getStatus())
				.tableType(dto.getTableType())
				.floorNo(dto.getFloorNo())
				.isActive(dto.getIsActive())
				.build();
	}
	
	
	public HotelTableDto getTableDtoBuilder(HotelTable table) {
		
		return HotelTableDto.builder()
				.tableNo(table.getTableNo())
				.status(table.getStatus())
				.tableType(table.getTableType())
				.floorNo(table.getFloorNo())
				.isActive(table.getIsActive())
				.build();
	}

}
