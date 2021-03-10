package com.sunan.product;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sunan.model.Product;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Integer> {

	public Optional<Product> findById(int id);

	public Page<Product> findByProductNameContainingIgnoreCaseAndIsActive(String productName, String active,
			Pageable pageable);

	public Page<Product> findByIsActive(String active, Pageable pageable);

	@Modifying
	@Query("UPDATE Product SET isActive='no' WHERE id= :id ")
	int updateIsActiveStatus(@Param("id") int id);

}
