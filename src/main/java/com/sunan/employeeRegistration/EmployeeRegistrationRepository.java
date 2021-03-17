package com.sunan.employeeRegistration;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.sunan.model.EmployeeRegistration;

@Repository
public interface EmployeeRegistrationRepository extends PagingAndSortingRepository<EmployeeRegistration, Integer> {

	public Optional<EmployeeRegistration> findById(int id);

	// public Page<Customer>
	// findByCategoryNameContainingIgnoreCaseAndIsActive(String categoryName,String
	// active,Pageable pageable);

	public Page<EmployeeRegistration> findByIsActive(String active, Pageable pageable);

	@Modifying
	@Query("UPDATE EmployeeRegistration SET isActive='no' WHERE id= :id")
	int updateActiveStatus(int id);

}
