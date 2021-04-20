package com.sunan.unit;

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

import com.sunan.category.CategoryDto;
import com.sunan.model.Category;
import com.sunan.model.Hotel;
import com.sunan.model.Units;
import com.sunan.utils.JsonUtils;

@Service
public class UnitsService implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(UnitsService.class);

	@Autowired
	private UnitsRepository unitsRepository;

	@Autowired
	UnitsMapper unitsMapper;

	@Autowired
	private JsonUtils utils;

	@Transactional
	public String save(UnitsDto unitsDto, int hotelId) {

		Units units = unitsMapper.getUnitBuilder(unitsDto);
		logger.info("Service : Saving units deatils");
		units.setHotel(new Hotel(hotelId));
		unitsRepository.save(units);
		return utils.objectMapperSuccess(unitsMapper.getUnitDtoBuilder(units), "Units Details Saved");
	}

	@Transactional
	public String update(UnitsDto unitsDto, int id, int hotelId) {
		logger.info("Service: Update units details with id {}", id);
		Optional<Units> units = unitsRepository.findById(id);
		if (units.isPresent()) {

			logger.info("Service: units details found with id {} for update operation", id);
			Units unit = unitsMapper.getUnitBuilder(unitsDto);
			logger.info("Service : updating units deatils");
			unit.setHotel(new Hotel(hotelId));
			unitsRepository.save(unit);
			return utils.objectMapperSuccess(unitsMapper.getUnitDtoBuilder(unit), "Units Details Updated");
		} else {
			logger.info("Service: Unit details not found with id {} for update operation", id);
			return utils.objectMapperError("Units Details Not Found !");
		}

	}
	
	
	@Transactional
	public String getById(int id) {
		logger.info("Service: Fetching units details with id {}", id);
		Optional<Units> units = unitsRepository.findById(id);
		if (units.isPresent()) {
			logger.info("Service: units details found with id {}", id);
			UnitsDto dto = unitsMapper.getUnitDtoBuilder(units.get());
			return utils.objectMapperSuccess(dto, "Units Details");
		}
		logger.info("Service: units details not found with id {}", id);
		return utils.objectMapperError("Units Details Not found, Id :" + id);
	}

	@Transactional
	public String findActiveList(String searchTerm, Integer pageNo, Integer pageSize, String sortBy,int hotelId) {
		logger.info("Service: Fetching list of category details ");
		PageRequest pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Units> pagedResult = null;

		pagedResult = unitsRepository.findByIsActiveAndHotel("yes", pageable,new Hotel(hotelId));

		Page<UnitsDto> page = pagedResult.map(new Function<Units, UnitsDto>() {
			@Override
			public UnitsDto apply(Units entity) {
				UnitsDto dto = unitsMapper.getUnitDtoBuilder(entity);
				return dto;
			}
		});
		logger.info("Service: Fetching list of units details, total records: {}", page.getTotalElements());
		return utils.objectMapperSuccess(page, "All Acive Units list.");
	}

}
