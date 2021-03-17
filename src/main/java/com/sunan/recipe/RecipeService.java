package com.sunan.recipe;

import java.io.Serializable;
import java.util.Optional;
import java.util.function.Function;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sunan.model.Recipe;
import com.sunan.model.RecipeJoin;
import com.sunan.recipe_join.RecipeJoinRepository;
import com.sunan.utils.JsonUtils;

@Service
public class RecipeService implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(RecipeService.class);

	@Autowired
	private RecipeRepository recipeRepository;

	@Autowired
	private RecipeJoinRepository recipeJoinRepository;

	@Autowired
	RecipeMapper recipeMapper;

	@Autowired
	private JsonUtils utils;

	@Transactional
	public String save(RecipeDto recipeDto) {

		Recipe recipe = recipeMapper.getRecipeBuilder(recipeDto);
		recipeRepository.save(recipe);

		RecipeJoin recipeJoin = recipeMapper.getRecipeJoinBuilder(recipeDto);
		recipeJoinRepository.save(recipeJoin);

		logger.info("Service: recipe details");
		return utils.objectMapperSuccess(recipeMapper.getRecipeDtoBuilder(recipe, recipeJoin), " Recipe Details Saved");

	}

	@Transactional
	public String update(RecipeDto recipeDto, int id) {
		logger.info("Service: Update recipe details with id {}", id);
		Optional<Recipe> optional = recipeRepository.findById(id);
		if (optional.isPresent()) {
			logger.info("Service: recipe details found with id {} for update operation", id);
			Recipe recipe = recipeMapper.getRecipeBuilder(recipeDto);
			recipeRepository.save(recipe);

			Optional<RecipeJoin> optionalRecipe_Join = recipeJoinRepository.findByRecipe(optional.get());
			if (optionalRecipe_Join.isPresent()) {
				RecipeJoin recipeJoin = recipeMapper.getRecipeJoinBuilder(optionalRecipe_Join.get(), recipeDto);
				recipeJoinRepository.save(recipeJoin);
			}

			return utils.objectMapperSuccess("Recipe Details Updated");
		}
		logger.info("Service: recipe details not found with id {} for update operation", id);
		return utils.objectMapperError("Recipe Details Not Found !");
	}

	@Transactional
	public String delete(int id) {
		logger.info("Service: Delete employee details with id {}", id);
		int isDelete = recipeRepository.updateActiveStatus(id);
		
		Optional<Recipe> optional = recipeRepository.findById(id);
		int isRecipeDelete = recipeJoinRepository.updateActiveStatusByRecipe(optional.get());
		if (isDelete > 0 && isRecipeDelete > 0) {
			logger.info("Service: employee details found with id {} for delete operation{}", id);
			return utils.objectMapperSuccess("Employee Deleted Successfully");
		}
		logger.info("Service: employee details not found with id {} for delete operation{}", id);
		return utils.objectMapperError("Employee Deleted Failed");
	}
	
	
	@Transactional
	public String getById(int id) {
		logger.info("Service: Fetching recipe details with id {}", id);
		Optional<Recipe> recipe = recipeRepository.findById(id);
		
		Optional<RecipeJoin> optionalRecipe_Join = recipeJoinRepository.findByRecipe(recipe.get());
		if (recipe.isPresent()) {
			logger.info("Service: recipe details found with id {}", id);
			RecipeDto dto = recipeMapper.getRecipeDtoBuilder(recipe.get(), optionalRecipe_Join.get());
			return utils.objectMapperSuccess(dto, "Recipe Details");
		}
		logger.info("Service: recipe details not found with id {}", id);
		return utils.objectMapperError("Recipe Details Not found, Id :" + id);
	}
	
	
	@Transactional
	public String findActiveList(String searchTerm, Integer pageNo, Integer pageSize, String sortBy) {
		logger.info("Service: Fetching list of recipe details ");
		PageRequest pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<RecipeJoin> pagedResult = null;

	//	pagedResult = recipeRepository.findByIsActive("yes", pageable);
		
		pagedResult = recipeJoinRepository.findByIsActive("yes", pageable);

		Page<RecipeDto> page = pagedResult.map(new Function<RecipeJoin, RecipeDto>() {
			@Override
			public RecipeDto apply(RecipeJoin entity) {
				RecipeDto dto = recipeMapper.getRecipeJoinDtoBuilder(entity);
				return dto;
			}
		});
		logger.info("Service: Fetching list of recipe details, total records: {}", page.getTotalElements());
		return utils.objectMapperSuccess(page, "All Active recipe list.");
	}


}
