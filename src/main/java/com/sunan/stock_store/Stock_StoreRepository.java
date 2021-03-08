package com.sunan.stock_store;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sunan.model.Stock_Store;

@Repository
public interface Stock_StoreRepository extends PagingAndSortingRepository<Stock_Store, Integer> {

	public Optional<Stock_Store> findById(int id);

	public Page<Stock_Store> findByDishContainingIgnoreCaseAndIsActive(String searchTerm, String active, Pageable pageable);

	public Page<Stock_Store> findByIsActive(String active, Pageable pageable);

	@Modifying
	@Query("UPDATE Stock_Store SET isActive='no' WHERE id= :id ")
	int updateIsActiveStatus(@Param("id") int id);

}
