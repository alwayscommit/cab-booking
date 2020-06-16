package com.assignment.cab_booking.exception;

import java.util.Date;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ApplicationExceptionsHandler {

	@ExceptionHandler(value = { BookingServiceException.class })
	public ResponseEntity<Object> handleCabServiceException(BookingServiceException ex, WebRequest request) {
		ErrorMessage error = new ErrorMessage(new Date(), ex.getMessage());
		return new ResponseEntity<Object>(error, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<Object> handleOtherException(Exception ex, WebRequest request) {
		ex.printStackTrace();
		ErrorMessage error = new ErrorMessage(new Date(), ex.getMessage());
		return new ResponseEntity<Object>(error, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = { DataIntegrityViolationException.class })
	public ResponseEntity<Object> handleDataIntegrityViolationException(Exception ex, WebRequest request) {
		ex.printStackTrace();
		ErrorMessage error = new ErrorMessage(new Date(), ex.getMessage());
		return new ResponseEntity<Object>(error, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
