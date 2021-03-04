package com.sunan.expenseType;

import java.io.Serializable;
import java.util.Optional;
import java.util.function.Function;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sunan.model.ExpenseType;
import com.sunan.utils.JsonUtils;

@Service
public class ExpenseTypeService implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(ExpenseTypeService.class);

	@Autowired
	private ExpenseTypeRepository expenseTypeRepository;

	@Autowired
	ExpenseTypeMapper expenseTypeMapper;

	@Autowired
	private JsonUtils utils;

	@Transactional
	public String save(ExpenseTypeDto expenseTypeDto) {
		ExpenseType expenseType = expenseTypeMapper.getExpenseTypeBuilder(expenseTypeDto);
		expenseTypeRepository.save(expenseType);
		logger.info("Service: Save Expense type details");
		return utils.objectMapperSuccess(expenseTypeMapper.getExpenseTypeDtoBuilder(expenseType),
				"Expense type Details Saved");
	}

	@Transactional
	public String update(ExpenseTypeDto expenseTypeDto, int id) {
		logger.info("Service: Update Expense type details with id {}", id);
		Optional<ExpenseType> optional = expenseTypeRepository.findById(id);
		if (optional.isPresent()) {
			logger.info("Service: Expense type details found with id {} for update operation", id);
			ExpenseType expenseType = expenseTypeMapper.getExpenseTypeBuilder(expenseTypeDto);
			expenseTypeRepository.save(expenseType);
			return utils.objectMapperSuccess(expenseTypeMapper.getExpenseTypeDtoBuilder(expenseType),
					"Expense type Details Updated");
		}
		logger.info("Service: Expense type details not found with id {} for update operation", id);
		return utils.objectMapperError("Expense type Details Not Found !");
	}

	@Transactional
	public String delete(int id) {
		logger.info("Service: Delete Expense type details with id {}", id);
		int isDelete = expenseTypeRepository.updateActiveStatus(id);
		if (isDelete > 0) {
			logger.info("Service: Expense type details found with id {} for delete operation{}", id);
			return utils.objectMapperSuccess("Expense type Deleted Successfully");
		}
		logger.info("Service: Expense type details not found with id {} for delete operation{}", id);
		return utils.objectMapperError("Expense type Deleted Failed");
	}

	@Transactional
	public String getById(int id) {
		logger.info("Service: Fetching Expense type details with id {}", id);
		Optional<ExpenseType> expenseType = expenseTypeRepository.findById(id);
		if (expenseType.isPresent()) {
			logger.info("Service: Expense type details found with id {}", id);
			ExpenseTypeDto dto = expenseTypeMapper.getExpenseTypeDtoBuilder(expenseType.get());
			return utils.objectMapperSuccess(dto, "Expense type Details");
		}
		logger.info("Service: Expense type details not found with id {}", id);
		return utils.objectMapperError("Expense type Details Not found, Id :" + id);
	}

	@Transactional
	public String findActiveList(String searchTerm, Integer pageNo, Integer pageSize, String sortBy) {
		logger.info("Service: Fetching list of Expense type details ");
		PageRequest pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<ExpenseType> pagedResult = null;
		if (StringUtils.isBlank(searchTerm)) {
			pagedResult = expenseTypeRepository.findByIsActive("yes", pageable);
		} else {
			pagedResult = expenseTypeRepository.findByNameContainingIgnoreCaseAndIsActive(searchTerm, "yes", pageable);
		}

		Page<ExpenseTypeDto> page = pagedResult.map(new Function<ExpenseType, ExpenseTypeDto>() {
			@Override
			public ExpenseTypeDto apply(ExpenseType entity) {
				ExpenseTypeDto dto = expenseTypeMapper.getExpenseTypeDtoBuilder(entity);
				return dto;
			}
		});
		logger.info("Service: Fetching list of Expense type details, total records: {}", page.getTotalElements());
		return utils.objectMapperSuccess(page, "All Acive Expense type list.");
	}

}
