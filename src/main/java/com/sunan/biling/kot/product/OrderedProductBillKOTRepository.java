package com.sunan.biling.kot.product;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.sunan.model.BillingOrderedProductKOT;

@Repository
public interface OrderedProductBillKOTRepository extends PagingAndSortingRepository<BillingOrderedProductKOT, Integer> {

}
