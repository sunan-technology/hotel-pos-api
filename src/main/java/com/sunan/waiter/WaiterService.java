package com.sunan.waiter;

import java.io.Serializable;
import java.util.Optional;
import java.util.function.Function;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sunan.model.Hotel;
import com.sunan.model.Waiter;
import com.sunan.utils.JsonUtils;

@Service
public class WaiterService implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(WaiterService.class);

	@Autowired
	private WaiterRepository waiterRepository;

	@Autowired
	WaiterMapper waiterMapper;

	@Autowired
	private JsonUtils utils;

	@Transactional
	public String save(WaiterDto waiterDto, int hotelId) {
		Waiter waiter = waiterMapper.getWaiterBuilder(waiterDto);
		waiter.setHotel(new Hotel(hotelId));
		waiterRepository.save(waiter);
		logger.info("Service: Save waiter details");
		return utils.objectMapperSuccess(waiterMapper.getWaiterDtoBuilder(waiter), "Waiter Details Saved");
	}

	@Transactional
	public String update(WaiterDto waiterDto, int id, int hotelId) {
		logger.info("Service: Update waiter type details with id {}", id);
		Optional<Waiter> optional = waiterRepository.findById(id);
		if (optional.isPresent()) {
			logger.info("Service: waiter details found with id {} for update operation", id);
			Waiter waiter = waiterMapper.getWaiterBuilder(waiterDto);
			waiter.setHotel(new Hotel(hotelId));
			waiterRepository.save(waiter);
			return utils.objectMapperSuccess(waiterMapper.getWaiterDtoBuilder(waiter), "Waiter Details Updated");
		}
		logger.info("Service: Waiter details not found with id {} for update operation", id);
		return utils.objectMapperError("Waiter Details Not Found !");
	}

	@Transactional
	public String delete(int id) {
		logger.info("Service: Delete waiter details with id {}", id);
		int isDelete = waiterRepository.updateActiveStatus(id);
		if (isDelete > 0) {
			logger.info("Service: waiter details found with id {} for delete operation{}", id);
			return utils.objectMapperSuccess("Waiter Deleted Successfully");
		}
		logger.info("Service:waiter details not found with id {} for delete operation{}", id);
		return utils.objectMapperError("Waiter Deleted Failed");
	}

	@Transactional
	public String getById(int id) {
		logger.info("Service: Fetching  waiter details with id {}", id);
		Optional<Waiter> waiter = waiterRepository.findById(id);
		if (waiter.isPresent()) {
			logger.info("Service: waiter details found with id {}", id);
			WaiterDto dto = waiterMapper.getWaiterDtoBuilder(waiter.get());
			return utils.objectMapperSuccess(dto, " Waiter Details");
		}
		logger.info("Service:  Waiter details not found with id {}", id);
		return utils.objectMapperError(" Waiter Details Not found, Id :" + id);
	}

	@Transactional
	public String findActiveList(String searchTerm, Integer pageNo, Integer pageSize, String sortBy) {
		logger.info("Service: Fetching list of product details ");
		PageRequest pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Waiter> pagedResult = null;

		pagedResult = waiterRepository.findByIsActive("yes", pageable);

		Page<WaiterDto> page = pagedResult.map(new Function<Waiter, WaiterDto>() {
			@Override
			public WaiterDto apply(Waiter entity) {
				WaiterDto dto = waiterMapper.getWaiterDtoBuilder(entity);
				return dto;
			}
		});
		logger.info("Service: Fetching list of waiter details, total records: {}", page.getTotalElements());
		return utils.objectMapperSuccess(page, "All Acive waiter list.");
	}

}
