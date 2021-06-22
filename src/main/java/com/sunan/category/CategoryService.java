package com.sunan.category;

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

import com.sunan.hotel.HotelRepository;
import com.sunan.model.Category;
import com.sunan.model.Hotel;
import com.sunan.utils.JsonUtils;

@Service
public class CategoryService implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(CategoryService.class);

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	CategoryMapper categoryMapper;
	
	@Autowired
	private HotelRepository hotelRepository;

	@Autowired
	private JsonUtils utils;

	@Transactional
	public String save(CategoryDto categoryDto,int hotelId) {
		
		Optional<Hotel> hotel=hotelRepository.findById(hotelId);
		if(hotel.isPresent()) {
		Category category = categoryMapper.getCategoryBuilder(categoryDto);
		category.setHotel(new Hotel(hotelId));
		categoryRepository.save(category);
		logger.info("Service: Save Category details");
		return utils.objectMapperSuccess(categoryMapper.getCategoryDtoBuilder(category), "Category Details Saved");
	} else {
		logger.info("Service: hotel not found");
		return utils.objectMapperError("Hotel not found");
	}
	}

	@Transactional
	public String update(CategoryDto categoryDto, int id,int hotelId) {
		logger.info("Service: Update category details with id {}", id);
		Optional<Category> optional = categoryRepository.findById(id);
		if (optional.isPresent()) {
			logger.info("Service: category details found with id {} for update operation", id);
			Category category = categoryMapper.getCategoryBuilder(categoryDto);
			category.setHotel(new Hotel(hotelId));
			categoryRepository.save(category);
			return utils.objectMapperSuccess(categoryMapper.getCategoryDtoBuilder(category),
					"Category Details Updated");
		}
		logger.info("Service: Category details not found with id {} for update operation", id);
		return utils.objectMapperError("Category Details Not Found !");
	}

	@Transactional
	public String delete(int id) {
		logger.info("Service: Delete Category details with id {}", id);
		int isDelete = categoryRepository.updateActiveStatus(id);
		if (isDelete > 0) {
			logger.info("Service: Category details found with id {} for delete operation{}", id);
			return utils.objectMapperSuccess("Category Deleted Successfully");
		}
		logger.info("Service: Category details not found with id {} for delete operation{}", id);
		return utils.objectMapperError("Category Deleted Failed");
	}

	@Transactional
	public String getById(int id) {
		logger.info("Service: Fetching category details with id {}", id);
		Optional<Category> category = categoryRepository.findById(id);
		if (category.isPresent()) {
			logger.info("Service: category details found with id {}", id);
			CategoryDto dto = categoryMapper.getCategoryDtoBuilder(category.get());
			return utils.objectMapperSuccess(dto, "Category Details");
		}
		logger.info("Service: category details not found with id {}", id);
		return utils.objectMapperError("Category Details Not found, Id :" + id);
	}

	@Transactional
	public String findActiveList(String searchTerm, Integer pageNo, Integer pageSize, String sortBy,int hotelId) {
		logger.info("Service: Fetching list of category details ");
		PageRequest pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Category> pagedResult = null;
		
			pagedResult = categoryRepository.findByIsActiveAndHotelId("yes", pageable,new Hotel(hotelId));
		

		Page<CategoryDto> page = pagedResult.map(new Function<Category, CategoryDto>() {
			@Override
			public CategoryDto apply(Category entity) {
				CategoryDto dto = categoryMapper.getCategoryDtoBuilder(entity);
				return dto;
			}
		});
		logger.info("Service: Fetching list of category details, total records: {}", page.getTotalElements());
		return utils.objectMapperSuccess(page, "All Acive Category list.");
	}

}
