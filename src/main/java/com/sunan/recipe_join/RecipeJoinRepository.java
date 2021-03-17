package com.sunan.recipe_join;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.sunan.model.Recipe;
import com.sunan.model.RecipeJoin;
@Repository
public interface RecipeJoinRepository extends PagingAndSortingRepository<RecipeJoin, Integer> {

	Optional<RecipeJoin> findByRecipe(Recipe recipe);
	
	public Page<RecipeJoin> findByIsActive(String active, Pageable pageable);

	@Modifying
	@Query("UPDATE RecipeJoin SET isActive='no' WHERE id= :id")
	int updateActiveStatus(int id);
	
	@Modifying
	@Query("UPDATE RecipeJoin SET isActive='no' WHERE recipe= :recipe")
	int updateActiveStatusByRecipe(Recipe recipe);
	

}
