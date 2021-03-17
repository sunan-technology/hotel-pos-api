package com.sunan.stock_store;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sunan.model.StockStore;

@Repository
public interface StockStoreRepository extends PagingAndSortingRepository<StockStore, Integer> {

	public Optional<StockStore> findById(int id);

	public Page<StockStore> findByDishContainingIgnoreCaseAndIsActive(String searchTerm, String active, Pageable pageable);

	public Page<StockStore> findByIsActive(String active, Pageable pageable);

	@Modifying
	@Query("UPDATE StockStore SET isActive='no' WHERE id= :id ")
	int updateIsActiveStatus(@Param("id") int id);

}
