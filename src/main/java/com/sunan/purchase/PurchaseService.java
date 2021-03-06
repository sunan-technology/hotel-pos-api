package com.sunan.purchase;

import java.io.Serializable;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunan.model.PerchaseJoin;
import com.sunan.model.Purchase;
import com.sunan.purchaseJoin.PurchaseJoinDto;
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
	public String save(PurchaseDto purchaseDto) {
		
		Purchase purchase=purchaseMapper.getPerchaseBuilder(purchaseDto);
		purchaseRepository.save(purchase);
		
		int purchaseId= purchase.getId();
		PurchaseJoinDto purchaseJoinDto=new PurchaseJoinDto();
		purchaseJoinDto.setPerchaseId(purchaseId);
		purchaseJoinDto.setProductId(purchaseDto.getProductId());
		purchaseJoinDto.setQuantity(purchaseDto.getQuantity());
		purchaseJoinDto.setTotalAmount(purchaseDto.getTotalAmount());
		purchaseJoinDto.setStorageTypeId(purchaseDto.getStorageTypeId());
		purchaseJoinDto.setWarehousesId(purchaseDto.getWarehousesId());
		purchaseJoinDto.setHasExpiryDate(purchaseDto.getHasExpiryDate());
		purchaseJoinDto.setExpiryDate(purchaseDto.getExpriyDate());
		purchaseJoinDto.setPrice(purchaseDto.getPricePerUnit());
		
		PerchaseJoin perchaseJoin =purchaseMapper.getPerchaseJoinBuilder(purchaseJoinDto);
		purchaseJoinRepository.save(perchaseJoin);
		
		logger.info("Service: purchase details");
		return utils.objectMapperSuccess(purchaseMapper.getPurchaseDtoBuilder(purchase, perchaseJoin), " Purchase Details Saved");
	}

}
