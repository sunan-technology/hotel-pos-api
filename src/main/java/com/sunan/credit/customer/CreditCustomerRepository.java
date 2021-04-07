package com.sunan.credit.customer;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sunan.model.CreditCustomer;

@Repository
public interface CreditCustomerRepository extends PagingAndSortingRepository<CreditCustomer, Integer> {

	 Optional<CreditCustomer> findByCreditCustomerId(int id);

	public Page<CreditCustomer> findByIsActive(String active, Pageable pageable);

	@Modifying
	@Query("UPDATE CreditCustomer SET isActive='no' WHERE id= :id ")
	int updateIsActiveStatus(@Param("id") int id);

}
