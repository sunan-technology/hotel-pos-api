package com.sunan.TempRestaurantPOS_OrderInfoKOT;

import java.io.Serializable;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunan.TempRestaurantPOS_OrderedProductKOT.TempRestaurantPOSOrderedProductKOTDto;
import com.sunan.TempRestaurantPOS_OrderedProductKOT.TempRestaurantPOSOrderedProductKOTMapper;
import com.sunan.TempRestaurantPOS_OrderedProductKOT.TempRestaurantPOSOrderedProductKOTRepository;
import com.sunan.category.CategoryRepository;
import com.sunan.dish.DishRepository;
import com.sunan.model.Category;
import com.sunan.model.Dish;
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
	TempRestaurantPOSOrderedInfoKOTMapper tempRestaurantPOSOrderedInfoKOTMapper;

	@Autowired
	TempRestaurantPOSOrderedProductKOTMapper tempRestaurantPOSOrderedProductKOTMapper;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private DishRepository dishRepository;

	@Autowired
	private JsonUtils utils;

	@Transactional
	public String save(TempRestaurantPOSOrderInfoKOTDto tempRestaurantPOSOrderInfoKOTDto) {

		TempRestaurantPOSOrderInfoKOT tempRestaurantPOSOrderInfoKOT = tempRestaurantPOSOrderedInfoKOTMapper
				.tempRestaurantPOSOrderInfoKOTBuilder(tempRestaurantPOSOrderInfoKOTDto);
		tempRestaurantPOSOrderInfoKOTRepository.save(tempRestaurantPOSOrderInfoKOT);

		int tempRestaurantPOSOrderInfoKOTId = tempRestaurantPOSOrderInfoKOT.getId();
		

		TempRestaurantPOSOrderedProductKOTDto tempRestaurantPOSOrderedProductKOTDto = new TempRestaurantPOSOrderedProductKOTDto();
		for (DishKOTDto dish : tempRestaurantPOSOrderInfoKOTDto.getDish()) {
			int dishId = dish.getDishId();

			Double rate = dish.getRate();
			int quantity = dish.getQuantity();
			Double amount = quantity * rate;
			int categoryId = dish.getCategoryId();

			Optional<Dish> dishes = dishRepository.findByDishId(dishId);

			Optional<Category> category = categoryRepository.findById(categoryId);

			Double vatPer = category.get().getVat();
			Double vatAmount = Common.calculateGST(rate, vatPer);

			Double stPer = category.get().getSt();
			Double stAmount = Common.calculateGST(rate, stPer);

			Double scPer = category.get().getSc();
			Double scAmount = Common.calculateGST(rate, scPer);

			Double discount = dishes.get().getDiscount();
			Double discountAmount = Common.calculateGST(rate, discount);

			tempRestaurantPOSOrderedProductKOTDto.setTempRestaurantPOSOrderInfoKOTId(tempRestaurantPOSOrderInfoKOTId);
			tempRestaurantPOSOrderedProductKOTDto.setDish(dish.getDish());
			tempRestaurantPOSOrderedProductKOTDto.setRate(dish.getRate());
			tempRestaurantPOSOrderedProductKOTDto.setQuantity(dish.getQuantity());
			tempRestaurantPOSOrderedProductKOTDto.setAmount(amount);
			tempRestaurantPOSOrderedProductKOTDto.setVatPer(vatPer);
			tempRestaurantPOSOrderedProductKOTDto.setVatAmount(vatAmount);
			tempRestaurantPOSOrderedProductKOTDto.setStPer(stPer);
			tempRestaurantPOSOrderedProductKOTDto.setStAmount(stAmount);
			tempRestaurantPOSOrderedProductKOTDto.setScPer(scPer);
			tempRestaurantPOSOrderedProductKOTDto.setScAmount(scAmount);
			tempRestaurantPOSOrderedProductKOTDto.setDiscountPer(discount);
			tempRestaurantPOSOrderedProductKOTDto.setDiscountAmount(discountAmount);
			tempRestaurantPOSOrderedProductKOTDto.setTotalAmount(tempRestaurantPOSOrderInfoKOTDto.getTotalAmount());
			tempRestaurantPOSOrderedProductKOTDto.setTableNumber(tempRestaurantPOSOrderInfoKOTDto.getTableNo());
			tempRestaurantPOSOrderedProductKOTDto.setItemStatus(Common.itemStatus);
			tempRestaurantPOSOrderedProductKOTDto.setIsActive("yes");

			TempRestaurantPOSOrderedProductKOT tempRestaurantPOSOrderedProductKOT = tempRestaurantPOSOrderedProductKOTMapper
					.tempRestaurantPOSOrderedProductKOTBuilder(tempRestaurantPOSOrderedProductKOTDto);

			tempRestaurantPOSOrderedProductKOT.setTempRestaurantPOSOrderInfoKOT(tempRestaurantPOSOrderInfoKOT);
			tempRestaurantPOSOrderedProductKOTRepository.save(tempRestaurantPOSOrderedProductKOT);

		}

		return utils.objectMapperSuccess("Temp Restaurant Order info saved");
	}

}
