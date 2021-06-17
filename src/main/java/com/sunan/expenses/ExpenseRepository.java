package com.sunan.expenses;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sunan.model.Expense;
import com.sunan.model.Hotel;

@Repository
public interface ExpenseRepository extends PagingAndSortingRepository<Expense, Integer> {

	public Optional<Expense> findById(int id);

	Page<Expense> findByIsActive(String active, Pageable pageable);

	@Modifying
	@Query("UPDATE Expense SET isActive='no' WHERE id= :id")
	int updateActiveStatus(@Param("id") int id);
	
	@Query("SELECT SUM(e.amount) FROM Expense e LEFT JOIN ExpenseType et ON e.expenseType =et.id WHERE e.hotel=:hotel AND et.name= :expenseType AND e.expenseDate BETWEEN :fromDate AND :toDate ")
	Long getExpenseCount(String expenseType,Date fromDate,Date toDate,Hotel hotel);
	
}
