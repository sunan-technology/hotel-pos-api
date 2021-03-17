package com.sunan.purchase;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.sunan.model.Purchase;

@Repository
public interface PurchaseRepository extends PagingAndSortingRepository<Purchase, Integer> {
	
	public Optional<Purchase> findById(int id);

}
