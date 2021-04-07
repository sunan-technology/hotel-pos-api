package com.sunan.billing.kot.info;

import java.io.Serializable;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunan.biling.kot.product.OrderedProductBillKOTMapper;
import com.sunan.biling.kot.product.OrderedProductBillKOTRepository;
import com.sunan.category.CategoryRepository;
import com.sunan.customer.CustomerRepository;
import com.sunan.dish.DishRepository;
import com.sunan.exception.BadRequestException;
import com.sunan.hotel.HotelRepository;
import com.sunan.member.MemberRepository;
import com.sunan.model.BillingInfoKOT;
import com.sunan.model.Category;
import com.sunan.model.Customer;
import com.sunan.model.Dish;
import com.sunan.model.Hotel;
import com.sunan.model.HotelTable;
import com.sunan.model.Member;
import com.sunan.model.OrderedProductBillKOT;
import com.sunan.order.kot.temp.info.DishKOTDto;
import com.sunan.order.kot.temp.info.TempOrderInfoKOTRepository;
import com.sunan.order.kot.temp.product.TempOrderedProductKOTRepository;
import com.sunan.utils.JsonUtils;

@Service
public class BillingInfoKOTService implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(BillingInfoKOTService.class);

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
	private TempOrderInfoKOTRepository tempOrderInfoKOTRepository;

	@Autowired
	private TempOrderedProductKOTRepository tempOrderedProductKOTRepository;

	@Autowired
	private BillingInfoKOTRepository billingInfoKOTRepository;

	@Autowired
	private OrderedProductBillKOTRepository orderedProductBillKOTRepository;

	@Autowired
	OrderedProductBillKOTMapper orderedProductBillKOTMapper;

	@Autowired
	BillingInfoKOTMapper billingInfoKOTMapper;

	@Autowired
	private JsonUtils utils;

	private void validateSaveRestaurantBillingInfoRequest(BillingInfoKOTDto billingInfoKOTDto,
			int hotelId) {

		Optional<Hotel> hotel = hotelRepository.findById(hotelId);
		if (!hotel.isPresent() || hotelId == 0) {
			throw new BadRequestException("hotel not found");
		}

		Optional<Customer> customer = customerRepository.findById(billingInfoKOTDto.getCustomerId());
		if (!customer.isPresent() || billingInfoKOTDto.getCustomerId() == 0) {
			throw new BadRequestException("Customer not found");
		}

		Optional<Member> member = memberRepository.findById(billingInfoKOTDto.getMemberId());
		if (!member.isPresent() || billingInfoKOTDto.getMemberId() == 0) {
			throw new BadRequestException("Member not found");
		}
	}

	@Transactional
	public String save(BillingInfoKOTDto billingInfoKOTDto, int hotelId) {
		// Request Validation
		validateSaveRestaurantBillingInfoRequest(billingInfoKOTDto, hotelId);

		Double roundOff = (double) Math.round(billingInfoKOTDto.getGrandTotal());

		BillingInfoKOT billingInfoKOT = billingInfoKOTMapper
				.getBillingInfoKOTBuilder(billingInfoKOTDto, roundOff);
		billingInfoKOT.setHotel(new Hotel(hotelId));
		logger.info("saving restaurant pos bill info KOT");
		billingInfoKOTRepository.save(billingInfoKOT);

		logger.info("saved restaurant pos bill info KOT, id: {}", billingInfoKOT.getId());

		for (DishKOTDto dish : billingInfoKOTDto.getDish()) {

			// check dish is exists or not
			Optional<Dish> dishOpt = dishRepository.findByDishId(dish.getDishId());
			if (dishOpt.isPresent()) {
				Double rate = dish.getRate();
				Double amount = dish.getQuantity() * rate;
				Optional<Category> category = categoryRepository.findById(dish.getCategoryId());
				OrderedProductBillKOT posOrderedProductBillKOT = orderedProductBillKOTMapper
						.getOrderedProductBillKOTBuilder(billingInfoKOTDto, dish, billingInfoKOT.getId(),
								amount, category.get(), dishOpt.get());
				posOrderedProductBillKOT.setHotel(new Hotel(hotelId));
				orderedProductBillKOTRepository.save(posOrderedProductBillKOT);
				// remove the record from the temp table
				tempOrderRemoveMethod(posOrderedProductBillKOT.getHotelTable().getTableNo(), hotelId);
			}

		}

		logger.info(" restaurant order bill info saved");
		return utils.objectMapperSuccess(" Restaurant Order bill info saved");

	}

	@Transactional
	private void tempOrderRemoveMethod(int tableId, int hotelId) {
		//Delete child 
		tempOrderInfoKOTRepository.deleteByHotelTableAndHotel(new HotelTable(tableId), new Hotel(hotelId));
		//Delete parent
		tempOrderedProductKOTRepository.deleteByHotelTableAndHotel(new HotelTable(tableId),
				new Hotel(hotelId));

	}

}
