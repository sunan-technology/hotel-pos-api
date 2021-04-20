package com.sunan.variation;

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

import com.sunan.hotel.HotelRepository;
import com.sunan.model.Hotel;
import com.sunan.model.Variation;
import com.sunan.utils.JsonUtils;

@Service
public class VariationService implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(VariationService.class);

	@Autowired
	private VariationRepository variationRepository;

	@Autowired
	private HotelRepository hotelRepository;

	@Autowired
	VariationMapper variationMapper;

	@Autowired
	private JsonUtils utils;

	@Transactional
	public String save(VariationDto variationDto, int hotelId) {

		Optional<Hotel> hotel = hotelRepository.findById(hotelId);
		if (hotel.isPresent()) {
			Variation variation = variationMapper.getVariationBuilder(variationDto, hotelId);
			logger.info("Service :Saving variation details ");
			variationRepository.save(variation);

			return utils.objectMapperSuccess(variationMapper.getVariationDtoBuilder(variation),
					"Variation Details Saved");
		} else {
			logger.info("Service: hotel not found");
			return utils.objectMapperError("Hotel not found");
		}
	}

	@Transactional
	public String update(VariationDto variationDto, int id, int hotelId) {
		logger.info("Service: Update variation details with id {}", id);
		Optional<Variation> optional = variationRepository.findById(id);
		if (optional.isPresent()) {
			logger.info("Service: variation details found with id {} for update operation{}", id);
			Variation variation = variationMapper.getVariationBuilder(variationDto, hotelId);
			variationRepository.save(variation);
			return utils.objectMapperSuccess(variationMapper.getVariationDtoBuilder(variation),
					"Variation Details Updated");
		}
		logger.info("Service: variation details not found with id {} for update operation{}", id);
		return utils.objectMapperError("Variation Details Not Found !");
	}

	@Transactional
	public String findActiveList(String searchTerm, Integer pageNo, Integer pageSize, String sortBy,int hotelId) {
		logger.info("Service: Fetching list of variation details ");
		PageRequest pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Variation> pagedResult = null;

		pagedResult = variationRepository.findByStatusAndHotel("active", pageable,new Hotel(hotelId));

		Page<VariationDto> page = pagedResult.map(new Function<Variation, VariationDto>() {
			@Override
			public VariationDto apply(Variation entity) {
				VariationDto dto = variationMapper.getVariationDtoBuilder(entity);
				return dto;
			}
		});
		logger.info("Service: Fetching list of variation details, total records: {}", page.getTotalElements());
		return utils.objectMapperSuccess(page, "All Acive variation list.");
	}

}
