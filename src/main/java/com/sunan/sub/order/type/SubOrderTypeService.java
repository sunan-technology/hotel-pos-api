package com.sunan.sub.order.type;

import java.io.Serializable;
import java.util.Optional;
import java.util.function.Function;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sunan.model.Hotel;
import com.sunan.model.Roles;
import com.sunan.model.SubOrderType;
import com.sunan.roles.RolesDto;
import com.sunan.utils.JsonUtils;

@Service
public class SubOrderTypeService implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(SubOrderTypeService.class);

	
	@Autowired
	private SubOrderTypeRepository subOrderTypeRepository;
	
	
	@Autowired
	SubOrderTypeMapper subOrderTypeMapper;
	
	@Autowired
	private JsonUtils utils;
	
	
	
	@Transactional
	public String save(SubOrderTypeDto subOrderTypeDto, int hotelId) {
		SubOrderType subOrderType = subOrderTypeMapper.getSubOrderTypeBuilder(subOrderTypeDto);	
		subOrderType.setHotel(new Hotel(hotelId));
		subOrderTypeRepository.save(subOrderType);
		logger.info("Service: Save roles details");
		return utils.objectMapperSuccess(subOrderTypeMapper.getSubOrderTypeDtoBuilder(subOrderType), "sub order type Details Saved");
	}
	
	
	@Transactional
	public String update(SubOrderTypeDto subOrderTypeDto, int id, int hotelId) {
		logger.info("Service: Update sub order type details with id {}", id);
		Optional<SubOrderType> optional = subOrderTypeRepository.findById(id);
		if (optional.isPresent()) {
			logger.info("Service: sub order type details found with id {} for update operation", id);
			SubOrderType subOrderType = subOrderTypeMapper.getSubOrderTypeBuilder(subOrderTypeDto);	
			subOrderType.setHotel(new Hotel(hotelId));
			subOrderTypeRepository.save(subOrderType);
			return utils.objectMapperSuccess(subOrderTypeMapper.getSubOrderTypeDtoBuilder(subOrderType), "Sub Order type Details Updated");
		}
		logger.info("Service: sub order type details not found with id {} for update operation", id);
		return utils.objectMapperError("Sub order type Details Not Found !");
	}
	
	@Transactional
	public String delete(int id) {
		logger.info("Service: Delete Sub order type details with id {}", id);
		int isDelete = subOrderTypeRepository.updateActiveStatus(id);
		if (isDelete > 0) {
			logger.info("Service: Sub order type details found with id {} for delete operation{}", id);
			return utils.objectMapperSuccess("Sub order type Deleted Successfully");
		}
		logger.info("Sub order type details not found with id {} for delete operation{}", id);
		return utils.objectMapperError("Sub order type Deleted Failed");
	}
	
	@Transactional
	public String getById(int id) {
		logger.info("Service: Fetching  Sub order type details with id {}", id);
		Optional<SubOrderType> optional = subOrderTypeRepository.findById(id);
		if (optional.isPresent()) {
			logger.info("Service: Sub order type  details found with id {}", id);
			SubOrderTypeDto dto = subOrderTypeMapper.getSubOrderTypeDtoBuilder(optional.get());
			return utils.objectMapperSuccess(dto, "  Sub order type Details");
		}
		logger.info("Service:   Sub order type details not found with id {}", id);
		return utils.objectMapperError("  Sub order type Details Not found, Id :" + id);
	}
	
	
	@Transactional
	public String findActiveList(String searchTerm, Integer pageNo, Integer pageSize, String sortBy,int hotelId) {
		logger.info("Service: Fetching list of Sub order type details ");
		PageRequest pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<SubOrderType> pagedResult = null;

		pagedResult = subOrderTypeRepository.findByIsActiveAndHotel("yes", pageable,new Hotel(hotelId));

		Page<SubOrderTypeDto> page = pagedResult.map(new Function<SubOrderType, SubOrderTypeDto>() {
			@Override
			public SubOrderTypeDto apply(SubOrderType entity) {
				SubOrderTypeDto dto = subOrderTypeMapper.getSubOrderTypeDtoBuilder(entity);
				return dto;
			}
		});
		logger.info("Service: Fetching list of Sub order type details, total records: {}", page.getTotalElements());
		return utils.objectMapperSuccess(page, "All Acive Sub order type list.");
	}

}
