package com.sunan.creditCustomer;

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

import com.sunan.model.CreditCustomer;
import com.sunan.model.Hotel;
import com.sunan.utils.JsonUtils;

@Service
public class CreditCustomerService implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(CreditCustomerService.class);

	@Autowired
	private CreditCustomerRepository creditCustomerRepository;

	@Autowired
	CreditCustomerMapper creditCustomerMapper;

	@Autowired
	private JsonUtils utils;

	@Transactional
	public String save(CreditCustomerDto creditCustomerDto,int hotelId) {
		CreditCustomer creditCustomer = creditCustomerMapper.getCreditCustomerBuilder(creditCustomerDto);
		creditCustomer.setHotel(new Hotel(hotelId));
		creditCustomerRepository.save(creditCustomer);
		logger.info("Service: credit customer details");
		return utils.objectMapperSuccess(creditCustomerMapper.getCreditCustomerDtoBuilder(creditCustomer),
				" Credit customer Details Saved");
	}

	@Transactional
	public String update(CreditCustomerDto creditCustomerDto, int id,int hotelId) {
		logger.info("Service: Update credit customer details with id {}", id);
		Optional<CreditCustomer> optional = creditCustomerRepository.findByCreditCustomerId(id);
		if (optional.isPresent()) {
			logger.info("Service: credit customer details found with id {} for update operation", id);
			CreditCustomer creditCustomer = creditCustomerMapper.getCreditCustomerBuilder(creditCustomerDto);
			creditCustomer.setHotel(new Hotel(hotelId));
			creditCustomerRepository.save(creditCustomer);
			return utils.objectMapperSuccess(creditCustomerMapper.getCreditCustomerDtoBuilder(creditCustomer),
					"Credit customer Details Updated");
		}
		logger.info("Service: Credit customer details not found with id {} for update operation", id);
		return utils.objectMapperError("Credit customer Details Not Found !");
	}

	@Transactional
	public String delete(int id) {
		logger.info("Service: Delete credit customer details with id {}", id);
		int isDelete = creditCustomerRepository.updateIsActiveStatus(id);
		if (isDelete > 0) {
			logger.info("Service: credit customer details found with id {} for delete operation{}", id);
			return utils.objectMapperSuccess("Credit customer Deleted Successfully");
		}
		logger.info("Service: credit customer details not found with id {} for delete operation{}", id);
		return utils.objectMapperError("Credit customer Deleted Failed");
	}

	@Transactional
	public String getById(int id) {
		logger.info("Service: Fetching  credit customer details with id {}", id);
		Optional<CreditCustomer> creditCustomer = creditCustomerRepository.findByCreditCustomerId(id);
		if (creditCustomer.isPresent()) {
			logger.info("Service: credit customer details found with id {}", id);
			CreditCustomerDto dto = creditCustomerMapper.getCreditCustomerDtoBuilder(creditCustomer.get());
			return utils.objectMapperSuccess(dto, "Credit customer Details");
		}
		logger.info("Service: credit customer details not found with id {}", id);
		return utils.objectMapperError("Credit customer Details Not found, Id :" + id);
	}

	public String findActiveList(String searchTerm, Integer pageNo, Integer pageSize, String sortBy) {
		logger.info("Service: Fetching list of credit customer details ");
		PageRequest pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<CreditCustomer> pagedResult = null;

		pagedResult = creditCustomerRepository.findByIsActive("yes", pageable);

		Page<CreditCustomerDto> page = pagedResult.map(new Function<CreditCustomer, CreditCustomerDto>() {
			@Override
			public CreditCustomerDto apply(CreditCustomer entity) {
				CreditCustomerDto dto = creditCustomerMapper.getCreditCustomerDtoBuilder(entity);
				return dto;
			}
		});
		logger.info("Service: Fetching list of credit customer details, total records: {}", page.getTotalElements());
		return utils.objectMapperSuccess(page, "All Acive credit customer list.");
	}
}
