package com.sunan.walletType;

import java.io.Serializable;
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

import com.sunan.model.Hotel;
import com.sunan.model.WalletType;
import com.sunan.utils.JsonUtils;

@Service
public class WalletTypeService implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(WalletTypeService.class);

	@Autowired
	private WalletTypeRepository walletTypeRepository;

	@Autowired
	WalletTypeMapper walletTypeMapper;

	@Autowired
	private JsonUtils utils;

	@Transactional
	public String save(WalletTypeDto walletTypeDto,int hotelId) {
		WalletType walletType = walletTypeMapper.getWalletTypeBuilder(walletTypeDto);
		walletType.setHotelId(new Hotel(hotelId));
		walletTypeRepository.save(walletType);
		logger.info("Service: Save WalletType details");
		return utils.objectMapperSuccess(walletTypeMapper.getWalletTypeDtoBuilder(walletType),
				"Wallet type Details Saved");
	}

	@Transactional
	public String update(WalletTypeDto walletTypeDto, int id,int hotelId) {
		logger.info("Service: Update wallet type details with id {}", id);
		Optional<WalletType> optional = walletTypeRepository.findById(id);
		if (optional.isPresent()) {
			logger.info("Service: Wallet type details found with id {} for update operation", id);
			WalletType walletType = walletTypeMapper.getWalletTypeBuilder(walletTypeDto);
			walletType.setHotelId(new Hotel(hotelId));
			walletTypeRepository.save(walletType);
			return utils.objectMapperSuccess(walletTypeMapper.getWalletTypeDtoBuilder(walletType),
					"Wallet type Details Updated");
		}
		logger.info("Service: Wallet type details not found with id {} for update operation", id);
		return utils.objectMapperError("Wallet type Details Not Found !");
	}

	@Transactional
	public String delete(int id) {
		logger.info("Service: Delete WalletType details with id {}", id);
		int isDelete = walletTypeRepository.updateActiveStatus(id);
		if (isDelete > 0) {
			logger.info("Service: Wallet type details found with id {} for delete operation{}", id);
			return utils.objectMapperSuccess("WalletType Deleted Successfully");
		}
		logger.info("Service: WalletType details not found with id {} for delete operation{}", id);
		return utils.objectMapperError("WalletType Deleted Failed");
	}

	@Transactional
	public String getById(int id) {
		logger.info("Service: Fetching wallet type details with id {}", id);
		Optional<WalletType> walletType = walletTypeRepository.findById(id);
		if (walletType.isPresent()) {
			logger.info("Service: wallet type details found with id {}", id);
			WalletTypeDto dto = walletTypeMapper.getWalletTypeDtoBuilder(walletType.get());
			return utils.objectMapperSuccess(dto, "Wallet type Details");
		}
		logger.info("Service: wallet type details not found with id {}", id);
		return utils.objectMapperError("Wallet type Details Not found, Id :" + id);
	}

	@Transactional
	public String findActiveList(String searchTerm, Integer pageNo, Integer pageSize, String sortBy) {
		logger.info("Service: Fetching list of wallet type details ");
		PageRequest pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<WalletType> pagedResult = null;
		if (StringUtils.isBlank(searchTerm)) {
			pagedResult = walletTypeRepository.findByIsActive("yes", pageable);
		} else {
			pagedResult = walletTypeRepository.findByNameContainingIgnoreCaseAndIsActive(searchTerm, "yes", pageable);
		}

		Page<WalletTypeDto> page = pagedResult.map(new Function<WalletType, WalletTypeDto>() {
			@Override
			public WalletTypeDto apply(WalletType entity) {
				WalletTypeDto dto = walletTypeMapper.getWalletTypeDtoBuilder(entity);
				return dto;
			}
		});
		logger.info("Service: Fetching list of walletType details, total records: {}", page.getTotalElements());
		return utils.objectMapperSuccess(page, "All Acive Wallet type list.");
	}

}
