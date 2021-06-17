package com.sunan.expenses;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
@RequestMapping(RequestMappingConstants.EXPENSE)
@Api(value = "Expense  profile", description = "Operations related to Expense ")
public class ExpenseController {
	
	private static final Logger logger = LoggerFactory.getLogger(ExpenseController.class);
	
	@Autowired
	private ExpenseService expenseService;
	
	@GetMapping()
	public ResponseEntity<?> getAllList(@RequestParam(name = "searchTerm", required = false) String searchTerm,
			@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "id") String sortBy,@RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Fetching list expense details");
		return new ResponseEntity<>(expenseService.findActiveList(searchTerm, pageNo, pageSize, sortBy),
				HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable int id,@RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Fetching Expense  details with id {}", id);
		return new ResponseEntity<>(expenseService.getById(id), HttpStatus.OK);
	}
	
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody ExpenseDto expenseDto,@RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Save Expense details ");
		return new ResponseEntity<>(expenseService.save(expenseDto,hotelId), HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody ExpenseDto expenseDto, @PathVariable int id,@RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Update Expense  details by id: {}", id);
		return new ResponseEntity<>(expenseService.update(expenseDto, id,hotelId), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable int id,@RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Delete Expense details by id: {}", id);
		return new ResponseEntity<>(expenseService.delete(id), HttpStatus.OK);
	}
	
	@GetMapping("/get-expense-count")
	public ResponseEntity<?> getExpenseCount(@RequestParam(name = "fromDate", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date fromDate, 
			@RequestParam(name = "toDate", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date toDate,@RequestHeader("hotelId") int hotelId){
		logger.info("Controller: Fetching  expense count details");
		return new ResponseEntity<>(expenseService.getExpenseCount(fromDate, toDate, hotelId),
				HttpStatus.OK);
	}


}
