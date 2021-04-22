package com.sunan.discount;

import java.io.Serializable;
import java.util.List;
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

import com.sunan.dish.DishService;
import com.sunan.exception.BadRequestException;
import com.sunan.model.Discount;
import com.sunan.model.DiscountOrderType;
import com.sunan.model.Hotel;
import com.sunan.utils.JsonUtils;

@Service
public class DiscountService implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(DishService.class);

	@Autowired
	private DiscountRepository discountRepository;
	
	@Autowired
	private DiscountOrderTypeRepository discountOrderTypeRepository;

	@Autowired
	DiscountMapper discountMapper;

	@Autowired
	private JsonUtils utils;
	
	private void validateDiscount(DiscountDto discountDto) {
		
		if(discountDto.getType().equals("Percentage") || discountDto.getType().equals("Fixed")) {
			throw new BadRequestException("please set proper discount type 'Percentage/Fixed' ");
			
		}
	}

	@Transactional
	public String save(DiscountDto discountDto, int hotelId) {

		//validateDiscount(discountDto);
		if(discountDto.getType().equals("Percentage") || discountDto.getType().equals("Fixed")) {
		Discount discount = discountMapper.getDiscountBuilder(discountDto);
		discount.setHotel(new Hotel(hotelId));
		logger.info("Service : saving discount details");
		discountRepository.save(discount);
		
		List<DiscountOrderType> discountOrderType=discountMapper.getDiscountOrderTypeBuilder(discountDto.getOrderType(), discount.getId(), hotelId);
		logger.info("Service : saving discount order type details");
		discountOrderTypeRepository.saveAll(discountOrderType);
		List<DiscountOrderType> discountOrderType2=(List<DiscountOrderType>) discountOrderTypeRepository.findAll();
		List<DiscountOrderTypeDto> discountOrderType1=discountMapper.getDiscountOrderTypeDtoBuilder(discountOrderType2);
		
		return utils.objectMapperSuccess(discountMapper.getDiscountDtoBuilder(discount,discountOrderType1),
				"Discount details saved successfully");
		}else {
			logger.info("Service :please set proper discount type 'Percentage/Fixed'");
			return utils.objectMapperError("please set proper discount type 'Percentage/Fixed'");
		}

	}

	@Transactional
	public String update(DiscountDto discountDto, int id, int hotelId) {

		Optional<Discount> discount = discountRepository.findById(id);
		if (discount.isPresent()) {
			logger.info("Service: dicount details found with id {} for update operation", id);
			Discount discounts = discountMapper.getDiscountBuilder(discountDto);
			discounts.setHotel(new Hotel(hotelId));
			logger.info("Service : updating discount details");
			discountRepository.save(discounts);
			return utils.objectMapperSuccess(
					"Discount details saved successfully");

		} else {
			logger.info("Service: discount details not found with id {} for update operation", id);
			return utils.objectMapperError("Discount Details Not Found !");
		}

	}

	@Transactional
	public String getById(int id) {
		logger.info("Service: Fetching discount details with id {}", id);
		Optional<Discount> discount = discountRepository.findById(id);
		if (discount.isPresent()) {
			logger.info("Service: discount details found with id {}", id);
			List<DiscountOrderType> discountOrderType2=(List<DiscountOrderType>) discountOrderTypeRepository.findAll();
			List<DiscountOrderTypeDto> discountOrderType1=discountMapper.getDiscountOrderTypeDtoBuilder(discountOrderType2);
			
			DiscountDto dto = discountMapper.getDiscountDtoBuilder(discount.get(),discountOrderType1);
			return utils.objectMapperSuccess(dto, "Discount Details");
		}
		logger.info("Service: discount details not found with id {}", id);
		return utils.objectMapperError("Discount Details Not found, Id :" + id);
	}

	@Transactional
	public String findActiveList(String searchTerm, Integer pageNo, Integer pageSize, String sortBy, int hotelId) {
		logger.info("Service: Fetching list of discount details ");
		PageRequest pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Discount> pagedResult = null;

		pagedResult = discountRepository.findByIsActiveAndHotel("yes", pageable, new Hotel(hotelId));

		Page<DiscountDto> page = pagedResult.map(new Function<Discount, DiscountDto>() {
			@Override
			public DiscountDto apply(Discount entity) {
				List<DiscountOrderType> discountOrderType2=(List<DiscountOrderType>) discountOrderTypeRepository.findAll();
				List<DiscountOrderTypeDto> discountOrderType1=discountMapper.getDiscountOrderTypeDtoBuilder(discountOrderType2);
				
				DiscountDto dto = discountMapper.getDiscountDtoBuilder(entity,discountOrderType1);
				return dto;
			}
		});
		logger.info("Service: Fetching list of discount details, total records: {}", page.getTotalElements());
		return utils.objectMapperSuccess(page, "All Acive discount list.");
	}
	
	
	@Transactional
	public String delete(int id) {
		logger.info("Service: Delete discount details with id {}", id);
		int isDelete = discountRepository.updateIsActiveStatus(id);
		int isOrderTypeDelete=discountOrderTypeRepository.updateIsActiveStatus(id);
		if (isDelete > 0 && isOrderTypeDelete > 0) {
			logger.info("Service: discount details found with id {} for delete operation{}", id);
			return utils.objectMapperSuccess("Discount Deleted Successfully");
		}
		logger.info("Service: Discount details not found with id {} for delete operation{}", id);
		return utils.objectMapperError("Discount Deleted Failed");
	}
}
