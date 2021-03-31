package com.sunan.recipe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sunan.constants.RequestMappingConstants;

import io.swagger.annotations.Api;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(RequestMappingConstants.RECIPE)
@Api(value = "Recipe profile", description = "Operations related to recipe")
public class RecipeController {

	private static final Logger logger = LoggerFactory.getLogger(RecipeController.class);

	@Autowired
	private RecipeService recipeService;

	@PostMapping
	public ResponseEntity<?> save(@RequestBody RecipeDto recipeDto,@RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Save recipe details ");
		return new ResponseEntity<>(recipeService.save(recipeDto), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody RecipeDto recipeDto, @PathVariable int id,@RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Update customer details by id: {}", id);
		return new ResponseEntity<>(recipeService.update(recipeDto, id), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable int id,@RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Delete recipe details by id: {}", id);
		return new ResponseEntity<>(recipeService.delete(id), HttpStatus.OK);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable int id,@RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Fetching recipe details with id {}", id);
		return new ResponseEntity<>(recipeService.getById(id), HttpStatus.OK);
	}
	
	
	@GetMapping()
	public ResponseEntity<?> getAllList(@RequestParam(name = "searchTerm", required = false) String searchTerm,
			@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "id") String sortBy,@RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Fetching list recipe details");
		return new ResponseEntity<>(recipeService.findActiveList(searchTerm, pageNo, pageSize, sortBy),
				HttpStatus.OK);
	}

}
