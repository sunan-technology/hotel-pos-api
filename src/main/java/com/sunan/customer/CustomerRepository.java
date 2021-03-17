package com.sunan.customer;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.sunan.model.Customer;

@Repository
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Integer> {

	public Optional<Customer> findById(int id);

	// public Page<Customer>
	// findByCategoryNameContainingIgnoreCaseAndIsActive(String categoryName,String
	// active,Pageable pageable);

	public Page<Customer> findByIsActive(String active, Pageable pageable);

	@Modifying
	@Query("UPDATE Customer SET isActive='no' WHERE id= :id")
	int updateActiveStatus(int id);

}
