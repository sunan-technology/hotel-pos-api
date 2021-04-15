package com.sunan.pos.report;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sunan.constants.RequestMappingConstants;

import io.swagger.annotations.Api;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(RequestMappingConstants.POSREPORTS)
@Api(value = "POS Report profile", description = "Operations related to pos reports")
public class POSReportController {
	
	private static final Logger logger = LoggerFactory.getLogger(POSReportController.class);
	
	@Autowired
	private POSReportService posReportService;
	
	  @GetMapping("/get-over-all-report") public ResponseEntity<?> getOverAllReport(@RequestParam(defaultValue = "0") Integer pageNo,@RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "id") String sortBy,
	   @RequestParam(name = "fromDate", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date fromDate, @RequestParam(name = "toDate", required =
	  false) @DateTimeFormat(pattern="yyyy-MM-dd") Date toDate,@RequestParam(name ="operator") String operator, @RequestHeader("hotelId") int hotelId){
	  
	  logger.info("Getting over all billing report details"); return new
	  ResponseEntity<>(posReportService.overAllReport(pageNo, pageSize, sortBy,hotelId, fromDate, toDate,operator), HttpStatus.OK); 
	  }
	 

}
