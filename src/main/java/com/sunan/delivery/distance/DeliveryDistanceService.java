package com.sunan.delivery.distance;

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

import com.sunan.model.DeliveryDistance;
import com.sunan.model.Hotel;
import com.sunan.utils.JsonUtils;

@Service
public class DeliveryDistanceService implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(DeliveryDistanceService.class);

	@Autowired
	private DeliveryDistanceRepository deliveryDistanceRepository;

	@Autowired
	DeliveryDistanceMapper deliveryDistanceMapper;

	@Autowired
	private JsonUtils utils;

	@Transactional
	public String save(DeliveryDistanceDto deliveryDistanceDto, int hotelId) {
		DeliveryDistance deliveryDistance = deliveryDistanceMapper.getDeliveryDistanceBuilder(deliveryDistanceDto);
		deliveryDistance.setHotel(new Hotel(hotelId));
		deliveryDistanceRepository.save(deliveryDistance);
		logger.info("Service: Save Delivery distance details");
		return utils.objectMapperSuccess(deliveryDistanceMapper.getDeliveryDistanceDtoBuilder(deliveryDistance),
				"Delivery distance Details Saved");
	}

	@Transactional
	public String update(DeliveryDistanceDto deliveryDistanceDto, int id, int hotelId) {
		logger.info("Service: Update Delivery distance details with id {}", id);
		Optional<DeliveryDistance> optional = deliveryDistanceRepository.findById(id);
		if (optional.isPresent()) {
			logger.info("Service: Delivery distance details found with id {} for update operation", id);
			DeliveryDistance deliveryDistance = deliveryDistanceMapper.getDeliveryDistanceBuilder(deliveryDistanceDto);
			deliveryDistance.setHotel(new Hotel(hotelId));
			deliveryDistanceRepository.save(deliveryDistance);
			return utils.objectMapperSuccess(deliveryDistanceMapper.getDeliveryDistanceDtoBuilder(deliveryDistance),
					"Delivery distance Details Updated");
		}
		logger.info("Service: Delivery distance details not found with id {} for update operation", id);
		return utils.objectMapperError("Delivery distance Details Not Found !");
	}

	@Transactional
	public String delete(int id) {
		logger.info("Service: Delete Delivery distance details with id {}", id);
		int isDelete = deliveryDistanceRepository.updateActiveStatus(id);
		if (isDelete > 0) {
			logger.info("Service: Delivery distance details found with id {} for delete operation{}", id);
			return utils.objectMapperSuccess("Delivery distance Deleted Successfully");
		}
		logger.info("Delivery distance details not found with id {} for delete operation{}", id);
		return utils.objectMapperError("Delivery distance Deleted Failed");
	}

	@Transactional
	public String getById(int id) {
		logger.info("Service: Fetching  Delivery distance details with id {}", id);
		Optional<DeliveryDistance> optional = deliveryDistanceRepository.findById(id);
		if (optional.isPresent()) {
			logger.info("Service: Delivery distance details found with id {}", id);
			DeliveryDistanceDto dto = deliveryDistanceMapper.getDeliveryDistanceDtoBuilder(optional.get());
			return utils.objectMapperSuccess(dto, " Delivery distance Details");
		}
		logger.info("Service:  Delivery distance details not found with id {}", id);
		return utils.objectMapperError(" Delivery distance Details Not found, Id :" + id);
	}

	@Transactional
	public String findActiveList(String searchTerm, Integer pageNo, Integer pageSize, String sortBy, int hotelId) {
		logger.info("Service: Fetching list of Delivery distance details ");
		PageRequest pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<DeliveryDistance> pagedResult = null;

		pagedResult = deliveryDistanceRepository.findByIsActiveAndHotel("yes", pageable, new Hotel(hotelId));

		Page<DeliveryDistanceDto> page = pagedResult.map(new Function<DeliveryDistance, DeliveryDistanceDto>() {
			@Override
			public DeliveryDistanceDto apply(DeliveryDistance entity) {
				DeliveryDistanceDto dto = deliveryDistanceMapper.getDeliveryDistanceDtoBuilder(entity);
				return dto;
			}
		});
		logger.info("Service: Fetching list of Delivery distance details, total records: {}", page.getTotalElements());
		return utils.objectMapperSuccess(page, "All Acive Delivery distance list.");
	}

}
