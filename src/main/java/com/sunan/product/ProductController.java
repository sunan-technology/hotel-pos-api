package com.sunan.product;

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
@RequestMapping(RequestMappingConstants.PRODUCT)
@Api(value = "Product profile", description = "Operations related to product")
public class ProductController {

	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductService productService;

	@GetMapping()
	public ResponseEntity<?> getAllList(@RequestParam(name = "searchTerm", required = false) String searchTerm,
			@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "id") String sortBy,@RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Fetching list product details");
		return new ResponseEntity<>(productService.findActiveList(searchTerm, pageNo, pageSize, sortBy), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable int id,@RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Fetching product details with id {}", id);
		return new ResponseEntity<>(productService.getById(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> save(@RequestBody ProductDto productDto,@RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Save product details ");
		return new ResponseEntity<>(productService.save(productDto), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody ProductDto productDto, @PathVariable int id,@RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Update product details by id: {}", id);
		return new ResponseEntity<>(productService.update(productDto, id), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable int id,@RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Delete product details by id: {}", id);
		return new ResponseEntity<>(productService.delete(id), HttpStatus.OK);
	}

}
