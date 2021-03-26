package com.sunan.table;

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
@RequestMapping(RequestMappingConstants.TABLE)
@Api(value = "HotelTable profile", description = "Operations related to hotel table")
public class HotelTableController {

	private static final Logger logger = LoggerFactory.getLogger(HotelTableController.class);

	@Autowired
	private HotelTableService tableService;

	@GetMapping()
	public ResponseEntity<?> getAllList(@RequestParam(name = "searchTerm", required = false) String searchTerm,
			@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "id") String sortBy,@RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Fetching list kitchen details");
		return new ResponseEntity<>(tableService.findActiveList(searchTerm, pageNo, pageSize, sortBy), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable int id,@RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Fetching table details with id {}", id);
		return new ResponseEntity<>(tableService.getById(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> save(@RequestBody HotelTableDto tableDto,@RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Save table details ");
		return new ResponseEntity<>(tableService.saveTable(tableDto), HttpStatus.OK);
	}



	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody HotelTableDto tableDto, @PathVariable int id) {
		logger.info("Controller: Update table details by id: {}", id);
		return new ResponseEntity<>(tableService.update(tableDto, id), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) {
		logger.info("Controller: Delete table details by id: {}", id);
		return new ResponseEntity<>(tableService.delete(id), HttpStatus.OK);
	}

}
