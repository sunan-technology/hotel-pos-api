package com.sunan.hotel;

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
@RequestMapping(RequestMappingConstants.HOTEL)
@Api(value = "Hotel profile", description = "Operations related to Hotel")
public class HotelController {
	
	@Autowired
	private HotelService hotelService;
	
	
	
	@GetMapping()
	public ResponseEntity<?> getAllList(@RequestParam(name = "searchTerm", required = false) String searchTerm,
			@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "id") String sortBy) {
//		logger.info("Getting list of" + className + " details");
//		UserDetailsImpl principal = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		System.out.println(principal.getAuthorities().toArray()[0]);
		return new ResponseEntity<>(hotelService.findActiveList(searchTerm, pageNo, pageSize, sortBy),
				HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable int id) {
	//	logger.info("Getting the " + className + " details, id: {}", id);
		return new ResponseEntity<>(hotelService.getById(id), HttpStatus.OK);
	}
	
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody HotelDto hotelDto) {
	//	logger.info("Add " + className + "  data");
		return new ResponseEntity<>(hotelService.save(hotelDto), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody HotelDto hotelDto, @PathVariable int id) {
		//logger.info("update " + className + " data by id: {}", categoryDto.getId());
		return new ResponseEntity<>(hotelService.update(hotelDto, id), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) {
		//logger.info("delete " + className + "  data by id: {}", id);
		return new ResponseEntity<>(hotelService.delete(id), HttpStatus.OK);
	}
	
}
