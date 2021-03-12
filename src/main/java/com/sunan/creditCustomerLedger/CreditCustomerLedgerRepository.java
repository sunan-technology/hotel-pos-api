package com.sunan.creditCustomerLedger;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.sunan.model.CreditCustomerLedger;

@Repository
public interface CreditCustomerLedgerRepository extends PagingAndSortingRepository<CreditCustomerLedger, Integer> {

	//public Optional<CreditCustomerLedger> findByCreditCustomerId(int id);
	
	public Optional<CreditCustomerLedger> getCreditCustomerLedgerByCreditCustomerId(int creditCustomerId);

	@Query("SELECT SUM(b.credit-b.debit) FROM CreditCustomerLedger b WHERE creditCustomerId= :creditCustomerId")
	public Double getCreditCustomerBalanceByCreditCustomerId(int creditCustomerId);
	
//	@Query("SELECT * FROM CreditCustomerLedger WHERE creditCustomerId = :creditCustomerId")
	public List<CreditCustomerLedger> findCreditCustomerByCreditCustomerId(int creditCustomerId);

}
