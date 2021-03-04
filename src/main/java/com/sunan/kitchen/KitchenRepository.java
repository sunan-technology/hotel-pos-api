package com.sunan.kitchen;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sunan.model.Kitchen;
@Repository
public interface KitchenRepository extends PagingAndSortingRepository<Kitchen, Integer> {
	
	public Optional<Kitchen> findById(int id);
	
	public Page<Kitchen> findByKitchenNameContainingIgnoreCaseAndIsActive(String kitchenName,String active, Pageable pageable);
	
	public Page<Kitchen> findByIsActive(String active,Pageable pageable);
	
	@Modifying
	@Query("UPDATE Kitchen SET isActive='no' WHERE id= :id ")
	int updateIsActiveStatus(@Param("id") int id);

}
