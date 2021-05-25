package com.sunan.raw.matrial;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.sunan.model.Recipe;
import com.sunan.model.RecipeRawMatrial;
@Repository
public interface RecipeRawMatrialRepository extends PagingAndSortingRepository<RecipeRawMatrial, Integer> {

	List<RecipeRawMatrial> findByRecipe(Recipe recipe);
//	Optional<RecipeRawMatrial> findByRecipe(Recipe recipe);
	
}
