package com.assignment.cab_booking.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.cab_booking.mapper.BookingMapper;
import com.assignment.cab_booking.model.dto.BookingDTO;
import com.assignment.cab_booking.model.dto.CabDriverDTO;
import com.assignment.cab_booking.model.request.BookingRequest;
import com.assignment.cab_booking.model.response.BookingRest;
import com.assignment.cab_booking.model.response.CabDriverRest;
import com.assignment.cab_booking.service.BookingService;
import com.assignment.cab_booking.view.CabBookingStatus;

@RestController
@RequestMapping("/cab/booking")
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
	public ResponseEntity<BookingRest> bookCab(@RequestBody BookingRequest bookingRequest) {
		LOGGER.info(String.format("Booking cab for customer :: %s", bookingRequest.getCustomerMobileNumber()));

		BookingDTO bookingDTO = mapToBookingDTO(bookingRequest);

		BookingDTO bookedCar = bookingService.bookCab(bookingDTO);

		BookingRest bookingResponse = mapToBookingDTO(bookedCar);
		return new ResponseEntity<BookingRest>(bookingResponse, HttpStatus.CREATED);
	}

	@GetMapping("/status")
	public List<CabBookingStatus> getCabs() {
		return bookingService.findAllCabBookingStatus();
	}

	private BookingDTO mapToBookingDTO(BookingRequest bookingRequest) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.addMappings(bookingMapper.bookingReqToDTOMapping());
		return modelMapper.map(bookingRequest, BookingDTO.class);
	}

	private BookingRest mapToBookingDTO(BookingDTO bookedCar) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.addMappings(bookingMapper.bookingDTOtoRestMapping());
		return modelMapper.map(bookedCar, BookingRest.class);
	}

}
