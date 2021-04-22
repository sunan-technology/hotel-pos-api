package com.sunan.table;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.sunan.model.Hotel;
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
	
	
	public List<HotelTable> getHotelTableBuilder(List<HotelTableDto> hotelTableDto,int hotelId){
		
		List<HotelTable> list=new ArrayList<HotelTable>();
		for(HotelTableDto dto : hotelTableDto) {
			list.add(HotelTable.builder()
					.tableNo(dto.getTableNo())
					.status(dto.getStatus())
					.tableType(dto.getTableType())
					.floorNo(dto.getFloorNo())
					.isActive(dto.getIsActive())
					.hotel(new Hotel(hotelId))
					.build());
		}
		
		return list;
	}
	
	public List<HotelTableDto> getHotelTableDtoBuilder(List<HotelTable> hotelTable){
		List<HotelTableDto> list=new ArrayList<HotelTableDto>();
		for(HotelTable table : hotelTable) {
			list.add(HotelTableDto.builder()
					.tableNo(table.getTableNo())
					.status(table.getStatus())
					.tableType(table.getTableType())
					.floorNo(table.getFloorNo())
					.isActive(table.getIsActive())
					.build());
		}
		return list;
	}

}
