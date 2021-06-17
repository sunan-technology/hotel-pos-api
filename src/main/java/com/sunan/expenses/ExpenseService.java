package com.sunan.expenses;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sunan.expense.type.ExpenseTypeRepository;
import com.sunan.model.Expense;
import com.sunan.model.ExpenseType;
import com.sunan.model.ExpenseType_;
import com.sunan.model.Expense_;
import com.sunan.model.Hotel;
import com.sunan.utils.JsonUtils;

@Service
public class ExpenseService implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(ExpenseService.class);
	
	@Autowired
	private ExpenseRepository expenseRepository;
	
	@Autowired
	private ExpenseTypeRepository  expenseTypeRepository;
	
	@Autowired
	ExpenseMapper expenseMapper;
	
	@Autowired
	private JsonUtils utils;
	
	
	@Transactional
	public String save(ExpenseDto expenseDto,int hotelId) {
		
		Optional<ExpenseType> expenseType=expenseTypeRepository.findById(expenseDto.getExpenseTypeId());
		if(expenseType.isPresent()) {
			logger.info("Service : saving expense deatils");
			Expense expense= expenseMapper.getExpenseBuilder(expenseDto);
			expense.setHotel(new Hotel(hotelId));
			expenseRepository.save(expense);
			logger.info("Service: Saved Expense details");
			return utils.objectMapperSuccess(expenseMapper.getExpenseDtoBuilder(expense),
					"Expense  Details Saved");
			
		}else {
			logger.info("Service: Expense type not found");
			return utils.objectMapperError("Expense type not found");
		}
		
	}
	
	
	@Transactional
	public String update(ExpenseDto expenseDto, int id,int hotelId) {
		logger.info("Service: Update Expense details with id {}", id);
		Optional<Expense> optional = expenseRepository.findById(id);
		if (optional.isPresent()) {
			logger.info("Service: Expense  details found with id {} for update operation", id);
			Expense expense= expenseMapper.getExpenseBuilder(expenseDto);
			expense.setHotel(new Hotel(hotelId));
			expenseRepository.save(expense);
			return utils.objectMapperSuccess(expenseMapper.getExpenseDtoBuilder(expense),
					"Expense  Details updated");
		}
		logger.info("Service: Expense details not found with id {} for update operation", id);
		return utils.objectMapperError("Expense Details Not Found !");
	}
	
	
	@Transactional
	public String delete(int id) {
		logger.info("Service: Delete Expense  details with id {}", id);
		int isDelete = expenseRepository.updateActiveStatus(id);
		if (isDelete > 0) {
			logger.info("Service: Expense details found with id {} for delete operation{}", id);
			return utils.objectMapperSuccess("Expense Deleted Successfully");
		}
		logger.info("Service: Expense details not found with id {} for delete operation{}", id);
		return utils.objectMapperError("Expense Deleted Failed");
	}

	
	@Transactional
	public String getById(int id) {
		logger.info("Service: Fetching Expense  details with id {}", id);
		Optional<Expense> expense = expenseRepository.findById(id);
		if (expense.isPresent()) {
			logger.info("Service: Expense  details found with id {}", id);
			ExpenseDto dto = expenseMapper.getExpenseDtoBuilder(expense.get());
			return utils.objectMapperSuccess(dto, "Expense  Details");
		}
		logger.info("Service: Expense  details not found with id {}", id);
		return utils.objectMapperError("Expense  Details Not found, Id :" + id);
	}
	
	@Transactional
	public String findActiveList(String searchTerm, Integer pageNo, Integer pageSize, String sortBy) {
		logger.info("Service: Fetching list of Expense  details ");
		PageRequest pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Expense> pagedResult = null;
			pagedResult = expenseRepository.findByIsActive("yes", pageable);
		

		Page<ExpenseDto> page = pagedResult.map(new Function<Expense, ExpenseDto>() {
			@Override
			public ExpenseDto apply(Expense entity) {
				ExpenseDto dto = expenseMapper.getExpenseDtoBuilder(entity);
				return dto;
			}
		});
		logger.info("Service: Fetching list of Expense details, total records: {}", page.getTotalElements());
		return utils.objectMapperSuccess(page, "All Acive Expense  list.");
	}

    @Transactional
	public String getExpenseCount(Date fromDate, Date toDate, int hotelId) {
		logger.info("Service : fetching expense count details");
		
		List<ExpenseType> expenseType= (List<ExpenseType>) expenseTypeRepository.findAll();
		Map<String ,Long> result=new HashMap<>();
		
		for(ExpenseType type :expenseType) {
//			Specification<Expense> specification=getExpenseCountSpecification(type.getName(), fromDate, toDate, hotelId);
			Long amountCount= expenseRepository.getExpenseCount(type.getName(), fromDate, toDate, new Hotel(hotelId));
			amountCount=(amountCount==null)? 0:amountCount;
			result.put(type.getName(), amountCount);
		}
		logger.info("Service: Expense count details ");
		return utils.objectMapperSuccess(result, "Expense count details");
	}
    
    Specification<Expense> getExpenseCountSpecification(String expenseType, Date fromDate,Date toDate,int hotelId){
    	Specification<Expense> specification=new Specification<Expense>() {

    		/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			List<Predicate> predicates = new ArrayList<Predicate>();
			@Override
			public Predicate toPredicate(Root<Expense> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				
				
				Join<Expense,ExpenseType> join=root.join(Expense_.EXPENSE_TYPE);
				if(!expenseType.isEmpty()) {
					Predicate predicate=criteriaBuilder.equal(join.get(ExpenseType_.NAME), expenseType);
					predicates.add(predicate);
				}
				
				if (fromDate != null && toDate != null) {
					Predicate date = criteriaBuilder.between(root.get(Expense_.EXPENSE_DATE), fromDate, toDate);
					predicates.add(date);
				}
				query.multiselect(criteriaBuilder.sum(root.<BigDecimal> get("amount")));
				query.where(criteriaBuilder.and(predicates.toArray(new Predicate[] {}))/* .IN(INCLAUSE) */)
		        
				.orderBy(criteriaBuilder.desc(root.get("id")));
					return query.getRestriction();
			}
		};
		return specification;
    }

}
