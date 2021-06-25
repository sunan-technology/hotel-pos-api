package com.sunan.pos.report;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
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
import com.sunan.model.InternalTransfer_;
import com.sunan.model.Kitchen;
import com.sunan.model.PerchaseJoin;
import com.sunan.model.PerchaseJoin_;
import com.sunan.model.Purchase;
import com.sunan.model.Purchase_;
import com.sunan.model.Supplier;
import com.sunan.purchase.PurchaseDto;
import com.sunan.purchase.PurchaseMapper;
import com.sunan.purchase.PurchaseRepository;
import com.sunan.purchase.join.PurchaseJoinDto;
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
	PurchaseMapper purchaseMapper;

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
	public String purchaseStockReport(Date fromDate, Date toDate, int supplierId, 
			  int hotelId) {
		logger.info("Service : fetching purchase stock list");
		
		Specification<Purchase> specificationPurchaseStock = new Specification<Purchase>() {			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Purchase> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<Predicate>(); 	
				
//				Join<PerchaseJoin, Purchase> join = root.join(PerchaseJoin_.PURCHASE,
//						JoinType.LEFT);
				Predicate predicate1 =    criteriaBuilder.equal(root.get(Purchase_.IS_ACTIVE),"yes");
				predicates.add(predicate1);
				if (supplierId > 0) {
					
					Predicate predicate = criteriaBuilder.equal(root.get(Purchase_.SUPPLIER), new Supplier(supplierId));
					predicates.add(predicate);
				}
				
				if(hotelId >0) {
					Predicate predicate = criteriaBuilder.equal(root.get(Purchase_.HOTEL), new Hotel(hotelId));
					predicates.add(predicate);
				}
				
				if (fromDate != null && toDate != null) {
//					Join<StudentSession, Student> join = root.join(StudentSession_.STUDENT,JoinType.LEFT);
					Predicate date = criteriaBuilder.between(root.get(Purchase_.DATE), fromDate, toDate);
					predicates.add(date);
				}
				
				query.where(criteriaBuilder.and(predicates.toArray(new Predicate[] {}))/* .IN(INCLAUSE) */)

				.orderBy(criteriaBuilder.desc(root.get("id")));
					return query.getRestriction();
			}
			
		};
	//	List<Purchase> purchase=purchaseRepository.getPurchaseReport(fromDate, toDate, new Supplier(supplierId) , new Hotel(hotelId));
		List<Purchase> purchase=purchaseRepository.findAll(specificationPurchaseStock);
		
		List<PurchaseDto> dto= purchaseMapper.getPurchaseDtoList(purchase);
		
		return utils.objectMapperSuccess(dto, "Purchase stock report list.");
	}

	@Transactional
	public String internalTransferReport(Date fromDate, Date toDate, int kitchenId,
		 int hotelId) {
		logger.info("Service : fetching internal transfer list");
		List<InternalTransfer> internalTransfer=internalTransferRepository.getInternalTransferReport(fromDate, toDate, new Kitchen(kitchenId), new Hotel(hotelId));		
		return utils.objectMapperSuccess(internalTransfer, "Internal transfer report list.");
	}

	@Transactional
	public String internalSupplierReport(Date fromDate, Date toDate, int supplierId, String purchaseType, int hotelId) {
		logger.info("Service : fetching supplier list");
		
		
		Specification<PerchaseJoin> specificationSupplier = new Specification<PerchaseJoin>() {			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<PerchaseJoin> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<Predicate>(); 	
				
				Join<PerchaseJoin, Purchase> join = root.join(PerchaseJoin_.PURCHASE,
						JoinType.LEFT);
				Predicate predicate1 =    criteriaBuilder.equal(join.get(Purchase_.IS_ACTIVE),"yes");
				predicates.add(predicate1);
				if (supplierId > 0) {
					
					Predicate predicate = criteriaBuilder.equal(join.get(Purchase_.SUPPLIER), new Supplier(supplierId));
					predicates.add(predicate);
				}
				if(hotelId >0) {
					Predicate predicate = criteriaBuilder.equal(root.get(Purchase_.HOTEL), new Hotel(hotelId));
					predicates.add(predicate);
				}
				
				if (fromDate != null && toDate != null) {
//					Join<StudentSession, Student> join = root.join(StudentSession_.STUDENT,JoinType.LEFT);
					Predicate date = criteriaBuilder.between(join.get(Purchase_.DATE), fromDate, toDate);
					predicates.add(date);
				}
				
				query.where(criteriaBuilder.and(predicates.toArray(new Predicate[] {}))/* .IN(INCLAUSE) */)

				.orderBy(criteriaBuilder.desc(root.get("id")));
					return query.getRestriction();
			}
			
		};
		
		List<PerchaseJoin> supplier=purchaseJoinRepository.findAll(specificationSupplier);	
