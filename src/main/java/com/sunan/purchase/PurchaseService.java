package com.sunan.purchase;

import java.io.Serializable;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunan.exception.BadRequestException;
import com.sunan.hotel.HotelRepository;
import com.sunan.model.Hotel;
import com.sunan.model.PerchaseJoin;
import com.sunan.model.Product;
import com.sunan.model.Purchase;
import com.sunan.model.StorageType;
import com.sunan.model.Supplier;
import com.sunan.model.Warehouses;
import com.sunan.product.ProductRepository;
import com.sunan.purchase.join.PurchaseJoinRepository;
import com.sunan.storage.type.StorageTypeRepository;
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
	private StorageTypeRepository storageTypeRepository;

	@Autowired
	private ProductRepository productRepository;

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

		Optional<StorageType> storageType = storageTypeRepository.findById(purchaseDto.getStorageTypeId());
		if (!storageType.isPresent() || purchaseDto.getStorageTypeId() == 0) {
			throw new BadRequestException("Storage type not found");
		}

		Optional<Warehouses> warehouses = warehousesRepository.findById(purchaseDto.getWarehousesId());
		if (!warehouses.isPresent() || purchaseDto.getWarehousesId() == 0) {
			throw new BadRequestException("Warehouse not found");
		}

		Optional<Product> product = productRepository.findById(purchaseDto.getProductId());
		if (!product.isPresent() || purchaseDto.getProductId() == 0) {
			throw new BadRequestException("product not found");
		}
	}

	@Transactional
	public String save(PurchaseDto purchaseDto, int hotelId) {

		Optional<Hotel> hotel = hotelRepository.findById(hotelId);
		if (hotel.isPresent()) {
			Optional<Supplier> supplier = supplierRepository.findById(purchaseDto.getSupplierId());
			if (supplier.isPresent()) {
				Double roundOff = (double) Math.round(purchaseDto.getGrandTotal());
				Purchase purchase = purchaseMapper.getPerchaseBuilder(purchaseDto,roundOff);
				purchase.setHotel(new Hotel(hotelId));
				purchaseRepository.save(purchase);
				logger.info("Service: perchase details saved");

				validateSavePurchaseJoinRequest(purchaseDto, hotelId);
				PerchaseJoin perchaseJoin = purchaseMapper.getPerchaseJoin(purchaseDto, purchase.getId());
				perchaseJoin.setHotel(new Hotel(hotelId));
				purchaseJoinRepository.save(perchaseJoin);

				logger.info("Service: purchase details");
				return utils.objectMapperSuccess(purchaseMapper.getPurchaseDtoBuilder(purchase, perchaseJoin),
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
}
