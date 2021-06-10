package com.sunan.raw.matrial;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@RequestMapping(RequestMappingConstants.RAWMATRIAL)
@Api(value = "Raw Matrial profile", description = "Operations related to raw matrials")
public class RawMatrialController {
	
	
	private static final Logger logger = LoggerFactory.getLogger(RawMatrialController.class);
	
	
	@Autowired
	private RawMatrialService rawMatrialService;
	
	
	@GetMapping()
	public ResponseEntity<?> getAllList(@RequestParam(name = "searchTerm", required = false) String searchTerm,
			@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "id") String sortBy,@RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Fetching list raw matrials details");
		return new ResponseEntity<>(rawMatrialService.findActiveList(searchTerm, pageNo, pageSize, sortBy,hotelId),
				HttpStatus.OK);
	}
	
	

	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable int id,@RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Fetching raw matrial details with id {}", id);
		return new ResponseEntity<>(rawMatrialService.getById(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> save(@RequestBody RawMatrialDto rawMatrialDto,@RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Save raw matrial details ");
		return new ResponseEntity<>(rawMatrialService.save(rawMatrialDto,hotelId), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody RawMatrialDto rawMatrialDto, @PathVariable int id,@RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Update raw matrial details by id: {}", id);
		return new ResponseEntity<>(rawMatrialService.update(rawMatrialDto, id,hotelId), HttpStatus.OK);
	}

}
