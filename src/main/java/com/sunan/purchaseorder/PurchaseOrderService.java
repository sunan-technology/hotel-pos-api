package com.sunan.purchaseorder;

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
import com.sunan.model.Product;
import com.sunan.model.PurchaseOrder;
import com.sunan.model.PurchaseOrderJoin;
import com.sunan.model.Supplier;
import com.sunan.product.ProductRepository;
import com.sunan.purchaseorder.join.PurchaseOrderJoinRepository;
import com.sunan.storage.type.StorageTypeRepository;
import com.sunan.supplier.SupplierRepository;
import com.sunan.utils.JsonUtils;
import com.sunan.warehouse.WarehousesRepository;

@Service
public class PurchaseOrderService implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(PurchaseOrderService.class);

	@Autowired
	private PurchaseOrderRepository purchaseOrderRepository;

	@Autowired
	private PurchaseOrderJoinRepository purchaseOrderJoinRepository;

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
	PurchaseOrderMapper purchaseOrderMapper;

	@Autowired
	private JsonUtils utils;

	private void validatePurchaseOrderJoinSaveRequest(int hotelId, int productId) {

		Optional<Hotel> hotel = hotelRepository.findById(hotelId);
		if (!hotel.isPresent() || hotelId == 0) {
			throw new BadRequestException("hotel not found");
		}

		Optional<Product> product = productRepository.findById(productId);
		if (!product.isPresent() || productId == 0) {
			throw new BadRequestException("product not found");
		}
	}

	@Transactional
	public String save(PurchaseOrderDto purchaseOrderDto, int hotelId) {

		Optional<Hotel> hotel = hotelRepository.findById(hotelId);
		if (hotel.isPresent()) {
			Optional<Supplier> supplier = supplierRepository.findById(purchaseOrderDto.getSupplierId());
			if (supplier.isPresent()) {
				PurchaseOrder purchaseOrder = purchaseOrderMapper.getPurchaseOrderBuilder(purchaseOrderDto);
				purchaseOrder.setHotel(new Hotel(hotelId));
				purchaseOrderRepository.save(purchaseOrder);

				validatePurchaseOrderJoinSaveRequest(hotelId, purchaseOrderDto.getProductId());
				PurchaseOrderJoin purchaseOrderJoin = purchaseOrderMapper.getPurchaseOrderJoin(purchaseOrderDto,
						purchaseOrder.getId());
				purchaseOrderJoin.setHotel(new Hotel(hotelId));
				purchaseOrderJoinRepository.save(purchaseOrderJoin);

				logger.info("Service: purchase order details");
				return utils.objectMapperSuccess(
						purchaseOrderMapper.getPurchaseOrderDtoBuilder(purchaseOrder, purchaseOrderJoin),
						" Purchase order Details Saved");
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
