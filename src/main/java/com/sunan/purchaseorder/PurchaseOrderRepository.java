package com.sunan.purchaseorder;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.sunan.model.PurchaseOrder;

@Repository
public interface PurchaseOrderRepository extends PagingAndSortingRepository<PurchaseOrder, Integer> {
	
	

}