//	List<Purchase> supplier=purchaseRepository.getSupplierReport(fromDate, toDate, new Supplier(supplierId), purchaseType, new Hotel(hotelId));
		HashMap<SupplierReportDto, List<SupplierMatrialReportDto>> result = new HashMap<>();
		for(PerchaseJoin perchaseJoin :supplier) {
			SupplierReportDto supplierDto=new SupplierReportDto();
			supplierDto.setInvoiceNo(perchaseJoin.getPurchase().getInvoiceNo());
			supplierDto.setPurchaseAmount(perchaseJoin.getPurchase().getGrandTotal());
			supplierDto.setPurchaseDate(perchaseJoin.getPurchase().getDate());
			supplierDto.setSupplierContact(perchaseJoin.getPurchase().getSupplier().getContactNo());
			supplierDto.setSupplierName(perchaseJoin.getPurchase().getSupplier().getSupplierName());
			
			SupplierMatrialReportDto supplierMatrial=new SupplierMatrialReportDto();
			supplierMatrial.setRawMatrialName(perchaseJoin.getRawMatrialName());
			supplierMatrial.setQuantity(perchaseJoin.getPurchaseQuantity());
			supplierMatrial.setExpiryDate(perchaseJoin.getExpiryDate());
			
			if(result.containsKey(supplierDto)) {
				result.get(supplierDto).add(supplierMatrial);
			}else {
				List<SupplierMatrialReportDto> dto=new ArrayList<>();
				dto.add(supplierMatrial);
				result.put(supplierDto, dto);
			}
		}
		logger.info("Service : Supplier report list.");
		return utils.objectMapperSuccess(result, "Supplier report list.");
	}

	@Transactional
	public String getItemWiseinternalTransferReport(Date fromDate, Date toDate, int kitchenId, String rawMatrialName,
			int hotelId) {
		logger.info("Service : fetching item wise internal transfer list");
		
		Specification<InternalTransfer> specification = new Specification<InternalTransfer>() {			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<InternalTransfer> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<Predicate>(); 	
				
//				Join<PerchaseJoin, Purchase> join = root.join(PerchaseJoin_.PURCHASE,
//						JoinType.LEFT);
				Predicate predicate1 =    criteriaBuilder.equal(root.get(InternalTransfer_.IS_ACTIVE),"yes");
				predicates.add(predicate1);
				if (kitchenId > 0) {
					
					Predicate predicate = criteriaBuilder.equal(root.get(InternalTransfer_.KITCHEN), new Kitchen(kitchenId));
					predicates.add(predicate);
				}
				
				if(hotelId >0) {
					Predicate predicate = criteriaBuilder.equal(root.get(Purchase_.HOTEL), new Hotel(hotelId));
					predicates.add(predicate);
				}
				
				if (fromDate != null && toDate != null) {
//					Join<StudentSession, Student> join = root.join(StudentSession_.STUDENT,JoinType.LEFT);
					Predicate date = criteriaBuilder.between(root.get(InternalTransfer_.INVOICE_DATE), fromDate, toDate);
					predicates.add(date);
				}
				
				query.where(criteriaBuilder.and(predicates.toArray(new Predicate[] {}))/* .IN(INCLAUSE) */)

				.orderBy(criteriaBuilder.desc(root.get("id")));
					return query.getRestriction();
			}
			
		};
		List<InternalTransfer> internalTransfer=internalTransferRepository.findAll(specification);
		//List<InternalTransfer> internalTransfer=internalTransferRepository.getInternalTransferReportList(fromDate, toDate, new Kitchen(kitchenId), new Hotel(hotelId));
		List<InternalTransferJoin> list=new ArrayList<InternalTransferJoin >();
		for (InternalTransfer internalTransfer2 : internalTransfer) {
		InternalTransferJoin internaltransferjoin=	internalTransferJoinRepository.findByRawMatrialNameAndInternalTransfer(rawMatrialName, new InternalTransfer(internalTransfer2.getId()));
		if(internaltransferjoin != null)	
		list.add(internaltransferjoin);
		}
		logger.info("Service : All Available raw matrial list");
		return utils.objectMapperSuccess(list, "Item Wise Internal Tansfer list");
	}

