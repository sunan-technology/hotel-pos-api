package com.sunan.stock.store;

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
@RequestMapping(RequestMappingConstants.STOCK_STORE)
@Api(value = "Stock_Store profile", description = "Operations related to stock store")
public class StockStoreController {

	private static final Logger logger = LoggerFactory.getLogger(StockStoreController.class);

	@Autowired
	private StockStoreService stockStoreService;

	@GetMapping()
	public ResponseEntity<?> getAllList(@RequestParam(name = "searchTerm", required = false) String searchTerm,
			@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "id") String sortBy,@RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Fetching list stock store details");
		return new ResponseEntity<>(stockStoreService.findActiveList(searchTerm, pageNo, pageSize, sortBy),
				HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable int id,@RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Fetching stock details with id {}", id);
		return new ResponseEntity<>(stockStoreService.getById(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> save(@RequestBody StockStoreDto stockStoreDto,@RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Save stock details ");
		return new ResponseEntity<>(stockStoreService.save(stockStoreDto,hotelId), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody StockStoreDto stockStoreDto, @PathVariable int id,@RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Update stock details by id: {}", id);
		return new ResponseEntity<>(stockStoreService.update(stockStoreDto, id,hotelId), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable int id,@RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Delete stock details by id: {}", id);
		return new ResponseEntity<>(stockStoreService.delete(id), HttpStatus.OK);
	}

}
