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
import com.sunan.constants.DefaultConstantValues;
import com.sunan.dish.DishRepository;
import com.sunan.hotel.HotelRepository;
import com.sunan.internal.transfer.InternalTransferMapper;
import com.sunan.internal.transfer.InternalTransferRepository;
import com.sunan.internal.transfer.join.InternalTransferJoinRepository;
import com.sunan.model.Hotel;
import com.sunan.model.InternalTransfer;
import com.sunan.model.InternalTransferJoin;
import com.sunan.model.Kitchen;
import com.sunan.model.Purchase;
import com.sunan.model.Supplier;
import com.sunan.purchase.PurchaseRepository;
import com.sunan.purchase.join.PurchaseJoinRepository;
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
	private PurchaseJoinRepository purchaseJoinRepository;
	
	@Autowired
	private InternalTransferRepository internalTransferRepository;
	
	@Autowired
	private InternalTransferJoinRepository internalTransferJoinRepository;
	
	@Autowired
	InternalTransferMapper internalTransferMapper;
	
	@Autowired
	private PurchaseRepository purchaseRepository;

	@Autowired
	private HotelRepository hotelRepo;

	@Autowired
	private JsonUtils utils;

	@Autowired
	private OverAllReportMapper overAllReportMapper;
	
	@Transactional
	public String overAllReport( int hotelId, Date fromDate,
			Date toDate, String operator) {

		logger.info("Getting data for over all report..");

		// CALL HOTEL REPO BY ID
		Optional<Hotel> entity = hotelRepo.findById(hotelId);
		if (entity.isPresent()) {
			Double saleByOprator = billingInfoKotRepository.sumGrandTotalByOperatorAndHotel(operator,
					new Hotel(hotelId), fromDate, toDate);

			OverAllReportDto dto = overAllReportMapper.getOverAllReportDtoMapper(entity.get(),
					getsumGrandTotalByPaymentModeAndDate(DefaultConstantValues.CASH, fromDate, toDate),
					getsumGrandTotalByPaymentModeAndDate(DefaultConstantValues.WALLET, fromDate, toDate),
					getsumGrandTotalByPaymentModeAndDate(DefaultConstantValues.CARD, fromDate, toDate),
					getSumGrandTotalByHotel(hotelId, fromDate, toDate), saleByOprator, fromDate, toDate);
			return utils.objectMapperSuccess(dto, "Over All billing report list.");
		} else {
			logger.info("Service : Hotel not found");
			return utils.objectMapperError("Hotel not found");
		}

	}
	
	private Double getSumGrandTotalByHotel(int hotelId, Date fromDate, Date toDate) {
		Double dineInTotal = billingInfoKotRepository.sumGrandTotalByHotel(new Hotel(hotelId), fromDate, toDate);
		if (dineInTotal != null) {
			return dineInTotal;
		}
		return DefaultConstantValues.DEFAULT_DOUBLE_VALUE;
	}

	private Double getsumGrandTotalByPaymentModeAndDate(String paymentMode, Date fromDate, Date toDate) {
		Double grandTotal = billingInfoKotRepository.sumGrandTotalByPaymentMode(paymentMode, fromDate, toDate);
		if (grandTotal != null) {
			return grandTotal;
		}
		return DefaultConstantValues.DEFAULT_DOUBLE_VALUE;
	}

	@Transactional
	public String purchaseStockReport(Date fromDate, Date toDate, int supplierId, String invoiceNo, String payment,
			 String purchaseType, int hotelId) {
		logger.info("Service : fetching purchase stock list");
		List<Purchase> purchase=purchaseRepository.getPurchaseReport(fromDate, toDate, new Supplier(supplierId), invoiceNo, payment, new Hotel(hotelId));
		
		return utils.objectMapperSuccess(purchase, "Purchase stock report list.");
	}

	@Transactional
	public String internalTransferReport(Date fromDate, Date toDate, int kitchenId, String invoiceNo, String payment,
			String purchaseType, int hotelId) {
		logger.info("Service : fetching internal transfer list");
		List<InternalTransfer> internalTransfer=internalTransferRepository.getInternalTransferReport(fromDate, toDate, new Kitchen(kitchenId), invoiceNo, payment, new Hotel(hotelId));		
		return utils.objectMapperSuccess(internalTransfer, "Internal transfer report list.");
	}

	@Transactional
	public String internalSupplierReport(Date fromDate, Date toDate, int supplierId, String purchaseType, int hotelId) {
		logger.info("Service : fetching supplier list");
		
		
	List<Purchase> supplier=purchaseRepository.getSupplierReport(fromDate, toDate, new Supplier(supplierId), purchaseType, new Hotel(hotelId));
		
		return utils.objectMapperSuccess(supplier, "Supplier report list.");
	}

	@Transactional
	public String getItemWiseinternalTransferReport(Date fromDate, Date toDate, int kitchenId, String rawMatrialName,
			int hotelId) {
		logger.info("Service : fetching item wise internal transfer list");
		
		List<InternalTransfer> internalTransfer=internalTransferRepository.getInternalTransferReportList(fromDate, toDate, new Kitchen(kitchenId), new Hotel(hotelId));
		List<InternalTransferJoin> list=new ArrayList<InternalTransferJoin >();
		for (InternalTransfer internalTransfer2 : internalTransfer) {
		InternalTransferJoin internaltransferjoin=	internalTransferJoinRepository.findByRawMatrialNameAndInternalTransfer(rawMatrialName, new InternalTransfer(internalTransfer2.getId()));
		if(internaltransferjoin != null)	
		list.add(internaltransferjoin);
		}
		
		return utils.objectMapperSuccess(list, "Item Wise Internal Tansfer list");
	}

	
	@Transactional
	public String getCurrentStock(String rawMatrialName, int hotelId) {
		logger.info("Service : fetching current Stock details");
		int quantity=purchaseJoinRepository.sumQuantityByHotelAndRawMatrialName(new Hotel(hotelId), rawMatrialName);
		
		CurrentStockDto dto =overAllReportMapper.getCurrentStockDto(quantity, rawMatrialName);
		return utils.objectMapperSuccess(dto, "Current Stock details");
	}

	@Transactional
	public String internalTransferReportList(Date fromDate, Date toDate, int kitchenId, int hotelId) {
		logger.info("Service : fetching internal transfer list");
		List<InternalTransfer> internalTransfer=internalTransferRepository.getInternalTransferReportList(fromDate, toDate, new Kitchen(kitchenId), new Hotel(hotelId));
		return utils.objectMapperSuccess(internalTransfer, "Internal transfer report list.");
	}
	
	
	
	
