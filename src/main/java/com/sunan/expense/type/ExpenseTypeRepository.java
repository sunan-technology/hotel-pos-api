package com.sunan.expense.type;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sunan.model.ExpenseType;

@Repository
public interface ExpenseTypeRepository  extends PagingAndSortingRepository<ExpenseType, Integer>{
	
	public Optional<ExpenseType> findById(int id);

	Page<ExpenseType> findByNameContainingIgnoreCaseAndIsActive(String name, String active, Pageable pageable);

	Page<ExpenseType> findByIsActive(String active, Pageable pageable);

	@Modifying
	@Query("UPDATE ExpenseType SET isActive='no' WHERE id= :id")
	int updateActiveStatus(@Param("id") int id);

}
