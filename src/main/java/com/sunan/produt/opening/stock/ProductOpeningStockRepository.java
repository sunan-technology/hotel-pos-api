package com.sunan.produt.opening.stock;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sunan.model.ProductOpeningStock;
@Repository
public interface ProductOpeningStockRepository extends PagingAndSortingRepository<ProductOpeningStock, Integer> {

	public Optional<ProductOpeningStock> findById(int id);

//	public Page<Product_OpeningStock> findByNameContainingIgnoreCaseAndIsActive(String name, String active,
//			Pageable pageable);

	public Page<ProductOpeningStock> findByIsActive(String active, Pageable pageable);

	@Modifying
	@Query("UPDATE ProductOpeningStock SET isActive='no' WHERE id= :id ")
	int updateIsActiveStatus(@Param("id") int id);

}
