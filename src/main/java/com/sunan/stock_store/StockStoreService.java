package com.sunan.stock_store;

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
import com.sunan.model.StockStore;
import com.sunan.utils.JsonUtils;

@Service
public class StockStoreService implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(StockStoreService.class);

	@Autowired
	private StockStoreRepository stockStoreRepository;

	@Autowired
	StockStoreMapper stockStoreMapper;

	@Autowired
	private JsonUtils utils;

	@Transactional
	public String save(StockStoreDto stockStoreDto,int hotelId) {
		StockStore stockStore = stockStoreMapper.getStock_StoreBuilder(stockStoreDto);
		stockStore.setHotel(new Hotel(hotelId));
		stockStoreRepository.save(stockStore);
		logger.info("Service: Save stock details");
		return utils.objectMapperSuccess(stockStoreMapper.getStock_StoreDtoBuilder(stockStore), "Stock Details Saved");
	}

	@Transactional
	public String update(StockStoreDto stockStoreDto, int id,int hotelId) {
		logger.info("Service: Update stock details with id {}", id);
		Optional<StockStore> optional = stockStoreRepository.findById(id);
		if (optional.isPresent()) {
			logger.info("Service: stock details found with id {} for update operation", id);
			StockStore stockStore = stockStoreMapper.getStock_StoreBuilder(stockStoreDto);
			stockStore.setHotel(new Hotel(hotelId));
			stockStoreRepository.save(stockStore);
			return utils.objectMapperSuccess(stockStoreMapper.getStock_StoreDtoBuilder(stockStore),
					"Stock Details Updated");
		}
		logger.info("Service: Stock details not found with id {} for update operation", id);
		return utils.objectMapperError("Stock Details Not Found !");
	}

	@Transactional
	public String delete(int id) {
		logger.info("Service: Delete stock details with id {}", id);
		int isDelete = stockStoreRepository.updateIsActiveStatus(id);
		if (isDelete > 0) {
			logger.info("Service: stock details found with id {} for delete operation{}", id);
			return utils.objectMapperSuccess("Stock Deleted Successfully");
		}
		logger.info("Service: stock details not found with id {} for delete operation{}", id);
		return utils.objectMapperError("Stock Deleted Failed");
	}

	@Transactional
	public String getById(int id) {
		logger.info("Service: Fetching stock details with id {}", id);
		Optional<StockStore> stockStore = stockStoreRepository.findById(id);
		if (stockStore.isPresent()) {
			logger.info("Service: stock details found with id {}", id);
			StockStoreDto dto = stockStoreMapper.getStock_StoreDtoBuilder(stockStore.get());
			return utils.objectMapperSuccess(dto, "Stock Details");
		}
		logger.info("Service: stock details not found with id {}", id);
		return utils.objectMapperError("Stock Details Not found, Id :" + id);
	}

	@Transactional
	public String findActiveList(String searchTerm, Integer pageNo, Integer pageSize, String sortBy) {
		logger.info("Service: Fetching list of stock details ");
		PageRequest pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<StockStore> pagedResult = null;
		if (StringUtils.isBlank(searchTerm)) {
			pagedResult = stockStoreRepository.findByIsActive("yes", pageable);
		} else {
			pagedResult = stockStoreRepository.findByDishContainingIgnoreCaseAndIsActive(searchTerm, "yes", pageable);
		}

		Page<StockStoreDto> page = pagedResult.map(new Function<StockStore, StockStoreDto>() {
			@Override
			public StockStoreDto apply(StockStore entity) {
				StockStoreDto dto = stockStoreMapper.getStock_StoreDtoBuilder(entity);
				return dto;
			}
		});
		logger.info("Service: Fetching list of stock details, total records: {}", page.getTotalElements());
		return utils.objectMapperSuccess(page, "All Acive stock list.");
	}

}
