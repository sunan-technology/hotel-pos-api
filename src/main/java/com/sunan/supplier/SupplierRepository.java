package com.sunan.supplier;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sunan.model.Supplier;

@Repository
public interface SupplierRepository extends PagingAndSortingRepository<Supplier, Integer> {

	public Optional<Supplier> findBySupplierId(int id);

	public Page<Supplier> findByIsActive(String active, Pageable pageable);

	@Modifying
	@Query("UPDATE Supplier SET isActive='no' WHERE id= :id ")
	int updateIsActiveStatus(@Param("id") int id);

}