//	@Transactional
//	public String overAllReportOne(Integer pageNo, Integer pageSize, String sortBy, int hotelId, Date fromDate,
//			Date toDate) {
//		logger.info("Getting over all one report..");
//		Optional<Hotel> entity = hotelRepo.findById(hotelId);
//		if (entity.isPresent()) {
//			
//			OverAllReportOneDto dto=new OverAllReportOneDto();
//			dto.setHotelName(entity.get().getHotelName());
//			dto.setHotelAddress(entity.get().getAddress1());
//			dto.setContactNo(entity.get().getContactNo());
//			dto.setEmail(entity.get().getEmail());
//			dto.setFromDate(fromDate);
//			dto.setToDate(toDate);
//			Double grossTotal= DefaultConstantValues.DEFAULT_DOUBLE_VALUE;
//		List<BillingInfoKOT> billingInfoKot=billingInfoKotRepository.findByBillDate(fromDate, toDate);
//		for (BillingInfoKOT billingInfoKOT2 : billingInfoKot) {
//			
//			List<BillingOrderedProductKOT> billingOrderedProductKot=orderedProductBillKOTRepository.findByBillId(new BillingInfoKOT(billingInfoKOT2.getId()));
//			for (BillingOrderedProductKOT billingOrderedProductKot2 : billingOrderedProductKot) {
//				Dish dish =dishRepository.findByDishName(billingOrderedProductKot2.getDish());
//				List<ProductBillReportDto> list=new ArrayList<>();
//				list.add(ProductBillReportDto.builder()
//						.categoryName(dish.getCategory().getCategoryName())
//						.dishName(billingOrderedProductKot2.getDish())
//						.quantity(billingOrderedProductKot2.getQuantity())
//						.rate(billingOrderedProductKot2.getRate())
//						.amount(billingOrderedProductKot2.getAmount())
//						.build());
//				grossTotal+=billingOrderedProductKot2.getAmount();
//			}
//		}	
//		
//
//		}else {
//			logger.info("Service : Hotel not found");
//			return utils.objectMapperError("Hotel not found");
//		} 
//		return null;
//	}
//	
//	
	

}
