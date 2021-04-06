package com.sunan.exception;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.sunan.utils.JsonUtils;


/**
 * @author Nivas Mane-Patil
 *
 * Feb 15, 2018
 */

@ControllerAdvice
@RestController
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	JsonUtils utils;
	
	@ExceptionHandler(NotFoundException.class)
	public final ResponseEntity<String> handleUserNotFoundException(NotFoundException ex,
			WebRequest request) {
		logger.error(ex.getMessage(), ex);
		ApiErrorDetails errorDetails = new ApiErrorDetails(HttpStatus.NOT_FOUND, LocalDateTime.now(), ex.getMessage(),
				request.getDescription(false),ex.getClass().getName());
		return new ResponseEntity<String>(utils.objectMapperError(errorDetails, ex.getMessage()), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(BadRequestException.class)
	public final ResponseEntity<String> handleBadRequestException(BadRequestException ex,
			WebRequest request) {
		logger.error(ex.getMessage(), ex);
		ApiErrorDetails errorDetails = new ApiErrorDetails(HttpStatus.BAD_REQUEST, LocalDateTime.now(), ex.getMessage(),
				request.getDescription(false),ex.getClass().getName());
		return new ResponseEntity<String>(utils.objectMapperError(errorDetails, ex.getMessage()), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(BadRequest.class)
	public final ResponseEntity<ApiErrorDetails> handleBadRequestException(NotFoundException ex,
			WebRequest request) {
		logger.error(ex.getMessage(), ex);
		ApiErrorDetails errorDetails = new ApiErrorDetails(HttpStatus.NOT_FOUND, LocalDateTime.now(), ex.getMessage(),
				request.getDescription(false),"");
		return new ResponseEntity<ApiErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
	} 
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<String> handleAllExceptions(Exception ex,  HandlerMethod handlerMethod, WebRequest request) {
		logger.error(ex.getMessage(), ex);
		ApiErrorDetails errorDetails = new ApiErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now(), ex.getMessage(),
				request.getDescription(false),ex.getClass().getName());
		 ex.printStackTrace();
		return new ResponseEntity<String>(utils.objectMapperError(errorDetails, "Something went wrong"), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
