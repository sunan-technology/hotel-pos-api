package com.sunan.table;

import java.io.Serializable;
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
import com.sunan.utils.JsonUtils;

@Service
public class HotelTableService implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(HotelTableService.class);

	@Autowired
	private HotelTableRepository tableRepository;

	@Autowired
	HotelTableMapper tableMapper;

	@Autowired
	private JsonUtils utils;

	

	@Transactional
	public String saveTable(HotelTableDto dto,int hotelId) {
		if (tableStatusRequestValidate(dto)) {
			if (tableTypeRequestValidate(dto)) {
				HotelTable table = tableMapper.getTableBuilder(dto);
				table.setHotel(new Hotel(hotelId));
				tableRepository.save(table);
				logger.info("Service: Save Table details");
				return utils.objectMapperSuccess(tableMapper.getTableDtoBuilder(table), "Table Details Saved");
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
	public String update(HotelTableDto tableDto, int id,int hotelId) {

		if (tableStatusRequestValidate(tableDto)) {
			if (tableTypeRequestValidate(tableDto)) {
				logger.info("Service: Update table details with id {}", id);
				Optional<HotelTable> optional = tableRepository.findById(id);
				if (optional.isPresent()) {
					logger.info("Service: table details found with id {} for update operation", id);
					HotelTable table = tableMapper.getTableBuilder(tableDto);
					table.setHotel(new Hotel(hotelId));
					tableRepository.save(table);
					return utils.objectMapperSuccess(tableMapper.getTableDtoBuilder(table), "Table Details Updated");
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
		if (StringUtils.isBlank(searchTerm)) {
			pagedResult = tableRepository.findByIsActive("yes", pageable);
		} else {
			pagedResult = tableRepository.findByStatusContainingIgnoreCaseAndIsActive(searchTerm, "yes", pageable);
		}

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

	public boolean tableStatusRequestValidate(HotelTableDto dto) {
		if (dto.getStatus().equals("active") || dto.getStatus().equals("deactive")) {
			return true;
		} else {
			return false;
		}
	}

	public boolean tableTypeRequestValidate(HotelTableDto dto) {
		if (dto.getTableType().equals("AC") || dto.getTableType().equals("NonAc")) {
			return true;
		} else {
			return false;
		}
	}

}
