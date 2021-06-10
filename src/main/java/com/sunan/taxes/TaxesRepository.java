package com.sunan.taxes;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.sunan.model.Taxes;
@Repository
public interface TaxesRepository extends PagingAndSortingRepository<Taxes, Integer> {

	public Page<Taxes> findByIsActive(String active, Pageable pageable);
	
}
