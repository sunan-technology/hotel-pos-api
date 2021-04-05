package com.sunan.purchaseorder;

import java.io.Serializable;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunan.model.Hotel;
import com.sunan.model.PurchaseOrder;
import com.sunan.model.PurchaseOrderJoin;
import com.sunan.purchaseorder_join.PurchaseOrderJoinRepository;
import com.sunan.utils.JsonUtils;

@Service
public class PurchaseOrderService implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(PurchaseOrderService.class);

	@Autowired
	private PurchaseOrderRepository purchaseOrderRepository;

	@Autowired
	private PurchaseOrderJoinRepository purchaseOrderJoinRepository;

	@Autowired
	PurchaseOrderMapper purchaseOrderMapper;

	@Autowired
	private JsonUtils utils;

	@Transactional
	public String save(PurchaseOrderDto purchaseOrderDto,int hotelId) {

		PurchaseOrder purchaseOrder = purchaseOrderMapper.getPurchaseOrderBuilder(purchaseOrderDto);
		purchaseOrder.setHotelId(new Hotel(hotelId));
		purchaseOrderRepository.save(purchaseOrder);

		int purchaseOrderId = purchaseOrder.getId();


		PurchaseOrderJoin purchaseOrderJoin = purchaseOrderMapper.getPurchaseOrderJoin(purchaseOrderDto,
				purchaseOrderId);
		purchaseOrderJoin.setHotelId(new Hotel(hotelId));
		purchaseOrderJoinRepository.save(purchaseOrderJoin);

		logger.info("Service: purchase order details");
		return utils.objectMapperSuccess(
				purchaseOrderMapper.getPurchaseOrderDtoBuilder(purchaseOrder, purchaseOrderJoin),
				" Purchase order Details Saved");
	}

}
