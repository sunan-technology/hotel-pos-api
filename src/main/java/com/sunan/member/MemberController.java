package com.sunan.member;

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
@RequestMapping(RequestMappingConstants.MEMBER)
@Api(value = "Member profile", description = "Operations related to member")
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Autowired
	private MemberService memberService;

	@GetMapping()
	public ResponseEntity<?> getAllList(@RequestParam(name = "searchTerm", required = false) String searchTerm,
			@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "id") String sortBy,@RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Fetching list member details");
		return new ResponseEntity<>(memberService.findActiveList(searchTerm, pageNo, pageSize, sortBy), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable int id,@RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Fetching member details with id {}", id);
		return new ResponseEntity<>(memberService.getById(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> save(@RequestBody MemberDto memberDto,@RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Save member details ");
		return new ResponseEntity<>(memberService.save(memberDto), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody MemberDto memberDto, @PathVariable int id) {
		logger.info("Controller: Update member details by id: {}", id);
		return new ResponseEntity<>(memberService.update(memberDto, id), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) {
		logger.info("Controller: Delete member details by id: {}", id);
		return new ResponseEntity<>(memberService.delete(id), HttpStatus.OK);
	}
	
	@PostMapping("/credit-debit-funds")
	public ResponseEntity<?> saveCreditAndDebitFunds(@RequestBody MemberLedgerDto dto,@RequestHeader("hotelId") int hotelId) {
		logger.info("Controller: Save member ledger details ");
		return new ResponseEntity<>(memberService.saveCreditAndDebitFunds(dto), HttpStatus.OK);
		
	}
	
	@PutMapping("/credit-debit-funds/{id}")
	public ResponseEntity<?> updateCreditAndDebitFunds(@RequestBody MemberLedgerDto dto,@PathVariable int id)
	{
		logger.info("Controller: Update credit and debbit details by id: {}", id);
		return new ResponseEntity<>(memberService.updateCreditAndDebitFunds(dto, id), HttpStatus.OK);
	}
	
	@GetMapping("/get-member-balance/{id}")
	public ResponseEntity<?> getMemberBalance(@PathVariable int id,@RequestHeader("hotelId") int hotelId){
		logger.info("Controller : Fetching member balance details with id {}",id);
		return new ResponseEntity<>(memberService.getMemberBalance(id),HttpStatus.OK);
	}
	
}
