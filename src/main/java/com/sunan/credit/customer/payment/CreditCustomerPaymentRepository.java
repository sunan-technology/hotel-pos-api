package com.sunan.credit.customer.payment;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.sunan.model.CreditCustomerPayment;

@Repository
public interface CreditCustomerPaymentRepository extends PagingAndSortingRepository<CreditCustomerPayment, Integer> {
	
	public Optional<CreditCustomerPayment> findById(int id);
	
	

}
