package com.sunan.TempRestaurantPOS_OrderInfoKOT;

import java.io.Serializable;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunan.RestaurantPOS_OrderInfoKOT.RestaurantPOSOrderInfoKOTMapper;
import com.sunan.RestaurantPOS_OrderInfoKOT.RestaurantPOSOrderInfoKOTRepository;
import com.sunan.RestaurantPOS_OrderedProductKOT.RestaurantPOSOrderedProductKOTMapper;
import com.sunan.RestaurantPOS_OrderedProductKOT.RestaurantPOSOrderedProductKOTRepository;
import com.sunan.TempRestaurantPOS_OrderedProductKOT.TempRestaurantPOSOrderedProductKOTMapper;
import com.sunan.TempRestaurantPOS_OrderedProductKOT.TempRestaurantPOSOrderedProductKOTRepository;
import com.sunan.category.CategoryRepository;
import com.sunan.dish.DishRepository;
import com.sunan.model.Category;
import com.sunan.model.Dish;
import com.sunan.model.Hotel;
import com.sunan.model.RestaurantPOSOrderInfoKOT;
import com.sunan.model.RestaurantPOSOrderedProductKOT;
import com.sunan.model.TempRestaurantPOSOrderInfoKOT;
import com.sunan.model.TempRestaurantPOSOrderedProductKOT;
import com.sunan.utils.Common;
import com.sunan.utils.JsonUtils;

@Service
public class TempRestaurantPOSOrderInfoKOTService implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(TempRestaurantPOSOrderInfoKOTService.class);

	@Autowired
	private TempRestaurantPOSOrderInfoKOTRepository tempRestaurantPOSOrderInfoKOTRepository;

	@Autowired
	private TempRestaurantPOSOrderedProductKOTRepository tempRestaurantPOSOrderedProductKOTRepository;
	
	
	@Autowired
	private RestaurantPOSOrderInfoKOTRepository restaurantPOSOrderInfoKOTRepository;
	
	
	@Autowired
	private RestaurantPOSOrderedProductKOTRepository restaurantPOSOrderedProductKOTRepository;

	@Autowired
	TempRestaurantPOSOrderedInfoKOTMapper tempRestaurantPOSOrderedInfoKOTMapper;

	@Autowired
	TempRestaurantPOSOrderedProductKOTMapper tempRestaurantPOSOrderedProductKOTMapper;
	
	
	@Autowired
	RestaurantPOSOrderInfoKOTMapper restaurantPOSOrderInfoKOTMapper;
	
	@Autowired
	RestaurantPOSOrderedProductKOTMapper restaurantPOSOrderedProductKOTMapper;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private DishRepository dishRepository;

	@Autowired
	private JsonUtils utils;

	@Transactional
	public String save(TempRestaurantPOSOrderInfoKOTDto tempRestaurantPOSOrderInfoKOTDto,int hotelId) {
		
	
		TempRestaurantPOSOrderInfoKOT tempRestaurantPOSOrderInfoKOT = tempRestaurantPOSOrderedInfoKOTMapper
				.tempRestaurantPOSOrderInfoKOTBuilder(tempRestaurantPOSOrderInfoKOTDto);
		tempRestaurantPOSOrderInfoKOT.setHotelId(new Hotel(hotelId));
		tempRestaurantPOSOrderInfoKOTRepository.save(tempRestaurantPOSOrderInfoKOT);
		
		RestaurantPOSOrderInfoKOT restaurantPOSOrderInfoKOT=restaurantPOSOrderInfoKOTMapper.getRestaurantPOSOrderInfoKOTBuilder(tempRestaurantPOSOrderInfoKOTDto);
		restaurantPOSOrderInfoKOT.setHotelId(new Hotel(hotelId));
		restaurantPOSOrderInfoKOTRepository.save(restaurantPOSOrderInfoKOT);
		
		int tempRestaurantPOSOrderInfoKOTId = tempRestaurantPOSOrderInfoKOT.getId();

		for (DishKOTDto dish : tempRestaurantPOSOrderInfoKOTDto.getDish()) {
			int dishId = dish.getDishId();
			
			Double rate = dish.getRate();
			int quantity = dish.getQuantity();
			Double amount = quantity * rate;
			int categoryId = dish.getCategoryId();
			


			Optional<Dish> dishes = dishRepository.findByDishId(dishId);
			
			if(!dishes.isPresent()) {
				
				return  utils.objectMapperError("dish id is not present");
			}


			Optional<Category> category = categoryRepository.findById(categoryId);
			
			if(!category.isPresent()) {
				
				return utils.objectMapperError("category id is not present");
			}



			Double vatPer = category.get().getVat();
			Double vatAmount = Common.calculateGST(rate, vatPer);

			Double stPer = category.get().getSt();
			Double stAmount = Common.calculateGST(rate, stPer);

			Double scPer = category.get().getSc();
			Double scAmount = Common.calculateGST(rate, scPer);

			Double discount = dishes.get().getDiscount();
			Double discountAmount = Common.calculateGST(rate, discount);

			TempRestaurantPOSOrderedProductKOT tempRestaurantPOSOrderedProductKOT = tempRestaurantPOSOrderedProductKOTMapper
					.tempRestaurantPOSOrderedProductKOT(tempRestaurantPOSOrderInfoKOTDto, dish,
							tempRestaurantPOSOrderInfoKOTId, amount, vatPer, vatAmount, stPer, stAmount, scPer,
							scAmount, discount, discountAmount);

			tempRestaurantPOSOrderedProductKOT.setTempRestaurantPOSOrderInfoKOT(tempRestaurantPOSOrderInfoKOT);
			tempRestaurantPOSOrderedProductKOT.setHotelId(new Hotel(hotelId));
			tempRestaurantPOSOrderedProductKOTRepository.save(tempRestaurantPOSOrderedProductKOT);
			
			RestaurantPOSOrderedProductKOT restaurantPOSOrderedProductKOT  =restaurantPOSOrderedProductKOTMapper.getRestaurantPOSOrderedProductKOTBuilder(tempRestaurantPOSOrderInfoKOTDto, dish, restaurantPOSOrderInfoKOT.getId(), amount, vatPer, vatAmount, stPer, stAmount, scPer, scAmount, discount, discountAmount);
			
			restaurantPOSOrderedProductKOT.setHotelId(new Hotel(hotelId));
			
			restaurantPOSOrderedProductKOTRepository.save(restaurantPOSOrderedProductKOT);

		}
         logger.info("Temp restaurant order info saved");
		return utils.objectMapperSuccess("Temp Restaurant Order info saved");
	}

}
