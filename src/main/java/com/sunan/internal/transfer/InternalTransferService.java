package com.sunan.internal.transfer;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sunan.exception.BadRequestException;
import com.sunan.internal.transfer.join.InternalTransferJoinDto;
import com.sunan.internal.transfer.join.InternalTransferJoinRepository;
import com.sunan.kitchen.KitchenRepository;
import com.sunan.kitchen.raw.material.KitchenRawMatrialMapper;
import com.sunan.kitchen.raw.material.KitchenRawMatrialRepository;
import com.sunan.member.MemberDto;
import com.sunan.model.Hotel;
import com.sunan.model.InternalTransfer;
import com.sunan.model.InternalTransferJoin;
import com.sunan.model.Kitchen;
import com.sunan.model.KitchenRawMatrial;
import com.sunan.model.Member;
import com.sunan.model.PerchaseJoin;
import com.sunan.purchase.join.PurchaseJoinRepository;
import com.sunan.utils.JsonUtils;
@Service
public class InternalTransferService implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(InternalTransferService.class);
	
	@Autowired
	private InternalTransferRepository internalTransferRepository;
	
	@Autowired
	private KitchenRepository kitchenRepository;
	
	@Autowired
	private KitchenRawMatrialRepository kitchenRawMatrialRepository;
	
	@Autowired
	KitchenRawMatrialMapper kitchenRawMatrialMapper;
	
	@Autowired
	private PurchaseJoinRepository purchaseJoinRepository;
	
	@Autowired
	private InternalTransferJoinRepository internalTransferJoinRepository;
	
	@Autowired
	InternalTransferMapper internalTransferMapper;
	
	@Autowired
	private JsonUtils utils;
	
	@Transactional
	public String save(InternalTransferDto internalTransferDto,int hotelId) {
		
		Optional<Kitchen> kitchen =kitchenRepository.findById(internalTransferDto.getKitchenId());
		if(kitchen.isPresent()) {
			
			
			//running for loop for updating raw materail quantity
			for(InternalTransferJoinDto dto : internalTransferDto.internalTransferJoinDtos ) {
				//getting purchase join object by purchaseJoin Id
				Optional<PerchaseJoin> purchaseJoin=purchaseJoinRepository.findById(dto.getPurchaseJoinId());
				if(dto.getQuantity() == 0 || dto.getQuantity() > purchaseJoin.get().getQuantity()) {
					return utils.objectMapperError("please provide proper quantity");
					
				}
				int quantity=purchaseJoin.get().getQuantity() -dto.getQuantity();
//				Double totalAmount=quantity*purchaseJoin.get().getPrice();
				//updating raw matrial quantity
				purchaseJoinRepository.updateQuantity(new Hotel(hotelId), quantity, dto.getPurchaseJoinId()); 
			}
			
			InternalTransfer internalTransfer=internalTransferMapper.getInternalTransferBuilder(internalTransferDto);
			internalTransfer.setHotel(new Hotel(hotelId));
			logger.info("Service : saving internal transfer details");
			internalTransferRepository.save(internalTransfer);
			
			List<InternalTransferJoin> internalTranferJoinDto=internalTransferMapper.getInternalTransferJoinBuilder(internalTransferDto.getInternalTransferJoinDtos(), internalTransfer.getId(), hotelId);
			logger.info("Service : saving internal transfer join details");
			internalTransferJoinRepository.saveAll(internalTranferJoinDto);
			//save raw matrial to kitchen
			List<KitchenRawMatrial> kitchenRawMatrial=kitchenRawMatrialMapper.getKitchenRawMatrial(internalTransferDto.getInternalTransferJoinDtos(), hotelId, internalTransferDto.getKitchenId());
			kitchenRawMatrialRepository.saveAll(kitchenRawMatrial);
			
			logger.info("Service: internal transfer details");
			
			List<InternalTransferJoinDto> internalTransferJoinDto=internalTransferMapper.getInternalTransferJoinDtoBuilder(internalTranferJoinDto);
			
			return utils.objectMapperSuccess(internalTransferMapper.getInternalTransferDtoBuilder(internalTransfer, internalTransferJoinDto), "Internal Transfer Saved");
			
		}else {
			logger.info("Service: kitchen not found");
			return utils.objectMapperError("Kitchen not found");
		}
		
	}
	
	
	@Transactional
	public String getById(int id) {
		logger.info("Service: Fetching internal transfer details with id {}", id);
		Optional<InternalTransfer> internalTransfer = internalTransferRepository.findById(id);

		
		if (internalTransfer.isPresent()) {
			logger.info("Service: internal transfer details found with id {}", id);
		
			List<InternalTransferJoin> internalTransferJoin = internalTransferJoinRepository.findByInternalTransfer(new InternalTransfer(id));
			List<InternalTransferJoinDto> internalTransferJoinDto = internalTransferMapper.getInternalTransferJoinDtoBuilder(internalTransferJoin);
			InternalTransferDto dto = internalTransferMapper.getInternalTransferDtoBuilder(internalTransfer.get(), internalTransferJoinDto);
			return utils.objectMapperSuccess(dto, "Recipe Details");
		}
		logger.info("Service: internal transfer details not found with id {}", id);
		return utils.objectMapperError("Internal transfer Details Not found, Id :" + id);
	}
	
	
	
	@Transactional
	public String findActiveList(String searchTerm, Integer pageNo, Integer pageSize, String sortBy,int hotelId) {
		logger.info("Service: Fetching list of internal transfer details ");
		PageRequest pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<InternalTransfer> pagedResult = null;
	
			pagedResult = internalTransferRepository.findByIsActiveAndHotel("yes", pageable,new Hotel(hotelId));
		

		Page<InternalTransferDto> page = pagedResult.map(new Function<InternalTransfer, InternalTransferDto>() {
			@Override
			public InternalTransferDto apply(InternalTransfer entity) {
				List<InternalTransferJoin> internalTransferJoin=internalTransferJoinRepository.findByInternalTransfer(new InternalTransfer(entity.getId()));
				
				List<InternalTransferJoinDto> internalTranferJoinDto=internalTransferMapper.getInternalTransferJoinDtoBuilder(internalTransferJoin);
				
				InternalTransferDto dto = internalTransferMapper.getInternalTransferDtoBuilder(entity, internalTranferJoinDto);
				return dto;
			}
		});
		logger.info("Service: Fetching list of internal transfer details details, total records: {}", page.getTotalElements());
		return utils.objectMapperSuccess(page, "All Acive internal transfer list.");
	}
	
	


}
