package com.assignment.cab_booking.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.assignment.cab_booking.dto.DriverDTO;
import com.assignment.cab_booking.entity.CarStatus;
import com.assignment.cab_booking.service.DriverService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(DriverController.class)
class DriverControllerTest {

	private static final String DRIVER_CONTROLLER_MAPPING = "/driver";

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private DriverService driverService;

	@Test
	public void testCreateDriver() throws Exception {
		DriverDTO driverDTO = new DriverDTO("Aakash", "Audi", CarStatus.AVAILABLE, "MH04JP0222", "7506500444",
				"19.231309", "72.982752");
		Mockito.when(driverService.registerDriver(Mockito.any())).thenReturn(driverDTO);
		this.mockMvc.perform(post(DRIVER_CONTROLLER_MAPPING).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(driverDTO))).andExpect(status().isOk());
		Mockito.verify(driverService, times(1)).registerDriver(Mockito.any());
	}

	@Test
	public void testCreate() throws Exception {
		DriverDTO driverDTO = new DriverDTO("Aakash", "Audi", CarStatus.AVAILABLE, "MH04JP0222", "7506500444",
				"19.231309", "72.982752");

		Mockito.when(driverService.registerDriver(Mockito.any())).thenReturn(driverDTO);

		this.mockMvc
				.perform(post(DRIVER_CONTROLLER_MAPPING).contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(driverDTO)))
				.andExpect(status().isOk()).andExpect(jsonPath("$.driverName", is(driverDTO.getDriverName())))
				.andExpect(jsonPath("$.driverNumber", is(driverDTO.getDriverNumber())))
				.andExpect(jsonPath("$.carNumber", is(driverDTO.getCarNumber())));

	}

}
