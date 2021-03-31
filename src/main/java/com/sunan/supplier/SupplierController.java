package com.sunan.supplier;

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
@RequestMapping(RequestMappingConstants.SUPPLIER)
@Api(value = "Supplier profile", description = "Operations related to supplier")
public class SupplierController {

	private static final Logger logger = LoggerFactory.getLogger(SupplierController.class);

	@Autowired
	private SupplierService supplierService;

	@GetMapping()
	public ResponseEntity<?> getAllList(@RequestParam(name = "searchTerm", required = false) String searchTerm,
			@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "id") String sortBy,@RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Fetching list storage type details");
		return new ResponseEntity<>(supplierService.findActiveList(searchTerm, pageNo, pageSize, sortBy),
				HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable int id,@RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Fetching supplier details with id {}", id);
		return new ResponseEntity<>(supplierService.getById(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> save(@RequestBody SupplierDto supplierDto,@RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Save supplier details ");
		return new ResponseEntity<>(supplierService.save(supplierDto), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody SupplierDto supplierDto, @PathVariable int id,@RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Update supplier details by id: {}", id);
		return new ResponseEntity<>(supplierService.update(supplierDto, id), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable int id,@RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Delete supplier details by id: {}", id);
		return new ResponseEntity<>(supplierService.delete(id), HttpStatus.OK);
	}
	
	@GetMapping("/get-supplier-balance/{id}")
	public ResponseEntity<?> getSupplierBalance(@PathVariable int id,@RequestHeader("hotelId") int hotelId){
		logger.info("Controller: Fetching supplier balance details with id {}", id);
		
		return new ResponseEntity<>(supplierService.getSupplierBalanceBySupplierId(id),HttpStatus.OK);
	}

}
