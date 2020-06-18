package com.assignment.cab_booking.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.cab_booking.mapper.BookingMapper;
import com.assignment.cab_booking.model.BookingState;
import com.assignment.cab_booking.model.BookingStateRequest;
import com.assignment.cab_booking.model.dto.BookingDTO;
import com.assignment.cab_booking.model.request.BookingRequest;
import com.assignment.cab_booking.model.response.BookingRest;
import com.assignment.cab_booking.model.response.BookingStatusRest;
import com.assignment.cab_booking.service.BookingService;
import com.assignment.cab_booking.view.CabBookingStatus;
import com.assignment.cab_booking.view.CustomerBookingHistory;

@RestController
@RequestMapping("/booking")
public class BookingController {

	private static final Logger LOGGER = LoggerFactory.getLogger(BookingController.class);

	private BookingService bookingService;

	private BookingMapper bookingMapper;

	@Autowired
	public BookingController(BookingService bookingService, BookingMapper bookingMapper) {
		this.bookingService = bookingService;
		this.bookingMapper = bookingMapper;
	}

	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BookingRest> bookCab(@Valid @RequestBody BookingRequest bookingRequest) {
		LOGGER.info(String.format("Booking cab for customer :: %s", bookingRequest.getCustomerMobileNumber()));
		BookingDTO bookingDTO = bookingMapper.mapToDTO(bookingRequest);

		BookingDTO bookedCar = bookingService.bookCab(bookingDTO);

		BookingRest bookingResponse = bookingMapper.mapToRest(bookedCar);
		return new ResponseEntity<BookingRest>(bookingResponse, HttpStatus.CREATED);
	}

	@GetMapping("/status")
	public ResponseEntity<List<CabBookingStatus>> getCabs() {
		List<CabBookingStatus> cabStatusList = bookingService.findAllCabBookingStatus();
		return new ResponseEntity<List<CabBookingStatus>>(cabStatusList, HttpStatus.OK);
	}
	
	@GetMapping("/customer/{customerNumber}/history")
	public ResponseEntity<List<CustomerBookingHistory>> getCustomerBookingHistory(@PathVariable String customerNumber) {
		List<CustomerBookingHistory> bookingHistory = bookingService.getCustomerBookingHistory(customerNumber);
		return new ResponseEntity<List<CustomerBookingHistory>>(bookingHistory, HttpStatus.OK);
	}

	@PutMapping(value = "/{referenceNo}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BookingStatusRest> updateBooking(@PathVariable String referenceNo,
			@RequestParam(name = "state") BookingStateRequest state) {
		LOGGER.info(String.format("Assigning completed status for booking referenceNo :: %s", referenceNo));

		BookingDTO bookedCar = bookingService.updateBooking(referenceNo, BookingState.valueOf(state.toString()));

		BookingStatusRest bookingStatusResponse = bookingMapper.mapToStatusRest(bookedCar);
		return new ResponseEntity<BookingStatusRest>(bookingStatusResponse, HttpStatus.OK);
	}

}
