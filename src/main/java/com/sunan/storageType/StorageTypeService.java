package com.sunan.storageType;

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
import com.sunan.model.StorageType;
import com.sunan.utils.JsonUtils;

@Service
public class StorageTypeService implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(StorageTypeService.class);

	@Autowired
	private StorageTypeRepository storageTypeRepository;

	@Autowired
	StorageTypeMapper storageTypeMapper;

	@Autowired
	private JsonUtils utils;

	@Transactional
	public String save(StorageTypeDto storageTypeDto,int hotelId) {
		StorageType storageType = storageTypeMapper.getStorageTypeBuilder(storageTypeDto);
		storageType.setHotelId(new Hotel(hotelId));
		storageTypeRepository.save(storageType);
		logger.info("Service: Save storage type details");
		return utils.objectMapperSuccess(storageTypeMapper.getStorageTypeDtoBuilder(storageType),
				"Storage type Details Saved");
	}

	@Transactional
	public String update(StorageTypeDto storageTypeDto, int id,int hotelId) {
		logger.info("Service: Update storage type details with id {}", id);
		Optional<StorageType> optional = storageTypeRepository.findById(id);
		if (optional.isPresent()) {
			logger.info("Service: storage type details found with id {} for update operation", id);
			StorageType storageType = storageTypeMapper.getStorageTypeBuilder(storageTypeDto);
			storageType.setHotelId(new Hotel(hotelId));
			storageTypeRepository.save(storageType);
			return utils.objectMapperSuccess(storageTypeMapper.getStorageTypeDtoBuilder(storageType),
					"Storage type Details Updated");
		}
		logger.info("Service: storage type details not found with id {} for update operation", id);
		return utils.objectMapperError("Storage type Details Not Found !");
	}

	@Transactional
	public String delete(int id) {
		logger.info("Service: Delete storage type details with id {}", id);
		int isDelete = storageTypeRepository.updateIsActiveStatus(id);
		if (isDelete > 0) {
			logger.info("Service: storage type details found with id {} for delete operation{}", id);
			return utils.objectMapperSuccess("Storage Type Deleted Successfully");
		}
		logger.info("Service: storage type details not found with id {} for delete operation{}", id);
		return utils.objectMapperError("Storage type Deleted Failed");
	}

	@Transactional
	public String getById(int id) {
		logger.info("Service: Fetching  storage type details with id {}", id);
		Optional<StorageType> storageType = storageTypeRepository.findById(id);
		if (storageType.isPresent()) {
			logger.info("Service:  storage type details found with id {}", id);
			StorageTypeDto dto = storageTypeMapper.getStorageTypeDtoBuilder(storageType.get());
			return utils.objectMapperSuccess(dto, " Storage type Details");
		}
		logger.info("Service:  storage type details not found with id {}", id);
		return utils.objectMapperError(" Storage type Details Not found, Id :" + id);
	}

	@Transactional
	public String findActiveList(String searchTerm, Integer pageNo, Integer pageSize, String sortBy) {
		logger.info("Service: Fetching list of product details ");
		PageRequest pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<StorageType> pagedResult = null;
		if (StringUtils.isBlank(searchTerm)) {
			pagedResult = storageTypeRepository.findByIsActive("yes", pageable);
		} else {
			pagedResult = storageTypeRepository.findByNameContainingIgnoreCaseAndIsActive(searchTerm, "yes", pageable);
		}

		Page<StorageTypeDto> page = pagedResult.map(new Function<StorageType, StorageTypeDto>() {
			@Override
			public StorageTypeDto apply(StorageType entity) {
				StorageTypeDto dto = storageTypeMapper.getStorageTypeDtoBuilder(entity);
				return dto;
			}
		});
		logger.info("Service: Fetching list of storage type details, total records: {}", page.getTotalElements());
		return utils.objectMapperSuccess(page, "All Acive storage type list.");
	}

}
