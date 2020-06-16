package com.assignment.cab_booking.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.assignment.cab_booking.model.AccountType;
import com.assignment.cab_booking.model.CarStatus;
import com.assignment.cab_booking.model.dto.CabDriverDTO;
import com.assignment.cab_booking.model.dto.LocationDTO;
import com.assignment.cab_booking.model.request.CabDriverRequest;
import com.assignment.cab_booking.service.CabDriverService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(CabController.class)
class CabControllerTest {

	private static final String CAB_DRIVER_CONTROLLER_MAPPING = "/cab-driver";

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private CabDriverService driverService;

	@Test
	public void testCreateDriverMock() throws Exception {
		CabDriverRequest driverRequest = new CabDriverRequest("Aakash", "Audi", "MH04JP0222", "7506500444", "19.231309",
				"72.982752");

		CabDriverDTO driverDTO = new CabDriverDTO("7506500444", "Aakash", "Ranglani" ,"aakash", AccountType.DRIVER, "Audi",
				CarStatus.AVAILABLE, "MH04JP0222", 19.231309, 72.982752, new Date());

		Mockito.when(driverService.registerDriver(Mockito.any())).thenReturn(driverDTO);
		this.mockMvc.perform(post(CAB_DRIVER_CONTROLLER_MAPPING).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(driverRequest))).andExpect(status().isCreated());
		verify(driverService, times(1)).registerDriver(Mockito.any());
	}

	@Test
	public void testCreateDriver() throws Exception {
		CabDriverDTO driverDTO = new CabDriverDTO("7506500444", "Aakash", "Ranglani" ,"aakash", AccountType.DRIVER, "Audi",
				CarStatus.AVAILABLE, "MH04JP0222", 19.231309, 72.982752, new Date());

		Mockito.when(driverService.registerDriver(Mockito.any())).thenReturn(driverDTO);

		this.mockMvc
				.perform(post(CAB_DRIVER_CONTROLLER_MAPPING).contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(driverDTO)))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.firstName", CoreMatchers.is(driverDTO.getFirstName())))
				.andExpect(jsonPath("$.mobileNumber", CoreMatchers.is(driverDTO.getMobileNumber())))
				.andExpect(jsonPath("$.carNumber", CoreMatchers.is(driverDTO.getCarNumber())));
	}

	@Test
	public void testGetDrivers() throws Exception {
		
		CabDriverDTO driverDTO1 = new CabDriverDTO("7506500444", "Aakash", "Ranglani" ,"aakash", AccountType.DRIVER, "Audi",
				CarStatus.AVAILABLE, "MH04JP0222", 19.231309, 72.982752, new Date());
		CabDriverDTO driverDTO2 = new CabDriverDTO("7506500444", "Aakash", "Ranglani" ,"aakash", AccountType.DRIVER, "Audi",
				CarStatus.AVAILABLE, "MH04JP0222", 19.231309, 72.982752, new Date());
		CabDriverDTO driverDTO3 = new CabDriverDTO("7506500444", "Aakash", "Ranglani" ,"aakash", AccountType.DRIVER, "Audi",
				CarStatus.AVAILABLE, "MH04JP0222", 19.231309, 72.982752, new Date());

		List<CabDriverDTO> driverList = new ArrayList<CabDriverDTO>();
		driverList.add(driverDTO1);
		driverList.add(driverDTO2);
		driverList.add(driverDTO3);

		Mockito.when(driverService.getNearByAvailableCabDrivers(Mockito.any())).thenReturn(driverList);

		this.mockMvc
				.perform(get(CAB_DRIVER_CONTROLLER_MAPPING+"/near?latitude=19.23096967&longitude=72.9824377"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.[0].carName", CoreMatchers.is(driverDTO1.getCarName())))
				.andExpect(jsonPath("$.[0].firstName", CoreMatchers.is(driverDTO1.getFirstName())))
				.andExpect(jsonPath("$.[0].carNumber", CoreMatchers.is(driverDTO1.getCarNumber())))
				.andExpect(jsonPath("$.[1].carName", CoreMatchers.is(driverDTO2.getCarName())))
				.andExpect(jsonPath("$.[1].lastName", CoreMatchers.is(driverDTO2.getLastName())))
				.andExpect(jsonPath("$.[1].carNumber", CoreMatchers.is(driverDTO2.getCarNumber())));

		verify(driverService, times(1)).getNearByAvailableCabDrivers(Mockito.any());
	}

	@Test
	public void testGetDriver() throws Exception {
		CabDriverDTO driverDTO = new CabDriverDTO("7506500444", "Aakash", "Ranglani" ,"aakash", AccountType.DRIVER, "Audi",
				CarStatus.AVAILABLE, "MH04JP0222", 19.231309, 72.982752, new Date());
		driverDTO.setUserId(123L);
		Mockito.when(driverService.getDriver(Mockito.anyString())).thenReturn(driverDTO);

		this.mockMvc
				.perform(get(CAB_DRIVER_CONTROLLER_MAPPING + "/123"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.firstName", CoreMatchers.is(driverDTO.getFirstName())))
				.andExpect(jsonPath("$.mobileNumber", CoreMatchers.is(driverDTO.getMobileNumber())))
				.andExpect(jsonPath("$.carNumber", CoreMatchers.is(driverDTO.getCarNumber())));

		verify(driverService, times(1)).getDriver(Mockito.anyString());
	}

}
