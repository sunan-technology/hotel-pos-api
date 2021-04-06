package com.sunan.RestaurantPOS_BillingInfoKOT;

import java.io.Serializable;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunan.RestaurantPOS_OrderedProductBillKOT.RestaurantPOSOrderedProductBillKOTMapper;
import com.sunan.RestaurantPOS_OrderedProductBillKOT.RestaurantPOSOrderedProductBillKOTRepository;
import com.sunan.TempRestaurantPOS_OrderInfoKOT.DishKOTDto;
import com.sunan.TempRestaurantPOS_OrderInfoKOT.TempRestaurantPOSOrderInfoKOTRepository;
import com.sunan.TempRestaurantPOS_OrderedProductKOT.TempRestaurantPOSOrderedProductKOTRepository;
import com.sunan.category.CategoryRepository;
import com.sunan.customer.CustomerRepository;
import com.sunan.dish.DishRepository;
import com.sunan.hotel.HotelRepository;
import com.sunan.member.MemberRepository;
import com.sunan.model.Category;
import com.sunan.model.Customer;
import com.sunan.model.Dish;
import com.sunan.model.Hotel;
import com.sunan.model.HotelTable;
import com.sunan.model.Member;
import com.sunan.model.RestaurantPOSBillingInfoKOT;
import com.sunan.model.RestaurantPOSOrderedProductBillKOT;
import com.sunan.utils.Common;
import com.sunan.utils.JsonUtils;

@Service
public class RestaurantPOSBillingInfoKOTService implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(RestaurantPOSBillingInfoKOTService.class);

	@Autowired
	private DishRepository dishRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private HotelRepository hotelRepository;

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private TempRestaurantPOSOrderInfoKOTRepository tempRestaurantPOSOrderInfoKOTRepository;

	@Autowired
	private TempRestaurantPOSOrderedProductKOTRepository tempRestaurantPOSOrderedProductKOTRepository;

	@Autowired
	private RestaurantPOSBillingInfoKOTRepository restaurantPOSBillingInfoKOTRepository;

	@Autowired
	private RestaurantPOSOrderedProductBillKOTRepository restaurantPOSOrderedProductBillKOTRepository;

	@Autowired
	RestaurantPOSOrderedProductBillKOTMapper restaurantPOSOrderedProductBillKOTMapper;

	@Autowired
	RestaurantPOSBillingInfoKOTMapper restaurantPOSBillingInfoKOTMapper;

	@Autowired
	private JsonUtils utils;

	@Transactional
	public String save(RestaurantPOSBillingInfoKOTDto restaurantPOSBillingInfoKOTDto, int hotelId) {

		Optional<Hotel> hotel = hotelRepository.findById(hotelId);
		if (!hotel.isPresent() || hotelId == 0) {
			utils.objectMapperError("hotel id is not present");

			Optional<Customer> customer = customerRepository.findById(restaurantPOSBillingInfoKOTDto.getCustomerId());
			if (!customer.isPresent() || restaurantPOSBillingInfoKOTDto.getCustomerId() == 0) {
				utils.objectMapperError("Customer id is not present");

				Optional<Member> member = memberRepository.findById(restaurantPOSBillingInfoKOTDto.getMemberId());
				if (!member.isPresent() || restaurantPOSBillingInfoKOTDto.getMemberId() == 0) {
					utils.objectMapperError("Member id is not present");
				}
			}
		}

		Double roundOff = (double) Math.round(restaurantPOSBillingInfoKOTDto.getGrandTotal());

		logger.info("saving restaurant pos bill info ");
		RestaurantPOSBillingInfoKOT restaurantPOSBillingInfoKOT = restaurantPOSBillingInfoKOTMapper
				.getRestaurantPOSBillingInfoKOTBuilder(restaurantPOSBillingInfoKOTDto, roundOff);
		restaurantPOSBillingInfoKOT.setHotel(new Hotel(hotelId));
		restaurantPOSBillingInfoKOTRepository.save(restaurantPOSBillingInfoKOT);

		for (DishKOTDto dish : restaurantPOSBillingInfoKOTDto.getDish()) {

			int dishId = dish.getDishId();

			Double rate = dish.getRate();
			int quantity = dish.getQuantity();
			Double amount = quantity * rate;
			int categoryId = dish.getCategoryId();

			Optional<Dish> dishes = dishRepository.findByDishId(dishId);

			if (!dishes.isPresent()) {

				return utils.objectMapperError("dish id is not present");
			}

			Optional<Category> category = categoryRepository.findById(categoryId);

			if (!category.isPresent()) {

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

			RestaurantPOSOrderedProductBillKOT restaurantPOSOrderedProductBillKOT = restaurantPOSOrderedProductBillKOTMapper
					.getRestaurantPOSOrderedProductBillKOTBuilder(restaurantPOSBillingInfoKOTDto, dish, hotelId, amount,
							vatPer, vatAmount, stPer, stAmount, scPer, scAmount, discount, discountAmount);
			restaurantPOSOrderedProductBillKOT.setHotel(new Hotel(hotelId));
			restaurantPOSOrderedProductBillKOTRepository.save(restaurantPOSOrderedProductBillKOT);

			tempMethod(restaurantPOSOrderedProductBillKOT.getHotelTable().getTableNo(), hotelId);

		}

		logger.info(" restaurant order bill info saved");
		return utils.objectMapperSuccess(" Restaurant Order bill info saved");

	}

	@Transactional
	private void tempMethod(int tableId, int hotelId) {
		tempRestaurantPOSOrderInfoKOTRepository.deleteByHotelTableAndHotel(new HotelTable(tableId),
				new Hotel(hotelId));
		tempRestaurantPOSOrderedProductKOTRepository.deleteByHotelTableAndHotel(new HotelTable(tableId),
				new Hotel(hotelId));

	}

}
