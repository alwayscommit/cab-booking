package com.assignment.cab_booking.controller;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.assignment.cab_booking.service.BookingService;

@WebMvcTest(BookingController.class)
class BookingControllerTest {

	private static final String CUSTOMER_CONTROLLER_MAPPING = "/booking/hello";

	@MockBean
	private BookingService bookingService;

	// TODO

}
