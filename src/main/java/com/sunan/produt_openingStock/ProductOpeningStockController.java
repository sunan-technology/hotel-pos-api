package com.sunan.produt_openingStock;

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
@RequestMapping(RequestMappingConstants.PRODUCTOPENINGSTOCK)
@Api(value = "Product_OpeningStock profile", description = "Operations related to product opening stock")
public class ProductOpeningStockController {

	private static final Logger logger = LoggerFactory.getLogger(ProductOpeningStockController.class);

	@Autowired
	private ProductOpeningStockService productOpeningStockService;

	@GetMapping()
	public ResponseEntity<?> getAllList(@RequestParam(name = "searchTerm", required = false) String searchTerm,
			@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "id") String sortBy,@RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Fetching list storage type details");
		return new ResponseEntity<>(productOpeningStockService.findActiveList(searchTerm, pageNo, pageSize, sortBy),
				HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable int id,@RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Fetching product opening stock details with id {}", id);
		return new ResponseEntity<>(productOpeningStockService.getById(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> save(@RequestBody ProductOpeningStockDto productOpeningStockDto,@RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Save product opening stock details ");
		return new ResponseEntity<>(productOpeningStockService.save(productOpeningStockDto), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody ProductOpeningStockDto productOpeningStockDto, @PathVariable int id) {
		logger.info("Controller: Update product opening stock details by id: {}", id);
		return new ResponseEntity<>(productOpeningStockService.update(productOpeningStockDto, id), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) {
		logger.info("Controller: Deleteproduct opening stock details by id: {}", id);
		return new ResponseEntity<>(productOpeningStockService.delete(id), HttpStatus.OK);
	}

}
