package com.assignment.cab_booking.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.assignment.cab_booking.mapper.BookingMapper;
import com.assignment.cab_booking.service.BookingService;

@WebMvcTest(BookingController.class)
class BookingControllerTest {

	private static final String CUSTOMER_CONTROLLER_MAPPING = "/booking/hello";

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private BookingMapper bookingMapper;

	@MockBean
	private BookingService bookingService;

	@Test
	public void testCreateDriver() throws Exception {

		this.mockMvc.perform(get(CUSTOMER_CONTROLLER_MAPPING)).andExpect(status().isOk());
	}

}
