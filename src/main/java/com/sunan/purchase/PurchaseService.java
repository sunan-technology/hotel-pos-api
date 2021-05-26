package com.sunan.purchase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.google.common.base.Predicates;
import com.sunan.exception.BadRequestException;
import com.sunan.hotel.HotelRepository;
import com.sunan.model.Hotel;
import com.sunan.model.PerchaseJoin;
import com.sunan.model.PerchaseJoin_;
import com.sunan.model.Purchase;
import com.sunan.model.Supplier;
import com.sunan.model.Warehouses;
import com.sunan.purchase.join.AvailableRawMatrialDto;
import com.sunan.purchase.join.PurchaseDetailsDto;
import com.sunan.purchase.join.PurchaseJoinDto;
import com.sunan.purchase.join.PurchaseJoinRepository;
import com.sunan.supplier.SupplierRepository;
import com.sunan.utils.JsonUtils;
import com.sunan.warehouse.WarehousesRepository;

@Service
public class PurchaseService implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(PurchaseService.class);

	@Autowired
	private PurchaseRepository purchaseRepository;

	@Autowired
	private PurchaseJoinRepository purchaseJoinRepository;

	@Autowired
	private HotelRepository hotelRepository;


	@Autowired
	private WarehousesRepository warehousesRepository;

	@Autowired
	private SupplierRepository supplierRepository;

	@Autowired
	PurchaseMapper purchaseMapper;

	@Autowired
	private JsonUtils utils;

	private void validateSavePurchaseJoinRequest(PurchaseDto purchaseDto, int hotelId) {

		Optional<Hotel> hotel = hotelRepository.findById(hotelId);
		if (!hotel.isPresent() || hotelId == 0) {
			throw new BadRequestException("hotel not found");
		}

//		Optional<Warehouses> warehouses = warehousesRepository.findById(purchaseDto.getWarehousesId());
//		if (!warehouses.isPresent() || purchaseDto.getWarehousesId() == 0) {
//			throw new BadRequestException("Warehouse not found");
//		}
//
//		Optional<Product> product = productRepository.findById(purchaseDto.getProductId());
//		if (!product.isPresent() || purchaseDto.getProductId() == 0) {
//			throw new BadRequestException("product not found");
//		}
	}

	@Transactional
	public String save(PurchaseDto purchaseDto, int hotelId) {

		Optional<Hotel> hotel = hotelRepository.findById(hotelId);
		if (hotel.isPresent()) {
			Optional<Supplier> supplier = supplierRepository.findById(purchaseDto.getSupplierId());
			if (supplier.isPresent()) {
				Double roundOff = (double) Math.round(purchaseDto.getGrandTotal());
				Purchase purchase = purchaseMapper.getPerchaseBuilder(purchaseDto, roundOff);
				purchase.setHotel(new Hotel(hotelId));
				purchaseRepository.save(purchase);
				logger.info("Service: perchase details saved");

				validateSavePurchaseJoinRequest(purchaseDto, hotelId);
				List<PerchaseJoin> perchaseJoin = purchaseMapper.getPurchaseJoin(purchaseDto.getPurchaseJoinDtos(), purchase.getId(),hotelId);
			
				purchaseJoinRepository.saveAll(perchaseJoin);

				logger.info("Service: purchase details");
				return utils.objectMapperSuccess(
						" Purchase Details Saved");
			} else {
				logger.info("Service: supplier not found");
				return utils.objectMapperError("Supplier not found");
			}
		} else {
			logger.info("Service: hotel not found");
			return utils.objectMapperError("Hotel not found");
		}

	}
	
	
	@Transactional
	public String findActiveList(String searchTerm, Integer pageNo, Integer pageSize, String sortBy,int hotelId) {
		logger.info("Service: Fetching list of stock purchase details ");
		PageRequest pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Purchase> pagedResult = null;
	
			pagedResult = purchaseRepository.findByIsActiveAndHotel("yes", pageable,new Hotel(hotelId));
		

		Page<PurchaseDto> page = pagedResult.map(new Function<Purchase, PurchaseDto>() {
			@Override
			public PurchaseDto apply(Purchase entity) {
				List<PerchaseJoin> perchaseJoin=purchaseJoinRepository.findByPurchase(new Purchase(entity.getId()));
				
				List<PurchaseJoinDto> purchaseJoinDto=purchaseMapper.getPurchaseJoinDtoBuilder(perchaseJoin);
				
				PurchaseDto dto = purchaseMapper.getPurchaseDtoBuilder(entity, purchaseJoinDto);
				return dto;
			}
		});
		logger.info("Service: Fetching list of stock purchase details details, total records: {}", page.getTotalElements());
		return utils.objectMapperSuccess(page, "All Acive Purchase Join list.");
	}

	@Transactional
	public String findAllPurchaseJoinList(String searchTerm, Integer pageNo, Integer pageSize, String sortBy,
			int hotelId) {
		
		logger.info("Service: Fetching list of stock purchase details ");
		PageRequest pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<PerchaseJoin> pagedResult = null;
	
			pagedResult = purchaseJoinRepository.findByIsActiveAndHotel("yes", pageable,new Hotel(hotelId));
			Page<PurchaseJoinDto> page = pagedResult.map(new Function<PerchaseJoin, PurchaseJoinDto>() {
				@Override
				public PurchaseJoinDto apply(PerchaseJoin entity) {
					
					PurchaseJoinDto dto = purchaseMapper.getPurchaseJoinDto(entity);
					return dto;
				}
			});
			logger.info("Service: Fetching list of stock purchase join details details, total records: {}", page.getTotalElements());
			return utils.objectMapperSuccess(page, "All Acive Purchase  join list.");
	}
	
	@Transactional
	public String getAllAvailablePurchaseRawMatrial(int warehouseId,int hotelId) {
		logger.info("Service : fetching list of available raw matrial ");
		Specification<PerchaseJoin> specification = new Specification<PerchaseJoin>() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<PerchaseJoin> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				if (warehouseId>0) {
					Predicate predicate = criteriaBuilder.equal(root.get(PerchaseJoin_.WAREHOUSES), new Warehouses(warehouseId));
					predicates.add(predicate);
				}
				
				Predicate quantityPredicate = criteriaBuilder.greaterThan(root.get(PerchaseJoin_.QUANTITY), 0);
				predicates.add(quantityPredicate);
				
				Predicate hotelPredicate = criteriaBuilder.equal(root.get(PerchaseJoin_.HOTEL), new Hotel(hotelId));
				predicates.add(hotelPredicate);
				
				query.where(criteriaBuilder.and(predicates.toArray(new Predicate[] {}))/* .IN(INCLAUSE) */)

				.orderBy(criteriaBuilder.desc(root.get("id")));
					return query.getRestriction();
			}
		};
		List<PerchaseJoin> purchaseDetailList = purchaseJoinRepository.findAll(specification);
		HashMap<RawMatrialDto, List<PurchaseDetail>> result = new HashMap<>();
		for (PerchaseJoin perchaseJoin : purchaseDetailList) {
			RawMatrialDto rawMatrialDto = new RawMatrialDto();
			rawMatrialDto.setId(perchaseJoin.getRawMatrial().getId());
			rawMatrialDto.setName(perchaseJoin.getRawMatrial().getName());
			PurchaseDetail detail = new PurchaseDetail();
			detail.setExpiryDate(perchaseJoin.getPurchaseDate());
			detail.setId(perchaseJoin.getId());
			detail.setPurchaseDate(perchaseJoin.getPurchaseDate());
			detail.setQuantity(perchaseJoin.getQuantity());
			if(result.containsKey(rawMatrialDto)) {
				result.get(rawMatrialDto).add(detail);
			}else {
				List<PurchaseDetail> data = new ArrayList<>();
				data.add(detail);
				result.put(rawMatrialDto, data);
			}
			
		}
		
//		List<PerchaseJoin> perchaseJoin=purchaseJoinRepository.getAvailableRawMatrial(new Warehouses(warehouseId), new Hotel(hotelId));
//		List<PurchaseDetailsDto> purchaseDetails=purchaseMapper.getPurchaseDetailsDtoBuilder(perchaseJoin);
//		List<AvailableRawMatrialDto> availableRawMatrialDto =purchaseMapper.getAvailableRawMatrialDto(perchaseJoin, purchaseDetails);
	//	List<AvailableRawMatrialDto> availableRawMatrialDto =purchaseMapper.getAvailableRawMatrialDtoBulder(perchaseJoin);
		logger.info("Service : All Available raw matrial list");
		return utils.objectMapperSuccess(result, " All Available raw matrial list");
	}
}
