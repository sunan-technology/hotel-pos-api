package com.sunan.dish;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sunan.model.Category;
import com.sunan.model.Dish;
import com.sunan.model.Hotel;
@Repository
public interface DishRepository extends PagingAndSortingRepository<Dish, Integer> {

	public Optional<Dish> findByDishId(int id);

	public Page<Dish> findByDishNameContainingIgnoreCaseAndIsActive(String dishName, String active, Pageable pageable);

	public Page<Dish> findByIsActive(String active, Pageable pageable);

	@Modifying
	@Query("UPDATE Dish SET isActive='no' WHERE id= :id ")
	int updateIsActiveStatus(@Param("id") int id);
	
	public List<Dish> findByCategoryAndHotelAndIsActive(Category category,Hotel hotel,String active);

}
