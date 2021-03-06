package com.assignment.cab_booking.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.assignment.cab_booking.constants.ExceptionConstants;

@ControllerAdvice
public class ApplicationExceptionsHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationExceptionsHandler.class);

	@ExceptionHandler(value = { BookingServiceException.class })
	public ResponseEntity<Object> handleCabServiceException(BookingServiceException ex, WebRequest request) {
		LOGGER.error("BookingServiceException occurred :: " + ex);
		ErrorMessage error = new ErrorMessage(new Date(), ex.getMessage());
		return new ResponseEntity<Object>(error, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<Object> handleOtherException(Exception ex, WebRequest request) {
		LOGGER.error("Exception occurred :: " + ex);
		ex.printStackTrace();
		ErrorMessage error = new ErrorMessage(new Date(), ex.getMessage());
		return new ResponseEntity<Object>(error, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = { DataIntegrityViolationException.class })
	public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex,
			WebRequest request) {
		LOGGER.error("DataIntegrityViolationException occurred :: " + ex);

		ErrorMessage error = new ErrorMessage(new Date(), ExceptionConstants.DB_CONSTRAINT_MESSAGE);
		return new ResponseEntity<Object>(error, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = { MethodArgumentNotValidException.class })
	public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex,
			WebRequest request) {
		LOGGER.error("MethodArgumentNotValidException occurred :: " + ex);

		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});

		return new ResponseEntity<Object>(errors, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

}
