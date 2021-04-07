package com.sunan.order.kot.temp.info;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunan.category.CategoryRepository;
import com.sunan.dish.DishRepository;
import com.sunan.exception.BadRequestException;
import com.sunan.hotel.HotelRepository;
import com.sunan.model.Category;
import com.sunan.model.Dish;
import com.sunan.model.Hotel;
import com.sunan.model.OrderInfoKOT;
import com.sunan.model.OrderedProductKOT;
import com.sunan.model.TempOrderInfoKOT;
import com.sunan.model.TempOrderedProductKOT;
import com.sunan.order.kot.info.OrderInfoKOTMapper;
import com.sunan.order.kot.info.OrderInfoKOTRepository;
import com.sunan.order.kot.product.OrderedProductKOTMapper;
import com.sunan.order.kot.product.OrderedProductKOTRepository;
import com.sunan.order.kot.temp.product.TempOrderedProductKOTMapper;
import com.sunan.order.kot.temp.product.TempOrderedProductKOTRepository;
import com.sunan.utils.JsonUtils;

@Service
public class TempOrderInfoKOTService implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(TempOrderInfoKOTService.class);

	@Autowired
	private TempOrderInfoKOTRepository tempOrderInfoKOTRepository;

	@Autowired
	private TempOrderedProductKOTRepository tempOrderedProductKOTRepository;

	@Autowired
	private OrderInfoKOTRepository orderInfoKOTRepository;

	@Autowired
	private HotelRepository hotelRepository;

	@Autowired
	private OrderedProductKOTRepository orderedProductKOTRepository;

	@Autowired
	TempOrderedInfoKOTMapper tempOrderedInfoKOTMapper;

	@Autowired
	TempOrderedProductKOTMapper tempOrderedProductKOTMapper;

	@Autowired
	OrderInfoKOTMapper orderInfoKOTMapper;

	@Autowired
	OrderedProductKOTMapper orderedProductKOTMapper;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private DishRepository dishRepository;

	@Autowired
	private JsonUtils utils;

	@Transactional
	public String save(TempOrderInfoKOTDto tempOrderInfoKOTDto, int hotelId) {

		Optional<Hotel> hotel = hotelRepository.findById(hotelId);
		if (!hotel.isPresent() || hotelId == 0) {
			throw new BadRequestException("hotel not found");
		}
       
		TempOrderInfoKOT tempOrderInfoKOT = tempOrderedInfoKOTMapper.tempOrderInfoKOTBuilder(tempOrderInfoKOTDto);
		tempOrderInfoKOT.setHotel(new Hotel(hotelId));
		tempOrderInfoKOTRepository.save(tempOrderInfoKOT);

		OrderInfoKOT orderInfoKOT = orderInfoKOTMapper.getOrderInfoKOTBuilder(tempOrderInfoKOTDto);
		orderInfoKOT.setHotel(new Hotel(hotelId));
		orderInfoKOTRepository.save(orderInfoKOT);

		for (DishKOTDto dish : tempOrderInfoKOTDto.getDish()) {
			Optional<Dish> dishes = dishRepository.findByDishId(dish.getDishId());

			if (dishes.isPresent()) {

				Double rate = dish.getRate();
				Double amount = dish.getQuantity() * rate;
				Optional<Category> category = categoryRepository.findById(dish.getCategoryId());

				TempOrderedProductKOT tempOrderedProductKOT = tempOrderedProductKOTMapper.tempOrderedProductKOT(
						tempOrderInfoKOTDto, dish, tempOrderInfoKOT.getId(), amount, category.get(), dishes.get());
				tempOrderedProductKOT.setHotel(new Hotel(hotelId));
				tempOrderedProductKOTRepository.save(tempOrderedProductKOT);

				OrderedProductKOT orderedProductKOT = orderedProductKOTMapper.getRestaurantPOSOrderedProductKOTBuilder(
						tempOrderInfoKOTDto, dish, tempOrderInfoKOT.getId(), amount, category.get(), dishes.get());
				orderedProductKOT.setHotel(new Hotel(hotelId));
				orderedProductKOTRepository.save(orderedProductKOT);

			} else {
				logger.debug("Dish is not present");
				return utils.objectMapperError("dish  is not present");
			}

		}
		logger.info("Temp restaurant order info saved");
		return utils.objectMapperSuccess("Temp Restaurant Order info saved");
	}
	
	/*
	 * @Transactional public String save2(TempOrderInfoKOTDto tempOrderInfoKOTDto,
	 * int hotelId) {
	 * 
	 * Optional<Hotel> hotel = hotelRepository.findById(hotelId); if
	 * (!hotel.isPresent() || hotelId == 0) { throw new
	 * BadRequestException("hotel not found"); }
	 * 
	 * TempOrderInfoKOT tempOrderInfoKOT =
	 * tempOrderedInfoKOTMapper.tempOrderInfoKOTBuilder(tempOrderInfoKOTDto);
	 * tempOrderInfoKOT.setHotel(new Hotel(hotelId));
	 * 
	 * // OrderInfoKOT orderInfoKOT =
	 * orderInfoKOTMapper.getOrderInfoKOTBuilder(tempOrderInfoKOTDto);
	 * orderInfoKOT.setHotel(new Hotel(hotelId));
	 * 
	 * 
	 * Set<TempOrderedProductKOT> tempOrderedProductKOTs = new
	 * HashSet<TempOrderedProductKOT>(); Set<OrderedProductKOT> orderedProductKOTs =
	 * new HashSet<OrderedProductKOT>();
	 * 
	 * for (DishKOTDto dish : tempOrderInfoKOTDto.getDish()) { Optional<Dish> dishes
	 * = dishRepository.findByDishId(dish.getDishId());
	 * 
	 * if (dishes.isPresent()) {
	 * 
	 * Double rate = dish.getRate(); Double amount = dish.getQuantity() * rate;
	 * Optional<Category> category =
	 * categoryRepository.findById(dish.getCategoryId());
	 * 
	 * TempOrderedProductKOT tempOrderedProductKOT =
	 * tempOrderedProductKOTMapper.tempOrderedProductKOT( tempOrderInfoKOTDto, dish,
	 * tempOrderInfoKOT.getId(), amount, category.get(), dishes.get());
	 * tempOrderedProductKOT.setHotel(new Hotel(hotelId));
	 * tempOrderedProductKOTs.add(tempOrderedProductKOT);
	 * 
	 * OrderedProductKOT orderedProductKOT =
	 * orderedProductKOTMapper.getRestaurantPOSOrderedProductKOTBuilder(
	 * tempOrderInfoKOTDto, dish, tempOrderInfoKOT.getId(), amount, category.get(),
	 * dishes.get()); orderedProductKOT.setHotel(new Hotel(hotelId));
	 * orderedProductKOTs.add(orderedProductKOT); } }
	 * tempOrderInfoKOT.setTempOrderedProductKOTs(tempOrderedProductKOTs);
	 * orderInfoKOT.setOrderedProductKOTs(orderedProductKOTs);;
	 * 
	 * tempOrderInfoKOTRepository.save(tempOrderInfoKOT);
	 * orderInfoKOTRepository.save(orderInfoKOT);
	 * 
	 * 
	 * // OrderInfoKOT orderInfoKOT =
	 * orderInfoKOTMapper.getOrderInfoKOTBuilder(tempOrderInfoKOTDto); //
	 * orderInfoKOT.setHotel(new Hotel(hotelId)); //
	 * orderInfoKOTRepository.save(orderInfoKOT); // // for (DishKOTDto dish :
	 * tempOrderInfoKOTDto.getDish()) { // Optional<Dish> dishes =
	 * dishRepository.findByDishId(dish.getDishId()); // // if (dishes.isPresent())
	 * { // // Double rate = dish.getRate(); // Double amount = dish.getQuantity() *
	 * rate; // Optional<Category> category =
	 * categoryRepository.findById(dish.getCategoryId()); // //
	 * TempOrderedProductKOT tempOrderedProductKOT =
	 * tempOrderedProductKOTMapper.tempOrderedProductKOT( // tempOrderInfoKOTDto,
	 * dish, tempOrderInfoKOT.getId(), amount, category.get(), dishes.get()); //
	 * tempOrderedProductKOT.setHotel(new Hotel(hotelId)); //
	 * tempOrderedProductKOTRepository.save(tempOrderedProductKOT); // //
	 * OrderedProductKOT orderedProductKOT =
	 * orderedProductKOTMapper.getRestaurantPOSOrderedProductKOTBuilder( //
	 * tempOrderInfoKOTDto, dish, tempOrderInfoKOT.getId(), amount, category.get(),
	 * dishes.get()); // orderedProductKOT.setHotel(new Hotel(hotelId)); //
	 * orderedProductKOTRepository.save(orderedProductKOT); // // } else { //
	 * logger.debug("Dish is not present"); // return
	 * utils.objectMapperError("dish  is not present"); // }
	 * 
	 * // } logger.info("Temp restaurant order info saved"); return
	 * utils.objectMapperSuccess("Temp Restaurant Order info saved"); }
	 */

}
