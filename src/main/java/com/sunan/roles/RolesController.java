package com.sunan.roles;

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
@RequestMapping(RequestMappingConstants.ROLES)
@Api(value = "Roles profile", description = "Operations related to roles")
public class RolesController {

	private static final Logger logger = LoggerFactory.getLogger(RolesController.class);

	@Autowired
	private RolesService rolesService;

	@GetMapping()
	public ResponseEntity<?> getAllList(@RequestParam(name = "searchTerm", required = false) String searchTerm,
			@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "id") String sortBy, @RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Fetching list roles details");
		return new ResponseEntity<>(rolesService.findActiveList(searchTerm, pageNo, pageSize, sortBy, hotelId),
				HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable int id, @RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Fetching roles details with id {}", id);
		return new ResponseEntity<>(rolesService.getById(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> save(@RequestBody RolesDto rolesDto, @RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Save roles details ");
		return new ResponseEntity<>(rolesService.save(rolesDto, hotelId), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody RolesDto rolesDto, @PathVariable int id,
			@RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Update roles details by id: {}", id);
		return new ResponseEntity<>(rolesService.update(rolesDto, id, hotelId), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable int id, @RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Delete roles details by id: {}", id);
		return new ResponseEntity<>(rolesService.delete(id), HttpStatus.OK);
	}

}
