package com.sunan.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author Nivas Mane-Patil
 *
 * Feb 15, 2018
 */
public class ApiErrorDetails {

	private HttpStatus status;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timestamp;
	private String message;
	private String debugMessage;
	private String exceptionType;
	
	public ApiErrorDetails(HttpStatus status, LocalDateTime timestamp, String message, String debugMessage, String exceptionType) {
		super();
		this.status = status;
		this.timestamp = timestamp;
		this.message = message;
		this.debugMessage = debugMessage;
		this.exceptionType = exceptionType;
	}

	
	public String getExceptionType() {
		return exceptionType;
	}


	public void setExceptionType(String exceptionType) {
		this.exceptionType = exceptionType;
	}


	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDebugMessage() {
		return debugMessage;
	}

	public void setDebugMessage(String debugMessage) {
		this.debugMessage = debugMessage;
	}

	@Override
	public String toString() {
		return "ApiErrorDetails [status=" + status + ", timestamp=" + timestamp + ", message=" + message
				+ ", debugMessage=" + debugMessage + ", exceptionType=" + exceptionType + "]";
	}
 
}
