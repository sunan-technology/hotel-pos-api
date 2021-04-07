package com.sunan.storage.type;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sunan.model.StorageType;

@Repository
public interface StorageTypeRepository extends PagingAndSortingRepository<StorageType, Integer> {

	public Optional<StorageType> findById(int id);

	public Page<StorageType> findByNameContainingIgnoreCaseAndIsActive(String name, String active, Pageable pageable);

	public Page<StorageType> findByIsActive(String active, Pageable pageable);

	@Modifying
	@Query("UPDATE StorageType SET isActive='no' WHERE id= :id ")
	int updateIsActiveStatus(@Param("id") int id);

}
