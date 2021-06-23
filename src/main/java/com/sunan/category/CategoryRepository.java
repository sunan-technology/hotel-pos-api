package com.sunan.category;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.sunan.model.Category;
import com.sunan.model.Hotel;
@Repository
public interface CategoryRepository  extends PagingAndSortingRepository<Category, Integer>{
	
	
	public Optional<Category> findById(int id);
	
	public Page<Category> findByCategoryNameContainingIgnoreCaseAndIsActive(String categoryName,String active,Pageable pageable);
	
	public Page<Category> findByIsActiveAndHotel(String active,Pageable pageable,Hotel hotel);
	
	@Modifying
	@Query("UPDATE Category SET isActive='no' WHERE id= :id")
	int updateActiveStatus(int id);
	
	

}
