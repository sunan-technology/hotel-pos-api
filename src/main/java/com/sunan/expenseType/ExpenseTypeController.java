package com.sunan.expenseType;

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
@RequestMapping(RequestMappingConstants.EXPENSE_TYPE)
@Api(value = "Expense Type profile", description = "Operations related to Expense type")
public class ExpenseTypeController {

	private static final Logger logger = LoggerFactory.getLogger(ExpenseTypeController.class);

	@Autowired
	private ExpenseTypeService expenseTypeService;

	@GetMapping()
	public ResponseEntity<?> getAllList(@RequestParam(name = "searchTerm", required = false) String searchTerm,
			@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "id") String sortBy) {
		logger.info("Controller: Fetching list expense details");
		return new ResponseEntity<>(expenseTypeService.findActiveList(searchTerm, pageNo, pageSize, sortBy),
				HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable int id) {
		logger.info("Controller: Fetching Expense type details with id {}", id);
		return new ResponseEntity<>(expenseTypeService.getById(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> save(@RequestBody ExpenseTypeDto expenseTypeDto) {
		logger.info("Controller: Save Expense type details ");
		return new ResponseEntity<>(expenseTypeService.save(expenseTypeDto), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody ExpenseTypeDto expenseTypeDto, @PathVariable int id) {
		logger.info("Controller: Update Expense type details by id: {}", id);
		return new ResponseEntity<>(expenseTypeService.update(expenseTypeDto, id), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) {
		logger.info("Controller: Delete Expense type details by id: {}", id);
		return new ResponseEntity<>(expenseTypeService.delete(id), HttpStatus.OK);
	}

}
