package com.sunan.pos.report;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunan.biling.kot.product.OrderedProductBillKOTRepository;
import com.sunan.billing.kot.info.BillingInfoKOTRepository;
import com.sunan.dish.DishRepository;
import com.sunan.hotel.HotelRepository;
import com.sunan.model.BillingInfoKOT;
import com.sunan.model.BillingOrderedProductKOT;
import com.sunan.model.Dish;
import com.sunan.model.Hotel;
import com.sunan.utils.JsonUtils;

@Service
public class POSReportService implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(POSReportService.class);

	@Autowired
	private BillingInfoKOTRepository billingInfoKotRepository;
	
	@Autowired
	private OrderedProductBillKOTRepository orderedProductBillKOTRepository;
	
	@Autowired
	private DishRepository dishRepository;

	@Autowired
	private HotelRepository hotelRepo;

	@Autowired
	private JsonUtils utils;

	@Transactional
	public String overAllReport( int hotelId, Date fromDate,
			Date toDate, String operator) {

		logger.info("Getting data for over all report..");

		// CALL HOTEL REPO BY ID
		Optional<Hotel> entity = hotelRepo.findById(hotelId);
		if (entity.isPresent()) {
			Double saleByOprator = billingInfoKotRepository.sumGrandTotalByOperatorAndHotel(operator,
					new Hotel(hotelId), fromDate, toDate);
			OverAllReportDto dto = new OverAllReportDto();
			dto.setHotelName(entity.get().getHotelName());
			dto.setHotelAddress(entity.get().getAddress1());
			dto.setContactNo(entity.get().getContactNo());
			dto.setEmail(entity.get().getEmail());
			Double cash = billingInfoKotRepository.sumGrandTotalByPaymentMode("cash", fromDate, toDate);
			Double wallet = billingInfoKotRepository.sumGrandTotalByPaymentMode("wallet", fromDate, toDate);
			Double card = billingInfoKotRepository.sumGrandTotalByPaymentMode("card", fromDate, toDate);
			Double dineIn = billingInfoKotRepository.sumGrandTotalByHotel(new Hotel(hotelId), fromDate, toDate);
			dto.setFromDate(fromDate);
			dto.setToDate(toDate);
			dto.setSaleByOprator(saleByOprator);
			dto.setCash(cash != null ? cash : 0);
			dto.setCard(card != null ? card : 0);
			dto.setWallet(wallet != null ? wallet : 0);
			dto.setDineIn(dineIn !=null ? dineIn:0);
			return utils.objectMapperSuccess(dto, "Over All billing report list.");
		} else {
			logger.info("Service : Hotel not found");
			return utils.objectMapperSuccess("Hotel not found");
		}

	}

	@Transactional
	public String overAllReportOne(Integer pageNo, Integer pageSize, String sortBy, int hotelId, Date fromDate,
			Date toDate) {
		logger.info("Getting over all one report..");
		Optional<Hotel> entity = hotelRepo.findById(hotelId);
		if (entity.isPresent()) {
			
			OverAllReportOneDto dto=new OverAllReportOneDto();
			dto.setHotelName(entity.get().getHotelName());
			dto.setHotelAddress(entity.get().getAddress1());
			dto.setContactNo(entity.get().getContactNo());
			dto.setEmail(entity.get().getEmail());
			dto.setFromDate(fromDate);
			dto.setToDate(toDate);
			Double grossTotal=0.0;
		List<BillingInfoKOT> billingInfoKot=billingInfoKotRepository.findByBillDate(fromDate, toDate);
		for (BillingInfoKOT billingInfoKOT2 : billingInfoKot) {
			
			List<BillingOrderedProductKOT> billingOrderedProductKot=orderedProductBillKOTRepository.findByBillId(new BillingInfoKOT(billingInfoKOT2.getId()));
			for (BillingOrderedProductKOT billingOrderedProductKot2 : billingOrderedProductKot) {
				Dish dish =dishRepository.findByDishName(billingOrderedProductKot2.getDish());
				List<ProductBillReportDto> list=new ArrayList<>();
				list.add(ProductBillReportDto.builder()
						.categoryName(dish.getCategory().getCategoryName())
						.dishName(billingOrderedProductKot2.getDish())
						.quantity(billingOrderedProductKot2.getQuantity())
						.rate(billingOrderedProductKot2.getRate())
						.amount(billingOrderedProductKot2.getAmount())
						.build());
				grossTotal+=billingOrderedProductKot2.getAmount();
			}
		}	
		

		}else {
			logger.info("Service : Hotel not found");
			return utils.objectMapperSuccess("Hotel not found");
		}
		
		
		return null;
	}
	
	
	

}
