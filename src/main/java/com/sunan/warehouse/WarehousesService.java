package com.sunan.warehouse;

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
import com.sunan.model.Warehouses;
import com.sunan.utils.JsonUtils;

@Service
public class WarehousesService implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(WarehousesService.class);

	@Autowired
	private WarehousesRepository warehousesRepository;

	@Autowired
	WarehousesMapper warehousesMapper;

	@Autowired
	private JsonUtils utils;

	@Transactional
	public String save(WarehousesDto warehousesDto,int hotelId) {
		Warehouses warehouses = warehousesMapper.getWarehousesBuilder(warehousesDto);
		warehouses.setHotelId(new Hotel(hotelId));
		warehousesRepository.save(warehouses);
		logger.info("Service: Save warehouse details");
		return utils.objectMapperSuccess(warehousesMapper.getWarehousesDtoBuilder(warehouses),
				"Warehouse Details Saved");
	}

	@Transactional
	public String update(WarehousesDto warehousesDto, int id,int hotelId) {
		logger.info("Service: Update warehouse details with id {}", id);
		Optional<Warehouses> optional = warehousesRepository.findById(id);
		if (optional.isPresent()) {
			logger.info("Service: warehouse details found with id {} for update operation", id);
			Warehouses warehouses = warehousesMapper.getWarehousesBuilder(warehousesDto);
			warehouses.setHotelId(new Hotel(hotelId));
			warehousesRepository.save(warehouses);
			return utils.objectMapperSuccess(warehousesMapper.getWarehousesDtoBuilder(warehouses),
					"Warehouse Details Updated");
		}
		logger.info("Service: warehouse details not found with id {} for update operation", id);
		return utils.objectMapperError("Warehouse Details Not Found !");
	}

	@Transactional
	public String delete(int id) {
		logger.info("Service: Delete warehouse details with id {}", id);
		int isDelete = warehousesRepository.updateIsActiveStatus(id);
		if (isDelete > 0) {
			logger.info("Service: warehouse details found with id {} for delete operation{}", id);
			return utils.objectMapperSuccess("Warehouse Deleted Successfully");
		}
		logger.info("Service: warehouse details not found with id {} for delete operation{}", id);
		return utils.objectMapperError("Warehouse Deleted Failed");
	}

	@Transactional
	public String getById(int id) {
		logger.info("Service: Fetching warehouse details with id {}", id);
		Optional<Warehouses> warehouses = warehousesRepository.findById(id);
		if (warehouses.isPresent()) {
			logger.info("Service: warehouse details found with id {}", id);
			WarehousesDto dto = warehousesMapper.getWarehousesDtoBuilder(warehouses.get());
			return utils.objectMapperSuccess(dto, "Warehouse Details");
		}
		logger.info("Service: warehouse details not found with id {}", id);
		return utils.objectMapperError("Warehouse Details Not found, Id :" + id);
	}

	@Transactional
	public String findActiveList(String searchTerm, Integer pageNo, Integer pageSize, String sortBy) {
		logger.info("Service: Fetching list of warehouses details ");
		PageRequest pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Warehouses> pagedResult = null;
		if (StringUtils.isBlank(searchTerm)) {
			pagedResult = warehousesRepository.findByIsActive("yes", pageable);
		} else {
			pagedResult = warehousesRepository.findByWarehouseNameContainingIgnoreCaseAndIsActive(searchTerm, "yes",
					pageable);
		}

		Page<WarehousesDto> page = pagedResult.map(new Function<Warehouses, WarehousesDto>() {
			@Override
			public WarehousesDto apply(Warehouses entity) {
				WarehousesDto dto = warehousesMapper.getWarehousesDtoBuilder(entity);
				return dto;
			}
		});
		logger.info("Service: Fetching list of warehouses details, total records: {}", page.getTotalElements());
		return utils.objectMapperSuccess(page, "All Acive warehouses list.");
	}

}
