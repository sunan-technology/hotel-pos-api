package com.sunan.supplier;

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

import com.sunan.model.Supplier;
import com.sunan.utils.JsonUtils;

@Service
public class SupplierService implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(SupplierService.class);

	@Autowired
	private SupplierRepository supplierRepository;

	@Autowired
	SupplierMapper supplierMapper;

	@Autowired
	private JsonUtils utils;

	@Transactional
	public String save(SupplierDto supplierDto) {
		Supplier supplier = supplierMapper.getSupplierBuilder(supplierDto);
		supplierRepository.save(supplier);
		logger.info("Service: supplier details");
		return utils.objectMapperSuccess(supplierMapper.getSupplierDtoBuilder(supplier), " Supplier Details Saved");
	}

	@Transactional
	public String update(SupplierDto supplierDto, int id) {
		logger.info("Service: Update supplier details with id {}", id);
		Optional<Supplier> optional = supplierRepository.findById(id);
		if (optional.isPresent()) {
			logger.info("Service: supplier details found with id {} for update operation", id);
			Supplier supplier = supplierMapper.getSupplierBuilder(supplierDto);
			supplierRepository.save(supplier);
			return utils.objectMapperSuccess(supplierMapper.getSupplierDtoBuilder(supplier),
					"Supplier Details Updated");
		}
		logger.info("Service:supplier details not found with id {} for update operation", id);
		return utils.objectMapperError("Supplier Details Not Found !");
	}

	@Transactional
	public String delete(int id) {
		logger.info("Service: Delete supplier details with id {}", id);
		int isDelete = supplierRepository.updateIsActiveStatus(id);
		if (isDelete > 0) {
			logger.info("Service: supplier details found with id {} for delete operation{}", id);
			return utils.objectMapperSuccess("Supplier Deleted Successfully");
		}
		logger.info("Service: supplier details not found with id {} for delete operation{}", id);
		return utils.objectMapperError("Supplier Deleted Failed");
	}

	@Transactional
	public String getById(int id) {
		logger.info("Service: Fetching  supplier details with id {}", id);
		Optional<Supplier> supplier = supplierRepository.findBySupplierId(id);
		if (supplier.isPresent()) {
			logger.info("Service: supplier details found with id {}", id);
			SupplierDto dto = supplierMapper.getSupplierDtoBuilder(supplier.get());
			return utils.objectMapperSuccess(dto, "Supplier Details");
		}
		logger.info("Service: Supplier details not found with id {}", id);
		return utils.objectMapperError("Supplier Details Not found, Id :" + id);
	}

	public String findActiveList(String searchTerm, Integer pageNo, Integer pageSize, String sortBy) {
		logger.info("Service: Fetching list of supplier details ");
		PageRequest pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Supplier> pagedResult = null;

		pagedResult = supplierRepository.findByIsActive("yes", pageable);

		Page<SupplierDto> page = pagedResult.map(new Function<Supplier, SupplierDto>() {
			@Override
			public SupplierDto apply(Supplier entity) {
				SupplierDto dto = supplierMapper.getSupplierDtoBuilder(entity);
				return dto;
			}
		});
		logger.info("Service: Fetching list of supplier details, total records: {}", page.getTotalElements());
		return utils.objectMapperSuccess(page, "All Acive supplier list.");
	}

}
