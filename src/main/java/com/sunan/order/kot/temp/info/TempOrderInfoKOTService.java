package com.sunan.order.kot.temp.info;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

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
import com.sunan.model.HotelTable;
import com.sunan.model.OrderInfoKOT;
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

		TempOrderInfoKOT opt = tempOrderInfoKOTRepository
				.findByHotelTableAndHotel(new HotelTable(tempOrderInfoKOTDto.getTableNo()), new Hotel(hotelId));
		if (opt == null) {

			Optional<Hotel> hotel = hotelRepository.findById(hotelId);
			if (!hotel.isPresent() || hotelId == 0) {
				throw new BadRequestException("hotel not found");
			}

			TempOrderInfoKOT tempOrderInfoKOT = tempOrderedInfoKOTMapper.tempOrderInfoKOTBuilder(tempOrderInfoKOTDto);
			tempOrderInfoKOT.setHotel(new Hotel(hotelId));
			tempOrderInfoKOTRepository.save(tempOrderInfoKOT);

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
				} else {
					logger.debug("Dish is not present");
					return utils.objectMapperError("dish  is not present");
				}

			}
			logger.info("Temp restaurant order info saved");
			return utils.objectMapperSuccess("Temp Restaurant Order info saved");
		} else {

			logger.info("Service :Table is already occupied");
			return utils.objectMapperSuccess("Table is already occupied");
		}
	}

	@Transactional
	public String updateProductQuantity(int quantity, int ticketId, int hotelId, int dishId) {
		Optional<TempOrderInfoKOT> opt = tempOrderInfoKOTRepository.findById(ticketId);

		if (opt.isPresent()) {

			tempOrderedProductKOTRepository.updateQuantityByHotelAndTicketId(new Hotel(hotelId), dishId, quantity);

			Double tempGrandTotal = 0.0;
			List<TempOrderedProductKOT> optional = tempOrderedProductKOTRepository
					.findByTempOrderInfoKOT(new TempOrderInfoKOT(ticketId));

			for (TempOrderedProductKOT tempOrderedProductKOT : optional) {

				tempGrandTotal += tempOrderedProductKOT.getRate() * tempOrderedProductKOT.getQuantity();
			}

			tempOrderInfoKOTRepository.updateGrandTotalByHotelAndTicketId(new Hotel(hotelId), ticketId, tempGrandTotal);

		} else {
			logger.info(" temp order not found");
			return utils.objectMapperError("temp order not found");
		}

		logger.info(" temp product quantity updated");
		return utils.objectMapperSuccess("temp product quantity updated");
	}

	@Transactional
	public String changeTable(int tableId, int ticketId, int hotelId) {

		Optional<TempOrderInfoKOT> opt = tempOrderInfoKOTRepository.findById(ticketId);
		Optional<OrderInfoKOT> orderInfoKot = orderInfoKOTRepository.findById(ticketId);
		if (opt.isPresent() && orderInfoKot.isPresent()) {
			tempOrderInfoKOTRepository.changeTable(new Hotel(hotelId), ticketId, tableId);
			tempOrderedProductKOTRepository.changeTable(new Hotel(hotelId), new TempOrderInfoKOT(ticketId), tableId);
//			orderInfoKOTRepository.changeTable(new Hotel(hotelId), ticketId, tableId);
		} else {
			logger.info(" Service : temp order not found");
			return utils.objectMapperError("Temp order not found");
		}

		logger.info("Service: table changed");
		return utils.objectMapperSuccess("Table changed Successfully");
	}

	@Transactional
	public String addDishQuantity(int dishId, int ticketId, int quantity, int hotelId) {
		Optional<TempOrderInfoKOT> opt = tempOrderInfoKOTRepository.findById(ticketId);

		if (opt.isPresent()) {

			tempOrderedProductKOTRepository.updateQuantityByHotelAndTicketId(new Hotel(hotelId), dishId, quantity + 1);

			Optional<TempOrderedProductKOT> optional = tempOrderedProductKOTRepository
					.findByIdAndTempOrderInfoKOT(dishId, new TempOrderInfoKOT(ticketId));

			tempOrderInfoKOTRepository.updateGrandTotalByHotelAndTicketId(new Hotel(hotelId), ticketId,
					opt.get().getGrandTotal() + optional.get().getRate());
		} else {
			logger.debug("Dish is not present");
			return utils.objectMapperError("dish  is not present");
		}

		logger.info(" temp product quantity updated");
		return utils.objectMapperSuccess("temp product quantity updated");

	}

	@Transactional
	public String removeDishQuantity(int dishId, int ticketId, int hotelId, int quantity) {
		Optional<TempOrderInfoKOT> opt = tempOrderInfoKOTRepository.findById(ticketId);
		if (opt.isPresent()) {
			logger.info("Service : remove temp dish quantity");
			tempOrderedProductKOTRepository.updateQuantityByHotelAndTicketId(new Hotel(hotelId), dishId, quantity - 1);
			logger.info("Service : remove dish quantity");
			orderedProductKOTRepository.updateQuantityByHotelAndTicketId(new Hotel(hotelId), dishId, quantity - 1);

			Optional<TempOrderedProductKOT> optional = tempOrderedProductKOTRepository
					.findByIdAndTempOrderInfoKOT(dishId, new TempOrderInfoKOT(ticketId));

			// updating grand total in temp order info table
			tempOrderInfoKOTRepository.updateGrandTotalByHotelAndTicketId(new Hotel(hotelId), ticketId,
					opt.get().getGrandTotal() - optional.get().getRate());
		} else {
			logger.debug("Dish is not present");
			return utils.objectMapperError("dish  is not present");
		}

		logger.info(" temp product quantity updated");
		return utils.objectMapperSuccess("temp product quantity updated");
	}

	@Transactional
	public String removeProduct(int dishId, int ticketId, int hotelId) {

		Optional<TempOrderInfoKOT> opt = tempOrderInfoKOTRepository.findById(ticketId);
		if (opt.isPresent()) {
			logger.info("Service :remove product");
			tempOrderedProductKOTRepository.deleteByTempOrderInfoKOTAndId(new TempOrderInfoKOT(ticketId), dishId);

		} else {
			logger.info("Service :temp  order is not present");
			return utils.objectMapperError("Temp order is not present");
		}

		logger.info(" temp product removed");
		return utils.objectMapperSuccess("Temp product removed");
	}

	@Transactional
	public String removeTempOrder(int ticketId) {

		Optional<TempOrderInfoKOT> tempOrderInfoKOT = tempOrderInfoKOTRepository.findById(ticketId);

		if (tempOrderInfoKOT.isPresent()) {
			logger.info("Service : delete temp order info ");
			tempOrderedProductKOTRepository.deleteByTempOrderInfoKOT(new TempOrderInfoKOT(ticketId));
			tempOrderInfoKOTRepository.deleteById(ticketId);

		} else {
			logger.info("Service : temp order not found");
			return utils.objectMapperError("Temp order not found");
		}
		logger.info("Temp order delete suceessfully");
		return utils.objectMapperSuccess("Temp order delete suceessfully");
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
