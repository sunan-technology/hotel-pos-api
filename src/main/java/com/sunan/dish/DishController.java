package com.sunan.dish;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sunan.constants.RequestMappingConstants;

import io.swagger.annotations.Api;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(RequestMappingConstants.DISH)
@Api(value = "Dish profile", description = "Operations related to dish")
public class DishController {

	private static final Logger logger = LoggerFactory.getLogger(DishController.class);

	@Autowired
	private DishService dishService;

	@GetMapping()
	public ResponseEntity<?> getAllList(@RequestParam(name = "searchTerm", required = false) String searchTerm,
			@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "id") String sortBy) {
		logger.info("Controller: Fetching list dish details");
		return new ResponseEntity<>(dishService.findActiveList(searchTerm, pageNo, pageSize, sortBy), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable int id) {
		logger.info("Controller: Fetching dish details with id {}", id);
		return new ResponseEntity<>(dishService.getById(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> save(@RequestBody DishDto dishDto) {
		logger.info("Controller: Save dish details ");
		return new ResponseEntity<>(dishService.save(dishDto), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody DishDto dishDto, @PathVariable int id) {
		logger.info("Controller: Update dish details by id: {}", id);
		return new ResponseEntity<>(dishService.update(dishDto, id), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) {
		logger.info("Controller: Delete dish details by id: {}", id);
		return new ResponseEntity<>(dishService.delete(id), HttpStatus.OK);
	}

}
