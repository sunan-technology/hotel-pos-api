package com.sunan.biling.kot.product;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.sunan.model.BillingInfoKOT;
import com.sunan.model.BillingOrderedProductKOT;

@Repository
public interface OrderedProductBillKOTRepository extends PagingAndSortingRepository<BillingOrderedProductKOT, Integer> {
	
//	public List<BillingOrderedProductKOT> findByBillId(BillingInfoKOT billingInfoKOT);

}
