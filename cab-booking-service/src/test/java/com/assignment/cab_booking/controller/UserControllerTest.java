package com.assignment.cab_booking.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.assignment.cab_booking.mapper.UserMapper;
import com.assignment.cab_booking.model.AccountType;
import com.assignment.cab_booking.model.dto.UserDTO;
import com.assignment.cab_booking.model.request.UserRequest;
import com.assignment.cab_booking.model.response.UserRest;
import com.assignment.cab_booking.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
class UserControllerTest {

	private static final String CUSTOMER_CONTROLLER_MAPPING = "/user";

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private UserMapper customerMapper;

	@MockBean
	private UserService customerService;
	
	@InjectMocks
	private UserControllerTest customerControllerTest;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testCreateDriverMock() throws Exception {
		Mockito.when(customerService.registerCustomer(Mockito.any())).thenReturn(mock(UserDTO.class));
		Mockito.when(customerMapper.mapToCustomerDTO(Mockito.any())).thenReturn(mock(UserDTO.class));
		Mockito.when(customerMapper.mapToCustomerResposne(Mockito.any())).thenReturn(customerRestData());

		this.mockMvc.perform(post(CUSTOMER_CONTROLLER_MAPPING+"/customer").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(customerRequestTestData()))).andExpect(status().isCreated());
		
		verify(customerService, times(1)).registerCustomer(Mockito.any());
		verify(customerMapper, times(1)).mapToCustomerDTO(Mockito.any());
		verify(customerMapper, times(1)).mapToCustomerResposne(Mockito.any());
	}

	@Test
	public void testCreateDriver() throws Exception {
		UserDTO customerDto = new UserDTO("aasscc22aa", "7506500591", "Aakash", "Ranglani", "aakash",
				AccountType.CUSTOMER, 19.231309, 72.982752, new Date());

		Mockito.when(customerService.registerCustomer(Mockito.any())).thenReturn(customerDto);
		
		Mockito.when(customerMapper.mapToCustomerDTO(Mockito.any())).thenReturn(mock(UserDTO.class));
		Mockito.when(customerMapper.mapToCustomerResposne(Mockito.any())).thenReturn(customerRestData());

		this.mockMvc
				.perform(post(CUSTOMER_CONTROLLER_MAPPING+"/customer").contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(customerDto)))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.firstName", CoreMatchers.is(customerDto.getFirstName())))
				.andExpect(jsonPath("$.mobileNumber", CoreMatchers.is(customerDto.getMobileNumber())))
				.andExpect(jsonPath("$.lastName", CoreMatchers.is(customerDto.getLastName())));
	}

	private UserRest customerRestData() {
		UserRest customerRest = new UserRest();
		customerRest.setAccountType(AccountType.DRIVER.toString());
		customerRest.setFirstName("Aakash");
		customerRest.setLastName("Ranglani");
		customerRest.setMobileNumber("7506500591");
		return customerRest;
	}
	
	private UserRequest customerRequestTestData() {
		UserRequest customerRequest = new UserRequest();
		customerRequest.setFirstName("Aakash");
		customerRequest.setLastName("Ranglani");
		customerRequest.setMobileNumber("7506500591");
		customerRequest.setPassword("Password");
		return customerRequest;
	}

	
}
