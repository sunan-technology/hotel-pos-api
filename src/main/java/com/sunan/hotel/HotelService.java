package com.sunan.hotel;

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
import com.sunan.utils.JsonUtils;

@Service
public class HotelService implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(HotelService.class);
	
	@Autowired
	private HotelRepository hotelRepository;
 
	@Autowired
	HotelMapper hotelMapper;
	
	@Autowired
	private JsonUtils utils;
	
	
	@Transactional
	public String save(HotelDto hotelDto) { 
		Hotel hotel = hotelMapper.getHotelBuilder(hotelDto);
		hotelRepository.save(hotel);
		logger.info("Service: Save hotel details");
		return utils.objectMapperSuccess(hotelMapper.getHotelDtoBuilder(hotel),"Hotel Details Saved");
	}
	
	@Transactional
	public String update(HotelDto hotelDto,int id)
	{
		logger.info("Service: Update hotel details with id {}", id);
		 Optional<Hotel> optional= hotelRepository.findById(id);
		 if(optional.isPresent())
		 {
			 logger.info("Service: hotel details found with id {} for update operation{}", id);
			 Hotel hotel = hotelMapper.getHotelBuilder(hotelDto);
			 hotelRepository.save(hotel);
			 return utils.objectMapperSuccess(hotelMapper.getHotelDtoBuilder(hotel),"Hotel Details Updated");
		 }
		 logger.info("Service: hotel details not found with id {} for update operation{}", id);
		 return utils.objectMapperError("Hotel Details Not Found !"); 
	}
	
	@Transactional
	public String delete(int id)
	{
		logger.info("Service: Delete hotel details with id {}", id);
		int isDelete=hotelRepository.updateActiveStatus(id);
		if(isDelete > 0)
		{
			logger.info("Service: hotel details found with id {} for delete operation{}", id);
			return utils.objectMapperSuccess("Hotel Deleted Successfully");
		}
		logger.info("Service: hotel details not found with id {} for delete operation{}", id);
		return utils.objectMapperError("Hotel Deleted Failed"); 
	}
	
	@Transactional
	public String getById(int id)
	{
		logger.info("Service: Fetching hotel details with id {}", id);
		Optional<Hotel> hotel =hotelRepository.findById(id);
		if(hotel.isPresent())
		{
			logger.info("Service: hotel details found with id {}", id);
			HotelDto dto = hotelMapper.getHotelDtoBuilder(hotel.get());
			return utils.objectMapperSuccess(dto, "Hotel Details");
		}
		logger.info("Service: hotel details not found with id {}", id);
		return utils.objectMapperError("Hotel Details Not found, Id :"+id); 
	}
	
	@Transactional
	public String findActiveList( String searchTerm, Integer pageNo, Integer pageSize, String sortBy)
	{
		logger.info("Service: Fetching list of hotel details ");
		PageRequest pageable= PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Hotel> pagedResult=null;
		if(StringUtils.isBlank(searchTerm))
		{
			pagedResult=hotelRepository.findByIsActive("yes", pageable);
		}else
		{
			pagedResult=hotelRepository.findByHotelNameContainingIgnoreCaseAndIsActive(searchTerm, "yes", pageable);
		}
		
		Page<HotelDto> page = pagedResult.map(new Function<Hotel, HotelDto>(){
			@Override
			public HotelDto apply(Hotel entity) {
				HotelDto dto = hotelMapper.getHotelDtoBuilder(entity);
				return dto;
			}
		});
		logger.info("Service: Fetching list of hotel details, total records: {}", page.getTotalElements());
		return utils.objectMapperSuccess(page, "All Acive Hotel list.");
	}
	
	

}
