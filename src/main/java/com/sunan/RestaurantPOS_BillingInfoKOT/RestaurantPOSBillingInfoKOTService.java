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
import com.sunan.exception.BadRequestException;
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

	private void validateSaveRestaurantBillingInfoRequest(RestaurantPOSBillingInfoKOTDto restaurantPOSBillingInfoKOTDto,
			int hotelId) {

		Optional<Hotel> hotel = hotelRepository.findById(hotelId);
		if (!hotel.isPresent() || hotelId == 0) {
			throw new BadRequestException("hotel not found");
		}

		Optional<Customer> customer = customerRepository.findById(restaurantPOSBillingInfoKOTDto.getCustomerId());
		if (!customer.isPresent() || restaurantPOSBillingInfoKOTDto.getCustomerId() == 0) {
			throw new BadRequestException("Customer not found");
		}

		Optional<Member> member = memberRepository.findById(restaurantPOSBillingInfoKOTDto.getMemberId());
		if (!member.isPresent() || restaurantPOSBillingInfoKOTDto.getMemberId() == 0) {
			throw new BadRequestException("Member not found");
		}
	}

	@Transactional
	public String save(RestaurantPOSBillingInfoKOTDto restaurantPOSBillingInfoKOTDto, int hotelId) {
		// Request Validation
		validateSaveRestaurantBillingInfoRequest(restaurantPOSBillingInfoKOTDto, hotelId);

		Double roundOff = (double) Math.round(restaurantPOSBillingInfoKOTDto.getGrandTotal());

		RestaurantPOSBillingInfoKOT restaurantPOSBillingInfoKOT = restaurantPOSBillingInfoKOTMapper
				.getRestaurantPOSBillingInfoKOTBuilder(restaurantPOSBillingInfoKOTDto, roundOff);
		restaurantPOSBillingInfoKOT.setHotel(new Hotel(hotelId));
		logger.info("saving restaurant pos bill info KOT");
		restaurantPOSBillingInfoKOTRepository.save(restaurantPOSBillingInfoKOT);

		logger.info("saved restaurant pos bill info KOT, id: {}", restaurantPOSBillingInfoKOT.getId());

		for (DishKOTDto dish : restaurantPOSBillingInfoKOTDto.getDish()) {

			// check dish is exists or not
			Optional<Dish> dishOpt = dishRepository.findByDishId(dish.getDishId());
			if (dishOpt.isPresent()) {
				Double rate = dish.getRate();
				Double amount = dish.getQuantity() * rate;
				Optional<Category> category = categoryRepository.findById(dish.getCategoryId());
				RestaurantPOSOrderedProductBillKOT posOrderedProductBillKOT = restaurantPOSOrderedProductBillKOTMapper
						.getRestaurantPOSOrderedProductBillKOTBuilder(restaurantPOSBillingInfoKOTDto, dish, hotelId,
								amount, category.get(), dishOpt.get());
				posOrderedProductBillKOT.setHotel(new Hotel(hotelId));
				restaurantPOSOrderedProductBillKOTRepository.save(posOrderedProductBillKOT);
				// remove the record from the temp table
				tempMethod(posOrderedProductBillKOT.getHotelTable().getTableNo(), hotelId);
			}

		}

		logger.info(" restaurant order bill info saved");
		return utils.objectMapperSuccess(" Restaurant Order bill info saved");

	}

	@Transactional
	private void tempMethod(int tableId, int hotelId) {
		//Delete child 
		tempRestaurantPOSOrderInfoKOTRepository.deleteByHotelTableAndHotel(new HotelTable(tableId), new Hotel(hotelId));
		//Delete parent
		tempRestaurantPOSOrderedProductKOTRepository.deleteByHotelTableAndHotel(new HotelTable(tableId),
				new Hotel(hotelId));

	}

}
