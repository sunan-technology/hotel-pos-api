package com.sunan.kitchen;

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

import com.sunan.model.Kitchen;
import com.sunan.utils.JsonUtils;

@Service
public class KitchenService implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(KitchenService.class);
	
	@Autowired
	private KitchenRepository kitchenRepository;
	
	@Autowired
	private JsonUtils utils;
	
	@Autowired
    KitchenMapper kitchenMapper;
	
	
	@Transactional
	public String save(KitchenDto kitchenDto) { 
		Kitchen kitchen = kitchenMapper.getKitchenBuilder(kitchenDto);
		kitchenRepository.save(kitchen);
		logger.info("Service: Save Kitchen details");
		return utils.objectMapperSuccess(kitchenMapper.getKitchenDtoBuilder(kitchen),"Kitchen Details Saved");
	}
	
	
	@Transactional
	public String update(KitchenDto kitchenDto,int id)
	{
		logger.info("Service: Update kitchen details with id {}", id);
		 Optional<Kitchen> optional= kitchenRepository.findById(id);
		 if(optional.isPresent())
		 {
			 logger.info("Service: kitchen details found with id {} for update operation", id);
			 Kitchen kitchen = kitchenMapper.getKitchenBuilder(kitchenDto);
			 kitchenRepository.save(kitchen);
			 return utils.objectMapperSuccess(kitchenMapper.getKitchenDtoBuilder(kitchen),"Kitchen Details Updated");
		 }
		 logger.info("Service: kitchen details not found with id {} for update operation", id);
		 return utils.objectMapperError("Kitchen Details Not Found !"); 
	}
	
	
	@Transactional
	public String delete(int id)
	{
		logger.info("Service: Delete Kitchen details with id {}", id);
		int isDelete=kitchenRepository.updateIsActiveStatus(id);
		if(isDelete > 0)
		{
			logger.info("Service: kitchen details found with id {} for delete operation{}", id);
			return utils.objectMapperSuccess("Kitchen Deleted Successfully");
		}
		logger.info("Service: kitchen details not found with id {} for delete operation{}", id);
		return utils.objectMapperError("Kitchen Deleted Failed"); 
	}
	
	
	@Transactional
	public String getById(int id)
	{
		logger.info("Service: Fetching kitchen details with id {}", id);
		Optional<Kitchen> kitchen =kitchenRepository.findById(id);
		if(kitchen.isPresent())
		{
			logger.info("Service: kitchen details found with id {}", id);
			KitchenDto dto = kitchenMapper.getKitchenDtoBuilder(kitchen.get());
			return utils.objectMapperSuccess(dto, "Kitchen Details");
		}
		logger.info("Service: kitchen details not found with id {}", id);
		return utils.objectMapperError("Kitchen Details Not found, Id :"+id); 
	}
	
	
	@Transactional
	public String findActiveList( String searchTerm, Integer pageNo, Integer pageSize, String sortBy)
	{
		logger.info("Service: Fetching list of kitchen details ");
		PageRequest pageable= PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Kitchen> pagedResult=null;
		if(StringUtils.isBlank(searchTerm))
		{
			pagedResult=kitchenRepository.findByIsActive("yes", pageable);
		}else
		{
			pagedResult=kitchenRepository.findByKitchenNameContainingIgnoreCaseAndIsActive(searchTerm, "yes", pageable);
		}
		
		Page<KitchenDto> page = pagedResult.map(new Function<Kitchen, KitchenDto>(){
			@Override
			public KitchenDto apply(Kitchen entity) {
				KitchenDto dto = kitchenMapper.getKitchenDtoBuilder(entity);
				return dto;
			}
		});
		logger.info("Service: Fetching list of kitchen details, total records: {}", page.getTotalElements());
		return utils.objectMapperSuccess(page, "All Acive Kitchen list.");
	}
	

	
	
	

}
