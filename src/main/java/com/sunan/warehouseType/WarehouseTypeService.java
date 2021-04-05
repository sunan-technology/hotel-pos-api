package com.sunan.warehouseType;

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
import com.sunan.model.WarehouseType;
import com.sunan.utils.JsonUtils;

@Service
public class WarehouseTypeService implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(WarehouseTypeService.class);

	@Autowired
	private WarehouseTypeRepository warehouseTypeRepository;

	@Autowired
	WarehouseTypeMapper warehouseTypeMapper;

	@Autowired
	private JsonUtils utils;

	@Transactional
	public String save(WarehouseTypeDto warehouseTypeDto,int hotelId) {
		WarehouseType warehouseType = warehouseTypeMapper.getWarehouseTypeBuilder(warehouseTypeDto);
		warehouseType.setHotelId(new Hotel(hotelId));
		warehouseTypeRepository.save(warehouseType);
		logger.info("Service: Save Warehouse type details");
		return utils.objectMapperSuccess(warehouseTypeMapper.getWarehouseTypeDtoBuilder(warehouseType),
				"Warehouse type Details Saved");
	}

	@Transactional
	public String update(WarehouseTypeDto warehouseTypeDto, int id,int hotelId) {
		logger.info("Service: Update warehouse type details with id {}", id);
		Optional<WarehouseType> optional = warehouseTypeRepository.findById(id);
		if (optional.isPresent()) {
			logger.info("Service: Warehouse type details found with id {} for update operation", id);
			WarehouseType warehouseType = warehouseTypeMapper.getWarehouseTypeBuilder(warehouseTypeDto);
			warehouseType.setHotelId(new Hotel(hotelId));
			warehouseTypeRepository.save(warehouseType);
			return utils.objectMapperSuccess(warehouseTypeMapper.getWarehouseTypeDtoBuilder(warehouseType),
					"Warehouse type Details Updated");
		}
		logger.info("Service: Warehouse type details not found with id {} for update operation", id);
		return utils.objectMapperError("Warehouse type Details Not Found !");
	}

	@Transactional
	public String delete(int id) {
		logger.info("Service: Delete Warehouse type details with id {}", id);
		int isDelete = warehouseTypeRepository.updateActiveStatus(id);
		if (isDelete > 0) {
			logger.info("Service: Warehouse type details found with id {} for delete operation{}", id);
			return utils.objectMapperSuccess("Warehouse type Deleted Successfully");
		}
		logger.info("Service: Warehouse type details not found with id {} for delete operation{}", id);
		return utils.objectMapperError("Warehouse type Deleted Failed");
	}

	@Transactional
	public String getById(int id) {
		logger.info("Service: Fetching Warehouse type details with id {}", id);
		Optional<WarehouseType> warehouseType = warehouseTypeRepository.findById(id);
		if (warehouseType.isPresent()) {
			logger.info("Service: Warehouse type details found with id {}", id);
			WarehouseTypeDto dto = warehouseTypeMapper.getWarehouseTypeDtoBuilder(warehouseType.get());
			return utils.objectMapperSuccess(dto, "Warehouse type Details");
		}
		logger.info("Service: Warehouse type details not found with id {}", id);
		return utils.objectMapperError("Warehouse type Details Not found, Id :" + id);
	}

	@Transactional
	public String findActiveList(String searchTerm, Integer pageNo, Integer pageSize, String sortBy) {
		logger.info("Service: Fetching list of warehouse type details ");
		PageRequest pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<WarehouseType> pagedResult = null;
		if (StringUtils.isBlank(searchTerm)) {
			pagedResult = warehouseTypeRepository.findByIsActive("yes", pageable);
		} else {
			pagedResult = warehouseTypeRepository.findByNameContainingIgnoreCaseAndIsActive(searchTerm, "yes",
					pageable);
		}

		Page<WarehouseTypeDto> page = pagedResult.map(new Function<WarehouseType, WarehouseTypeDto>() {
			@Override
			public WarehouseTypeDto apply(WarehouseType entity) {
				WarehouseTypeDto dto = warehouseTypeMapper.getWarehouseTypeDtoBuilder(entity);
				return dto;
			}
		});
		logger.info("Service: Fetching list of warehouse type details, total records: {}", page.getTotalElements());
		return utils.objectMapperSuccess(page, "All Acive warehouse type list.");
	}

}
