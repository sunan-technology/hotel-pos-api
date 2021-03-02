package com.sunan.hotel;

import java.io.Serializable;
import java.util.Optional;
import java.util.function.Function;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
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
	@Autowired
	private HotelRepository hotelRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private JsonUtils utils;
	
	@Transactional
	public String save(HotelDto hotelDto) {
		Hotel hotel=modelMapper.map(hotelDto, Hotel.class);
		hotelRepository.save(hotel);
		return utils.objectMapperSuccess(modelMapper.map(hotel, HotelDto.class),"Hotel Details Saved");
	}
	
	@Transactional
	public String update(HotelDto hotelDto,int id)
	{
		 Optional<Hotel> optional= hotelRepository.findById(id);
		 if(optional.isPresent())
		 {
			 Hotel hotel =modelMapper.map(hotelDto ,Hotel.class);
			 hotelRepository.save(hotel);
			 return utils.objectMapperSuccess(modelMapper.map(hotel, HotelDto.class),"Hotel Details Updated");
		 }
		 else {
			 return utils.objectMapperError("Hotel Details Not Found !");
		 }
		
	}
	
	@Transactional
	public String delete(int id)
	{
		int isDelete=hotelRepository.updateActiveStatus(id);
		if(isDelete > 0)
		{
			return utils.objectMapperSuccess("Hotel Deleted Successfully");
		}
		else
		{
			return utils.objectMapperError("Hotel Deleted Failed");
		}
		
	}
	
	@Transactional
	public String getById(int id)
	{
		Optional<Hotel> hotel =hotelRepository.findById(id);
		if(hotel.isPresent())
		{
			HotelDto dto=modelMapper.map(hotel.get(), HotelDto.class);
			return utils.objectMapperSuccess(dto, "Hotel Details");
		}
		else
		{
			return utils.objectMapperError("Hotel Details Not found, Id :"+id);
		}
		
	}
	
	@Transactional
	public String findActiveList( String searchTerm, Integer pageNo, Integer pageSize, String sortBy)
	{
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
				HotelDto dto = modelMapper.map(entity, HotelDto.class);
				return dto;
			}
		});
		return utils.objectMapperSuccess(page, "All Acive Category list.");
	}
	
	

}
