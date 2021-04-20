package com.sunan.raw.matrial;

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

import com.sunan.category.CategoryRepository;
import com.sunan.model.Category;
import com.sunan.model.Hotel;
import com.sunan.model.RawMatrial;
import com.sunan.utils.JsonUtils;

@Service
public class RawMatrialService implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(RawMatrialService.class);

	@Autowired
	private RawMatrialRepository rawMatrialRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	RawMatrialMapper rawMatrialMapper;

	@Autowired
	private JsonUtils utils;

	@Transactional
	public String save(RawMatrialDto rawMatrialDto, int hotelId) {

		Optional<Category> category = categoryRepository.findById(rawMatrialDto.getCategoryId());
		if (category.isPresent()) {

			RawMatrial rawMatrial = rawMatrialMapper.getRawMatrialBuilder(rawMatrialDto);
			rawMatrial.setHotel(new Hotel(hotelId));
			logger.info("Service :saving raw matrial details");
			rawMatrialRepository.save(rawMatrial);
			return utils.objectMapperSuccess(rawMatrialMapper.getRowMatrialDtoBuilder(rawMatrial),
					"Raw Matrial saved Successfully");

		} else {
			logger.info("Service : Category not found");
			return utils.objectMapperError("Category not found");
		}

	}

	@Transactional
	public String update(RawMatrialDto rawMatrialDto, int id, int hotelId) {
		logger.info("Service: Update customer details with id {}", id);
		Optional<RawMatrial> optional = rawMatrialRepository.findById(id);
		if (optional.isPresent()) {

			Optional<Category> category = categoryRepository.findById(rawMatrialDto.getCategoryId());
			if (category.isPresent()) {
				logger.info("Service: customer details found with id {} for update operation", id);
				RawMatrial rawMatrial = rawMatrialMapper.getRawMatrialBuilder(rawMatrialDto);
				rawMatrial.setHotel(new Hotel(hotelId));
				logger.info("Service :updating raw matrial details");
				rawMatrialRepository.save(rawMatrial);
				return utils.objectMapperSuccess(rawMatrialMapper.getRowMatrialDtoBuilder(rawMatrial),
						"Raw Matrial Details Updated");
			} else {
				logger.info("Service : Category not found");
				return utils.objectMapperError("Category not found");
			}
		}
		logger.info("Service: Customer details not found with id {} for update operation", id);
		return utils.objectMapperError("Customer Details Not Found !");
	}

	@Transactional
	public String getById(int id) {
		logger.info("Service: Fetching raw matrial details with id {}", id);
		Optional<RawMatrial> optional = rawMatrialRepository.findById(id);
		if (optional.isPresent()) {
			logger.info("Service: raw matrial details found with id {}", id);
			RawMatrialDto dto = rawMatrialMapper.getRowMatrialDtoBuilder(optional.get());
			return utils.objectMapperSuccess(dto, "Raw matrial Details");
		}
		logger.info("Service: Raw matrial details not found with id {}", id);
		return utils.objectMapperError("Raw matrial Details Not found, Id :" + id);
	}

	@Transactional
	public String findActiveList(String searchTerm, Integer pageNo, Integer pageSize, String sortBy, int hotelId) {
		logger.info("Service: Fetching list of customer details ");
		PageRequest pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<RawMatrial> pagedResult = null;

		pagedResult = rawMatrialRepository.findByIsActiveAndHotel("yes", pageable, new Hotel(hotelId));

		Page<RawMatrialDto> page = pagedResult.map(new Function<RawMatrial, RawMatrialDto>() {
			@Override
			public RawMatrialDto apply(RawMatrial entity) {
				RawMatrialDto dto = rawMatrialMapper.getRowMatrialDtoBuilder(entity);
				return dto;
			}
		});
		logger.info("Service: Fetching list of raw matrial details, total records: {}", page.getTotalElements());
		return utils.objectMapperSuccess(page, "All Acive raw matrials list.");
	}

}
