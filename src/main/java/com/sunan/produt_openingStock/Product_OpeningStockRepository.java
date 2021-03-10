package com.sunan.produt_openingStock;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sunan.model.Product_OpeningStock;
@Repository
public interface Product_OpeningStockRepository extends PagingAndSortingRepository<Product_OpeningStock, Integer> {

	public Optional<Product_OpeningStock> findById(int id);

//	public Page<Product_OpeningStock> findByNameContainingIgnoreCaseAndIsActive(String name, String active,
//			Pageable pageable);

	public Page<Product_OpeningStock> findByIsActive(String active, Pageable pageable);

	@Modifying
	@Query("UPDATE Product_OpeningStock SET isActive='no' WHERE id= :id ")
	int updateIsActiveStatus(@Param("id") int id);

}
