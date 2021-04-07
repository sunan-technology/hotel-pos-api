package com.sunan.credit.customer.ledger;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.sunan.model.CreditCustomer;
import com.sunan.model.CreditCustomerLedger;

@Repository
public interface CreditCustomerLedgerRepository extends PagingAndSortingRepository<CreditCustomerLedger, Integer> {

	//public Optional<CreditCustomerLedger> findByCreditCustomerId(int id);
	
	public Optional<CreditCustomerLedger> getCreditCustomerLedgerByCreditCustomer(CreditCustomer creditCustomer);

	@Query("SELECT SUM(b.credit-b.debit) FROM CreditCustomerLedger b WHERE creditCustomer= :creditCustomer")
	public Double getCreditCustomerBalanceByCreditCustomerId(CreditCustomer creditCustomer);
	

	public List<CreditCustomerLedger> findCreditCustomerByCreditCustomer(CreditCustomer creditCustomer);

}
