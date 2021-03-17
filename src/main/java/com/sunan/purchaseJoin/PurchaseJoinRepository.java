package com.sunan.purchaseJoin;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.sunan.model.PerchaseJoin;
@Repository
public interface PurchaseJoinRepository extends PagingAndSortingRepository<PerchaseJoin, Integer> {
	
	

}
