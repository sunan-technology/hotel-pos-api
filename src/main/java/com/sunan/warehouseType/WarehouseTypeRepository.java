package com.sunan.warehouseType;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sunan.model.WarehouseType;

@Repository
public interface WarehouseTypeRepository extends PagingAndSortingRepository<WarehouseType, Integer> {

	public Optional<WarehouseType> findById(int id);

	Page<WarehouseType> findByNameContainingIgnoreCaseAndIsActive(String name, String active, Pageable pageable);

	Page<WarehouseType> findByIsActive(String active, Pageable pageable);

	@Modifying
	@Query("UPDATE WarehouseType SET isActive='no' WHERE id= :id")
	int updateActiveStatus(@Param("id") int id);
	
	

}
