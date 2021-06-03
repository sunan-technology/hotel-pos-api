package com.sunan.table;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sunan.model.Hotel;
import com.sunan.model.HotelTable;
import com.sunan.model.TempOrderInfoKOT;
import com.sunan.order.kot.temp.info.TempOrderInfoKOTRepository;
import com.sunan.utils.JsonUtils;

@Service
public class HotelTableService implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(HotelTableService.class);

	@Autowired
	private HotelTableRepository tableRepository;
	
	@Autowired
	private TempOrderInfoKOTRepository tempOrderInfoKOTRepository;

	@Autowired
	HotelTableMapper tableMapper;

	@Autowired
	private JsonUtils utils;

	

	@Transactional
	public String saveTable(List<HotelTableDto> dto,int hotelId) {
		
		
		if (tableStatusRequestValidate(dto)) {
			if (tableTypeRequestValidate(dto)) {
				List<HotelTable> table = tableMapper.getHotelTableBuilder(dto,hotelId);
				tableRepository.saveAll(table);
				logger.info("Service: Save Table details");
				return utils.objectMapperSuccess(tableMapper.getHotelTableDtoBuilder(table), "Table Details Saved");
			} else {
				logger.info("Service : Table save opration failed ! ");
				return utils.objectMapperError("Table save opration failed due to wrong tableType(add-AC/NonAc)");
			}
		} else {
			logger.info("Service : Table save opration failed ! ");
			return utils.objectMapperError("Table save opration failed due to wrong status(add-active/deactive)");
		}
	}

	@Transactional
	public String update(List<HotelTableDto> tableDto, int id,int hotelId) {

		if (tableStatusRequestValidate(tableDto)) {
			if (tableTypeRequestValidate(tableDto)) {
				logger.info("Service: Update table details with id {}", id);
				Optional<HotelTable> optional = tableRepository.findById(id);
				if (optional.isPresent()) {
					logger.info("Service: table details found with id {} for update operation", id);
					List<HotelTable> table = tableMapper.getHotelTableBuilder(tableDto,hotelId);
					tableRepository.saveAll(table);
					return utils.objectMapperSuccess(tableMapper.getHotelTableDtoBuilder(table), "Table Details Updated");
				} else {
					logger.info("Service: table details not found with id {} for update operation", id);
					return utils.objectMapperError("table Details Not Found !");
				}
			} else {
				logger.info("Service : Table update opration failed ! ");
				return utils.objectMapperError("Table update opration failed due to wrong tableType(add-AC/NonAc)");
			}

		} else {
			logger.info("Service : Table update opration failed ! ");
			return utils.objectMapperError("Table update opration failed due to wrong status(add-active/deactive)");
		}
	}

	@Transactional
	public String delete(int id) {
		logger.info("Service: Delete table details with id {}", id);
		int isDelete = tableRepository.updateIsActiveStatus(id);
		if (isDelete > 0) {
			logger.info("Service: table details found with id {} for delete operation{}", id);
			return utils.objectMapperSuccess("Table Deleted Successfully");
		}
		logger.info("Service: table details not found with id {} for delete operation{}", id);
		return utils.objectMapperError("Table Deleted Failed");
	}

	@Transactional
	public String getById(int id) {
		logger.info("Service: Fetching table details with id {}", id);
		Optional<HotelTable> table = tableRepository.findById(id);
		if (table.isPresent()) {
			logger.info("Service: table details found with id {}", id);
			HotelTableDto dto = tableMapper.getTableDtoBuilder(table.get());
			return utils.objectMapperSuccess(dto, "Table Details");
		}
		logger.info("Service: table details not found with id {}", id);
		return utils.objectMapperError("Table Details Not found, Id :" + id);
	}

	@Transactional
	public String findActiveList(String searchTerm, Integer pageNo, Integer pageSize, String sortBy) {
		logger.info("Service: Fetching list of hotelTable details ");
		PageRequest pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<HotelTable> pagedResult = null;
		
			pagedResult = tableRepository.findByIsActive("yes", pageable);
		

		Page<HotelTableDto> page = pagedResult.map(new Function<HotelTable, HotelTableDto>() {
			@Override
			public HotelTableDto apply(HotelTable entity) {
				HotelTableDto dto = tableMapper.getTableDtoBuilder(entity);
				return dto;
			}
		});
		logger.info("Service: Fetching list of tabl details, total records: {}", page.getTotalElements());
		return utils.objectMapperSuccess(page, "All Acive Table list.");
	}

	public boolean tableStatusRequestValidate(List<HotelTableDto> hotelTableDto) {
		for(HotelTableDto tableDto : hotelTableDto) {
		if (tableDto.getStatus().equals("active") || tableDto.getStatus().equals("deactive")) {
			return true;
		} else {
			return false;
		}
		}
		return false;
	}

	public boolean tableTypeRequestValidate(List<HotelTableDto> hotelTableDto) {
		for(HotelTableDto dto : hotelTableDto) {
		if (dto.getTableType().equals("AC") || dto.getTableType().equals("NonAc")) {
			return true;
		} else {
			return false;
		}
		}
		return false;
	}

	public String getAvailableTable(int hotelId) {
		
		List<AvailableTableDto> result = new ArrayList<>();
		List <HotelTable> Tablelist = tableRepository.findByHotel(new Hotel(hotelId));
		for (HotelTable hotelTable : Tablelist) {
			
			AvailableTableDto availableTableDto = new AvailableTableDto();
			availableTableDto.setId(hotelTable.getId());
			availableTableDto.setTableName(String.valueOf(hotelTable.getTableNo()));
			
			TempOrderInfoKOT optional = tempOrderInfoKOTRepository.findByHotelTableAndHotel(hotelTable, new Hotel(hotelId));
			if(optional!= null) {
				availableTableDto.setAvailableStatus("No");
			}else {
				availableTableDto.setAvailableStatus("yes");
			}
			result.add(availableTableDto);
		} 
		
		return utils.objectMapperSuccess(result, "All available Table list.");
	}

}
