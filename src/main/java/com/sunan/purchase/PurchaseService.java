package com.sunan.purchase;

import java.io.Serializable;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunan.model.Hotel;
import com.sunan.model.PerchaseJoin;
import com.sunan.model.Purchase;
import com.sunan.purchaseJoin.PurchaseJoinRepository;
import com.sunan.utils.JsonUtils;

@Service
public class PurchaseService implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(PurchaseService.class);

	@Autowired
	private PurchaseRepository purchaseRepository;

	@Autowired
	private PurchaseJoinRepository purchaseJoinRepository;

	@Autowired
	PurchaseMapper purchaseMapper;

	@Autowired
	private JsonUtils utils;

	@Transactional
	public String save(PurchaseDto purchaseDto,int hotelId) {

		Purchase purchase = purchaseMapper.getPerchaseBuilder(purchaseDto);
		purchase.setHotel(new Hotel(hotelId));
		purchaseRepository.save(purchase);

		int purchaseId = purchase.getId();
		

		PerchaseJoin perchaseJoin = purchaseMapper.getPerchaseJoin(purchaseDto, purchaseId);
		perchaseJoin.setHotel(new Hotel(hotelId));
		purchaseJoinRepository.save(perchaseJoin);

		logger.info("Service: purchase details");
		return utils.objectMapperSuccess(purchaseMapper.getPurchaseDtoBuilder(purchase, perchaseJoin),
				" Purchase Details Saved");
	}

}
