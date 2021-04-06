package com.sunan.customer;

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

import com.sunan.model.Customer;
import com.sunan.model.Hotel;
import com.sunan.utils.JsonUtils;

@Service
public class CustomerService implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	CustomerMapper customerMapper;

	@Autowired
	private JsonUtils utils;

	@Transactional
	public String save(CustomerDto customerDto,int hotelId) {
		Customer customer = customerMapper.getCustomerBuilder(customerDto);
		customer.setHotel(new Hotel(hotelId));
		customerRepository.save(customer);
		logger.info("Service: Save customer details");
		return utils.objectMapperSuccess(customerMapper.getCustomerDtoBuilder(customer), "Customer Details Saved");
	}

	@Transactional
	public String update(CustomerDto customerDto, int id,int hotelId) {
		logger.info("Service: Update customer details with id {}", id);
		Optional<Customer> optional = customerRepository.findById(id);
		if (optional.isPresent()) {
			logger.info("Service: customer details found with id {} for update operation", id);
			Customer customer = customerMapper.getCustomerBuilder(customerDto);
			customer.setHotel(new Hotel(hotelId));
			customerRepository.save(customer);
			return utils.objectMapperSuccess(customerMapper.getCustomerDtoBuilder(customer),
					"Customer Details Updated");
		}
		logger.info("Service: Customer details not found with id {} for update operation", id);
		return utils.objectMapperError("Customer Details Not Found !");
	}

	@Transactional
	public String delete(int id) {
		logger.info("Service: Delete customer details with id {}", id);
		int isDelete = customerRepository.updateActiveStatus(id);
		if (isDelete > 0) {
			logger.info("Service: customer details found with id {} for delete operation{}", id);
			return utils.objectMapperSuccess("Customer Deleted Successfully");
		}
		logger.info("Service: customer details not found with id {} for delete operation{}", id);
		return utils.objectMapperError("Customer Deleted Failed");
	}

	@Transactional
	public String getById(int id) {
		logger.info("Service: Fetching customer details with id {}", id);
		Optional<Customer> customer = customerRepository.findById(id);
		if (customer.isPresent()) {
			logger.info("Service: customer details found with id {}", id);
			CustomerDto dto = customerMapper.getCustomerDtoBuilder(customer.get());
			return utils.objectMapperSuccess(dto, "Customer Details");
		}
		logger.info("Service: customer details not found with id {}", id);
		return utils.objectMapperError("Customer Details Not found, Id :" + id);
	}

	@Transactional
	public String findActiveList(String searchTerm, Integer pageNo, Integer pageSize, String sortBy) {
		logger.info("Service: Fetching list of customer details ");
		PageRequest pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Customer> pagedResult = null;

		pagedResult = customerRepository.findByIsActive("yes", pageable);

		Page<CustomerDto> page = pagedResult.map(new Function<Customer, CustomerDto>() {
			@Override
			public CustomerDto apply(Customer entity) {
				CustomerDto dto = customerMapper.getCustomerDtoBuilder(entity);
				return dto;
			}
		});
		logger.info("Service: Fetching list of customer details, total records: {}", page.getTotalElements());
		return utils.objectMapperSuccess(page, "All Acive customer list.");
	}

}
