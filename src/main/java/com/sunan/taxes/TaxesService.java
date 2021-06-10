package com.sunan.taxes;

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
import com.sunan.model.Taxes;
import com.sunan.utils.JsonUtils;

@Service
public class TaxesService implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(TaxesService.class);
	
	@Autowired
	private TaxesRepository taxesRepository;
	
	@Autowired
	TaxesMapper taxesMapper;
	
	@Autowired
	private JsonUtils utils;
	
	@Transactional
	public String save(TaxesDto taxesDto ,int hotelId) {
		Taxes taxes = taxesMapper.getTaxesBuilder(taxesDto);
		taxesRepository.save(taxes);
		taxes.setHotel(new Hotel(hotelId));
		logger.info("Service: Save tax details");
		return utils.objectMapperSuccess(taxesMapper.getTaxesDtoBuilder(taxes), "Tax Details Saved");
	}
	
	
	@Transactional
	public String update(TaxesDto taxesDto, int id,int hotelId) {
		logger.info("Service: Update tax details with id {}", id);
		Optional<Taxes> optional = taxesRepository.findById(id);
		if (optional.isPresent()) {
			logger.info("Service: tax details found with id {} for update operation", id);
			Taxes taxes = taxesMapper.getTaxesBuilder(taxesDto);
			taxes.setHotel(new Hotel(hotelId));
			taxesRepository.save(taxes);
			return utils.objectMapperSuccess(taxesMapper.getTaxesDtoBuilder(taxes), "Tax Details updated");
		}
		logger.info("Service: Tax details not found with id {} for update operation", id);
		return utils.objectMapperError("Tax Details Not Found !");
	}
	
	
	@Transactional
	public String getById(int id) {
		logger.info("Service: Fetching  tax details with id {}", id);
		Optional<Taxes> optional = taxesRepository.findById(id);
		if (optional.isPresent()) {
			logger.info("Service: tax details found with id {}", id);
			TaxesDto dto = taxesMapper.getTaxesDtoBuilder(optional.get());
			return utils.objectMapperSuccess(dto, " Tax Details");
		}
		logger.info("Service:  Tax details not found with id {}", id);
		return utils.objectMapperError(" Tax Details Not found, Id :" + id);
	}
	
	
	@Transactional
	public String findActiveList(String searchTerm, Integer pageNo, Integer pageSize, String sortBy) {
		logger.info("Service: Fetching list of tax details ");
		PageRequest pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Taxes> pagedResult = null;

		pagedResult = taxesRepository.findByIsActive("yes", pageable);

		Page<TaxesDto> page = pagedResult.map(new Function<Taxes, TaxesDto>() {
			@Override
			public TaxesDto apply(Taxes entity) {
				TaxesDto dto = taxesMapper.getTaxesDtoBuilder(entity);
				return dto;
			}
		});
		logger.info("Service: Fetching list of tax details, total records: {}", page.getTotalElements());
		return utils.objectMapperSuccess(page, "All Acive tax  list.");
	}

}
