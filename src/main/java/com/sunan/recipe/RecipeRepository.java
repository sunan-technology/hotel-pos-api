package com.sunan.recipe;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.sunan.model.Recipe;

@Repository
public interface RecipeRepository extends PagingAndSortingRepository<Recipe, Integer> {
	
	public Optional<Recipe> findById(int id);


	public Page<Recipe> findByIsActive(String active, Pageable pageable);

	@Modifying
	@Query("UPDATE Recipe SET isActive='no' WHERE id= :id")
	int updateActiveStatus(int id);
	
	

}
