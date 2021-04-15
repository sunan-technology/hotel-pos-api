package com.sunan.dish;

import java.io.Serializable;
import java.util.List;
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

import com.sunan.category.CategoryRepository;
import com.sunan.kitchen.KitchenRepository;
import com.sunan.model.Category;
import com.sunan.model.Dish;
import com.sunan.model.Hotel;
import com.sunan.utils.JsonUtils;

@Service
public class DishService implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(DishService.class);

	@Autowired
	private DishRepository dishRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private KitchenRepository kitchenRepository;

	@Autowired
	DishMapper dishMapper;

	@Autowired
	private JsonUtils utils;

	@Transactional
	public String save(DishDto dishDto, int hotelId) {

		if (categoryRepository.findById(dishDto.getCategoryId()).isPresent()) {

			if (kitchenRepository.findById(dishDto.getKitchenId()).isPresent()) {
				Dish dish = dishMapper.getDishBuilder(dishDto);
				dish.setHotel(new Hotel(hotelId));
				dishRepository.save(dish);
				logger.info("Service: Save Dish details");
				return utils.objectMapperSuccess(dishMapper.getDishDtoBuilder(dish), "Dish Details Saved");
			} else {
				logger.info("Service : Kitchen not present");
				return utils.objectMapperError("Kitchen not present");
			}
		} else {
			logger.info("Service : Category Not Found");
			return utils.objectMapperError("Category Not Found");
		}
	}

	@Transactional
	public String update(DishDto dishDto, int id, int hotelId) {
		logger.info("Service: Update dish details with id {}", id);
		Optional<Dish> optional = dishRepository.findById(id);
		if (optional.isPresent()) {
			logger.info("Service: dish details found with id {} for update operation", id);
			Dish dish = dishMapper.getDishBuilder(dishDto);
			dish.setHotel(new Hotel(hotelId));
			dishRepository.save(dish);
			return utils.objectMapperSuccess(dishMapper.getDishDtoBuilder(dish), "Dish Details Updated");
		}
		logger.info("Service: dish details not found with id {} for update operation", id);
		return utils.objectMapperError("Dish Details Not Found !");
	}

	@Transactional
	public String delete(int id) {
		logger.info("Service: Delete dish details with id {}", id);
		int isDelete = dishRepository.updateIsActiveStatus(id);
		if (isDelete > 0) {
			logger.info("Service: dish details found with id {} for delete operation{}", id);
			return utils.objectMapperSuccess("Dish Deleted Successfully");
		}
		logger.info("Service: dish details not found with id {} for delete operation{}", id);
		return utils.objectMapperError("Dish Deleted Failed");
	}

	@Transactional
	public String getById(int id) {
		logger.info("Service: Fetching dish details with id {}", id);
		Optional<Dish> dish = dishRepository.findById(id);
		if (dish.isPresent()) {
			logger.info("Service: dish details found with id {}", id);
			DishDto dto = dishMapper.getDishDtoBuilder(dish.get());
			return utils.objectMapperSuccess(dto, "Dish Details");
		}
		logger.info("Service: dish details not found with id {}", id);
		return utils.objectMapperError("Dish Details Not found, Id :" + id);
	}

	@Transactional
	public String findActiveList(String searchTerm, Integer pageNo, Integer pageSize, String sortBy) {
		logger.info("Service: Fetching list of dish details ");
		PageRequest pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Dish> pagedResult = null;
		if (StringUtils.isBlank(searchTerm)) {
			pagedResult = dishRepository.findByIsActive("yes", pageable);
		} else {
			pagedResult = dishRepository.findByDishNameContainingIgnoreCaseAndIsActive(searchTerm, "yes", pageable);
		}

		Page<DishDto> page = pagedResult.map(new Function<Dish, DishDto>() {
			@Override
			public DishDto apply(Dish entity) {
				DishDto dto = dishMapper.getDishDtoBuilder(entity);
				return dto;
			}
		});
		logger.info("Service: Fetching list of dish details, total records: {}", page.getTotalElements());
		return utils.objectMapperSuccess(page, "All Acive dish list.");
	}

	@Transactional
	public String getAllDishByCategory(int categoryId, int hotelId) {

		Optional<Category> category = categoryRepository.findById(categoryId);
		if (category.isPresent()) {
			logger.info("Service : fetching category wise dish");
			List<Dish> list = dishRepository.findByCategoryAndHotelAndIsActive(new Category(categoryId),
					new Hotel(hotelId), "yes");
			logger.info("Service: Fetching list of dish details by category, total records");
			return utils.objectMapperSuccess(list, "All Acive dish list by category.");
		} else {
			logger.info("Service : category not found");
			return utils.objectMapperError("Cateogry not found");
		}
	}

}