//	
//	@Transactional
//	public String getCurrentStock(int rawmatrialId, int hotelId,int warehouseId) {
//		logger.info("Service : fetcing raw matrial list");
//		List<PerchaseJoin> rawMatrialList = new ArrayList<>();
//		if (rawmatrialId > 0) {
//			rawMatrialList
//					.addAll(purchaseJoinRepository.getRawmatrial(new RawMatrial(rawmatrialId),new Warehouses(warehouseId), new Hotel(hotelId)));
//		} else {
//			rawMatrialList.addAll(purchaseJoinRepository.findByQuantityGreaterThanAndHotel(0, new Hotel(hotelId)));
//		}
//		List<RawatrialDto> dto = purchaseMapper.getRawmatrialDtoBuilder(rawMatrialList);
//		logger.info("Service : All Available raw matrial list");
//		return utils.objectMapperSuccess(dto, " All Available raw matrial list");
//	}

	@Transactional
	public String internalTransferReportList(Date fromDate, Date toDate, int kitchenId, int hotelId) {
		logger.info("Service : fetching internal transfer list");
		Specification<InternalTransfer> specification = new Specification<InternalTransfer>() {			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<InternalTransfer> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<Predicate>(); 	
				
//				Join<PerchaseJoin, Purchase> join = root.join(PerchaseJoin_.PURCHASE,
//						JoinType.LEFT);
				Predicate predicate1 =    criteriaBuilder.equal(root.get(InternalTransfer_.IS_ACTIVE),"yes");
				predicates.add(predicate1);
				if (kitchenId > 0) {
					
					Predicate predicate = criteriaBuilder.equal(root.get(InternalTransfer_.KITCHEN), new Kitchen(kitchenId));
					predicates.add(predicate);
				}
				
				if(hotelId >0) {
					Predicate predicate = criteriaBuilder.equal(root.get(Purchase_.HOTEL), new Hotel(hotelId));
					predicates.add(predicate);
				}
				
				if (fromDate != null && toDate != null) {
//					Join<StudentSession, Student> join = root.join(StudentSession_.STUDENT,JoinType.LEFT);
					Predicate date = criteriaBuilder.between(root.get(InternalTransfer_.INVOICE_DATE), fromDate, toDate);
					predicates.add(date);
				}
				
				query.where(criteriaBuilder.and(predicates.toArray(new Predicate[] {}))/* .IN(INCLAUSE) */)

				.orderBy(criteriaBuilder.desc(root.get("id")));
					return query.getRestriction();
			}
			
		};
		List<InternalTransfer> internalTransfer=internalTransferRepository.findAll(specification);
		//List<InternalTransfer> internalTransfer=internalTransferRepository.getInternalTransferReportList(fromDate, toDate, new Kitchen(kitchenId), new Hotel(hotelId));
		return utils.objectMapperSuccess(internalTransfer, "Internal transfer report list.");
	}

	@Transactional
	public String getPurchaseJoinReportByPurchaseId(int purchaseid, int hotelId) {
		logger.info("Service : fetching purchase join report details");
		Optional<Purchase> optional = purchaseRepository.findById(purchaseid);
		if(optional.isPresent()) {
			List<PerchaseJoin> purchaseJoin=purchaseJoinRepository.findByPurchase(new Purchase(purchaseid));
			List<PurchaseJoinDto> dto =purchaseMapper.getPurchaseJoinDto(purchaseJoin);
			return utils.objectMapperSuccess(dto, "Purchase join report Details");
		}
		
		logger.info("Service: purchase details not found with id {}", purchaseid);
		return utils.objectMapperError("Purchase Details Not found, Id :" + purchaseid);
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
