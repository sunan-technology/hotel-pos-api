package com.sunan.warehouse;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sunan.model.Warehouses;
@Repository
public interface WarehousesRepository extends PagingAndSortingRepository<Warehouses, Integer> {
	
	public Optional<Warehouses> findById(int id);

	public Page<Warehouses> findByWarehouseNameContainingIgnoreCaseAndIsActive(String warehouseName, String active, Pageable pageable);

	public Page<Warehouses> findByIsActive(String active, Pageable pageable);

	@Modifying
	@Query("UPDATE Warehouses SET isActive='no' WHERE id= :id ")
	int updateIsActiveStatus(@Param("id") int id);

}
