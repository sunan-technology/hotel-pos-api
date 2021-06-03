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
	
	  @GetMapping("/get-over-all-report") 
	  public ResponseEntity<?> getOverAllReport( @RequestParam(name = "fromDate", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date fromDate, @RequestParam(name = "toDate", required =
	  false) @DateTimeFormat(pattern="yyyy-MM-dd") Date toDate,@RequestParam(name ="operator") String operator, @RequestHeader("hotelId") int hotelId){
	  
	  logger.info("Getting over all billing report details"); return new
	  ResponseEntity<>(posReportService.overAllReport(hotelId, fromDate, toDate,operator), HttpStatus.OK); 
	  }
	  
	  

//	  @GetMapping("/get-over-all-report-one") public ResponseEntity<?> getOverAllReportOne(@RequestParam(defaultValue = "0") Integer pageNo,@RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "id") String sortBy,
//	   @RequestParam(name = "fromDate", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date fromDate, @RequestParam(name = "toDate", required =
//	  false) @DateTimeFormat(pattern="yyyy-MM-dd") Date toDate, @RequestHeader("hotelId") int hotelId){
//	  
//	  logger.info("Getting over all billing report details"); return new
//	  ResponseEntity<>(posReportService.overAllReportOne(pageNo, pageSize, sortBy,hotelId, fromDate, toDate), HttpStatus.OK); 
//	  }
	  
	  @GetMapping("/get-purchase-stock-report") 
	  public ResponseEntity<?> getPerchaseStockReport(@RequestParam(name = "fromDate", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date fromDate, @RequestParam(name = "toDate", required =
	  false) @DateTimeFormat(pattern="yyyy-MM-dd") Date toDate,@RequestParam(name ="supplierId") int  supplierId, @RequestHeader("hotelId") int hotelId){
		  logger.info("Controller : Getting perchase stock report details");
		  return new ResponseEntity<>(posReportService.purchaseStockReport(fromDate, toDate,supplierId,hotelId), HttpStatus.OK); 
	  }
	  
	  @GetMapping("/get-internal-transfer-report") 
	  public ResponseEntity<?> getInternalTransferReport(@RequestParam(name = "fromDate", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date fromDate, @RequestParam(name = "toDate", required =
			  false) @DateTimeFormat(pattern="yyyy-MM-dd") Date toDate,@RequestParam(name ="kitchenId") int  kitchenId,@RequestParam(name ="invoiceno") String  invoiceNo,@RequestParam(name ="payment") String  payment,@RequestParam(name ="purchasetype") String  purchaseType, @RequestHeader("hotelId") int hotelId){
		  logger.info("Controller : Getting internal transfer report details");
		  return new ResponseEntity<>(posReportService.internalTransferReport(fromDate, toDate,kitchenId,invoiceNo,payment,purchaseType,hotelId), HttpStatus.OK); 
	  }
	  
	  
	  @GetMapping("/get-supplier-report") 
	  public ResponseEntity<?> getSupplierrReport(@RequestParam(name = "fromDate", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date fromDate, @RequestParam(name = "toDate", required =
			  false) @DateTimeFormat(pattern="yyyy-MM-dd") Date toDate,@RequestParam(name ="supplierId") int  supplierId,@RequestParam(name ="purchasetype") String  purchaseType, @RequestHeader("hotelId") int hotelId){
		  logger.info("Controller : Getting supplier report details");
		  return new ResponseEntity<>(posReportService.internalSupplierReport(fromDate, toDate,supplierId,purchaseType,hotelId), HttpStatus.OK); 
	  }
	  
	  //TODO :item wise internal transfer report
	  @GetMapping("/get-item-wise-internal-transfer-report") 
	  public ResponseEntity<?> getItemWiseInternalTransferReport(@RequestParam(name = "fromDate", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date fromDate, @RequestParam(name = "toDate", required =
			  false) @DateTimeFormat(pattern="yyyy-MM-dd") Date toDate,@RequestParam(name ="kitchenId") int  kitchenId,@RequestParam(name ="rawmatrialname") String  rawMatrialName, @RequestHeader("hotelId") int hotelId){
		  logger.info("Controller : Getting item wise internal transfer report details");
		  return new ResponseEntity<>(posReportService.getItemWiseinternalTransferReport(fromDate, toDate,kitchenId,rawMatrialName,hotelId), HttpStatus.OK); 
	  }
	  
	  @GetMapping("/get-current-stock") 
	  public ResponseEntity<?> getCurrentStock(@RequestParam(name ="rawmatrialname") String  rawMatrialName, @RequestHeader("hotelId") int hotelId){
		  logger.info("Controller : Getting current stock details");
		  return new ResponseEntity<>(posReportService.getCurrentStock(rawMatrialName,hotelId), HttpStatus.OK); 
	  }
	  
	  
	  @GetMapping("/internal-transfer-report-list") 
	  public ResponseEntity<?> internalTransferReportList(@RequestParam(name = "fromDate", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date fromDate, @RequestParam(name = "toDate", required =
			  false) @DateTimeFormat(pattern="yyyy-MM-dd") Date toDate,@RequestParam(name ="kitchenId") int  kitchenId, @RequestHeader("hotelId") int hotelId){
		  logger.info("Controller : Getting internal transfer report details");
		  return new ResponseEntity<>(posReportService.internalTransferReportList(fromDate, toDate,kitchenId,hotelId), HttpStatus.OK); 
	  }
	   
	 

}
