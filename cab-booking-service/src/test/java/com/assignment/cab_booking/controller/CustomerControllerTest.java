package com.assignment.cab_booking.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.assignment.cab_booking.model.AccountType;
import com.assignment.cab_booking.model.dto.CustomerDTO;
import com.assignment.cab_booking.model.request.CabDriverRequest;
import com.assignment.cab_booking.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

	private static final String CUSTOMER_CONTROLLER_MAPPING = "/customer";

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private CustomerService customerService;

	@Test
	public void testCreateDriverMock() throws Exception {
		CabDriverRequest driverRequest = new CabDriverRequest("Aakash", "Audi", "MH04JP0222", "7506500444", "19.231309",
				"72.982752");

		CustomerDTO customerDTO = new CustomerDTO(1L, "7506500444", "Aakash", "Ranglani", "aakash",
				AccountType.CUSTOMER, 19.231309, 72.982752, new Date());

		Mockito.when(customerService.registerCustomer(Mockito.any())).thenReturn(customerDTO);
		this.mockMvc.perform(post(CUSTOMER_CONTROLLER_MAPPING).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(driverRequest))).andExpect(status().isCreated());
		verify(customerService, times(1)).registerCustomer(Mockito.any());
	}

	@Test
	public void testCreateDriver() throws Exception {
		CustomerDTO customerDto = new CustomerDTO(1L, "7506500444", "Aakash", "Ranglani", "aakash",
				AccountType.CUSTOMER, 19.231309, 72.982752, new Date());

		Mockito.when(customerService.registerCustomer(Mockito.any())).thenReturn(customerDto);

		this.mockMvc
				.perform(post(CUSTOMER_CONTROLLER_MAPPING).contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(customerDto)))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.firstName", CoreMatchers.is(customerDto.getFirstName())))
				.andExpect(jsonPath("$.mobileNumber", CoreMatchers.is(customerDto.getMobileNumber())))
				.andExpect(jsonPath("$.lastName", CoreMatchers.is(customerDto.getLastName())));
	}

}
