package com.assignment.cab_booking.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.cab_booking.entity.BookingEntity;
import com.assignment.cab_booking.entity.CarEntity;
import com.assignment.cab_booking.mapper.BookingMapper;
import com.assignment.cab_booking.model.dto.BookingDTO;
import com.assignment.cab_booking.model.request.BookingRequest;
import com.assignment.cab_booking.model.response.BookingRest;
import com.assignment.cab_booking.service.BookingService;
import com.assignment.cab_booking.view.CabStatus;

@RestController
@RequestMapping("/booking")
public class BookingController {

	private BookingService bookingService;

	private BookingMapper bookingMapper;

	@Autowired
	public BookingController(BookingService bookingService, BookingMapper bookingMapper) {
		this.bookingService = bookingService;
		this.bookingMapper = bookingMapper;
	}

	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BookingRest> registerDriver(@RequestBody BookingRequest bookingRequest) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.addMappings(bookingMapper.bookingReqToDTOMapping());
		BookingDTO bookingDTO = modelMapper.map(bookingRequest, BookingDTO.class);

		BookingDTO bookedCar = bookingService.bookCab(bookingDTO);

		modelMapper.addMappings(bookingMapper.bookingDTOtoRestMapping());
		BookingRest bookingResponse = modelMapper.map(bookedCar, BookingRest.class);
		return new ResponseEntity<BookingRest>(bookingResponse, HttpStatus.CREATED);
	}

	@GetMapping("/cabs")
	public List<CabStatus> getCabs() {
		return bookingService.findAllCabs();
	}

}
