package com.sunan.employee.registration;

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

import com.sunan.model.EmployeeRegistration;
import com.sunan.model.Hotel;
import com.sunan.utils.JsonUtils;

@Service
public class EmployeeRegistrationService implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(EmployeeRegistrationService.class);

	@Autowired
	private EmployeeRegistrationRepository employeeRegistrationRepository;

	@Autowired
	EmployeeRegistrationMapper employeeRegistrationMapper;

	@Autowired
	private JsonUtils utils;

	@Transactional
	public String save(EmployeeRegistrationDto employeeRegistrationDto, int hotelId) {
		EmployeeRegistration employeeRegistration = employeeRegistrationMapper
				.getEmployeeRegistrationBuilder(employeeRegistrationDto);
		employeeRegistration.setHotel(new Hotel(hotelId));
		employeeRegistrationRepository.save(employeeRegistration);
		logger.info("Service: Save employee details");
		return utils.objectMapperSuccess(
				employeeRegistrationMapper.getEmployeeRegistrationDtoBuilder(employeeRegistration),
				"Employee Details Saved");
	}

	@Transactional
	public String update(EmployeeRegistrationDto employeeRegistrationDto, int id,int hotelId) {
		logger.info("Service: Update employee details with id {}", id);
		Optional<EmployeeRegistration> optional = employeeRegistrationRepository.findById(id);
		if (optional.isPresent()) {
			logger.info("Service: employee details found with id {} for update operation", id);
			EmployeeRegistration employeeRegistration = employeeRegistrationMapper
					.getEmployeeRegistrationBuilder(employeeRegistrationDto);
			employeeRegistration.setHotel(new Hotel(hotelId));
			employeeRegistrationRepository.save(employeeRegistration);
			return utils.objectMapperSuccess(
					employeeRegistrationMapper.getEmployeeRegistrationDtoBuilder(employeeRegistration),
					"Employee Details Updated");
		}
		logger.info("Service: employee details not found with id {} for update operation", id);
		return utils.objectMapperError("Employee Details Not Found !");
	}

	@Transactional
	public String delete(int id) {
		logger.info("Service: Delete employee details with id {}", id);
		int isDelete = employeeRegistrationRepository.updateActiveStatus(id);
		if (isDelete > 0) {
			logger.info("Service: employee details found with id {} for delete operation{}", id);
			return utils.objectMapperSuccess("Employee Deleted Successfully");
		}
		logger.info("Service: employee details not found with id {} for delete operation{}", id);
		return utils.objectMapperError("Employee Deleted Failed");
	}

	@Transactional
	public String getById(int id) {
		logger.info("Service: Fetching employee details with id {}", id);
		Optional<EmployeeRegistration> employeeRegistration = employeeRegistrationRepository.findById(id);
		if (employeeRegistration.isPresent()) {
			logger.info("Service: employee details found with id {}", id);
			EmployeeRegistrationDto dto = employeeRegistrationMapper
					.getEmployeeRegistrationDtoBuilder(employeeRegistration.get());
			return utils.objectMapperSuccess(dto, "Employee Details");
		}
		logger.info("Service: employee details not found with id {}", id);
		return utils.objectMapperError("Employee Details Not found, Id :" + id);
	}

	@Transactional
	public String findActiveList(String searchTerm, Integer pageNo, Integer pageSize, String sortBy) {
		logger.info("Service: Fetching list of employee details ");
		PageRequest pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<EmployeeRegistration> pagedResult = null;

		pagedResult = employeeRegistrationRepository.findByIsActive("yes", pageable);

		Page<EmployeeRegistrationDto> page = pagedResult
				.map(new Function<EmployeeRegistration, EmployeeRegistrationDto>() {
					@Override
					public EmployeeRegistrationDto apply(EmployeeRegistration entity) {
						EmployeeRegistrationDto dto = employeeRegistrationMapper
								.getEmployeeRegistrationDtoBuilder(entity);
						return dto;
					}
				});
		logger.info("Service: Fetching list of employee details, total records: {}", page.getTotalElements());
		return utils.objectMapperSuccess(page, "All Acive employee list.");
	}

}
