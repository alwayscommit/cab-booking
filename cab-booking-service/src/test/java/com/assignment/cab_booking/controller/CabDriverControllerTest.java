package com.assignment.cab_booking.controller;

import static org.mockito.Mockito.mock;
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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.assignment.cab_booking.mapper.CabDriverMapper;
import com.assignment.cab_booking.model.AccountType;
import com.assignment.cab_booking.model.CarStatus;
import com.assignment.cab_booking.model.dto.CabDriverDTO;
import com.assignment.cab_booking.model.request.CabDriverRequest;
import com.assignment.cab_booking.model.response.CabDriverRest;
import com.assignment.cab_booking.service.CabDriverService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SuppressWarnings("unchecked")
@WebMvcTest(CabDriverController.class)
class CabDriverControllerTest {

	private static final String CAB_DRIVER_CONTROLLER_MAPPING = "/cab-driver";

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private CabDriverService driverService;

	@MockBean
	private CabDriverMapper cabDriverMapper;

	@InjectMocks
	private CabDriverController cabDriverController;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testCreateDriverMock() throws Exception {
		CabDriverRequest driverRequest = new CabDriverRequest("Aakash", "Audi", "MH04JP0222", "7506500444", "19.231309",
				"72.982752");

		Mockito.when(cabDriverMapper.mapToRest(Mockito.any()))
				.thenReturn(carDriverTestData("Audi", "Aakash", "7506500444", "MH04JP0222"));
		Mockito.when(cabDriverMapper.mapToDTO(Mockito.any(CabDriverRequest.class)))
				.thenReturn(mock(CabDriverDTO.class));
		Mockito.when(driverService.registerDriver(Mockito.any())).thenReturn(mock(CabDriverDTO.class));

		this.mockMvc.perform(post(CAB_DRIVER_CONTROLLER_MAPPING).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(driverRequest))).andExpect(status().isCreated());
		verify(driverService, times(1)).registerDriver(Mockito.any());
		verify(cabDriverMapper, times(1)).mapToRest(Mockito.any());
		verify(cabDriverMapper, times(1)).mapToDTO(Mockito.any(CabDriverRequest.class));
	}

	@Test
	public void testCreateDriver() throws Exception {
		CabDriverDTO driverDTO = new CabDriverDTO("7506500444", "Aakash", "Ranglani", "aakash", AccountType.DRIVER,
				"Audi", CarStatus.AVAILABLE, "MH04MB22222", 19.231309, 72.982752, new Date());

		Mockito.when(cabDriverMapper.mapToDTO(Mockito.any(CabDriverRequest.class)))
				.thenReturn(mock(CabDriverDTO.class));
		Mockito.when(cabDriverMapper.mapToRest(Mockito.any()))
				.thenReturn(carDriverTestData("Audi", "Aakash", "7506500444", "MH04MB22222"));
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

		List<CabDriverDTO> driverList = mock(List.class);
		List<CabDriverRest> driverResponseList = new ArrayList<CabDriverRest>();
		driverResponseList.add(carDriverTestData("Audi", "Aakash", "7506500444", "MH04MB2222"));
		driverResponseList.add(carDriverTestData("Audi1", "Aakash1", "7506500441", "MH04MB2111"));

		Mockito.when(cabDriverMapper.mapToResponseList(Mockito.anyList())).thenReturn(driverResponseList);
		Mockito.when(driverService.getNearByAvailableCabDrivers(Mockito.any())).thenReturn(driverList);

		this.mockMvc.perform(get(CAB_DRIVER_CONTROLLER_MAPPING + "/near?latitude=19.23096967&longitude=72.9824377"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.[0].carName", CoreMatchers.is(driverResponseList.get(0).getCarName())))
				.andExpect(jsonPath("$.[0].firstName", CoreMatchers.is(driverResponseList.get(0).getFirstName())))
				.andExpect(jsonPath("$.[0].carNumber", CoreMatchers.is(driverResponseList.get(0).getCarNumber())))
				.andExpect(jsonPath("$.[1].carName", CoreMatchers.is(driverResponseList.get(1).getCarName())))
				.andExpect(jsonPath("$.[1].firstName", CoreMatchers.is(driverResponseList.get(1).getFirstName())))
				.andExpect(jsonPath("$.[1].carNumber", CoreMatchers.is(driverResponseList.get(1).getCarNumber())));

		verify(driverService, times(1)).getNearByAvailableCabDrivers(Mockito.any());
		verify(cabDriverMapper, times(1)).mapToResponseList(Mockito.any());
	}

	@Test
	public void testGetDriver() throws Exception {

		CabDriverRest actualCabDriver = carDriverTestData("Audi1", "Aakash1", "7506500441", "MH04MB2111");

		Mockito.when(driverService.getDriver(Mockito.anyString())).thenReturn(mock(CabDriverDTO.class));
		Mockito.when(cabDriverMapper.mapToDriverResponse(Mockito.any())).thenReturn(actualCabDriver);

		this.mockMvc.perform(get(CAB_DRIVER_CONTROLLER_MAPPING + "/123")).andExpect(status().isOk())
				.andExpect(jsonPath("$.firstName", CoreMatchers.is(actualCabDriver.getFirstName())))
				.andExpect(jsonPath("$.mobileNumber", CoreMatchers.is(actualCabDriver.getMobileNumber())))
				.andExpect(jsonPath("$.carNumber", CoreMatchers.is(actualCabDriver.getCarNumber())));

		verify(driverService, times(1)).getDriver(Mockito.anyString());
		verify(cabDriverMapper, times(1)).mapToDriverResponse(Mockito.any());
	}

	private CabDriverRest carDriverTestData(String carName, String firstName, String mobileNumber, String carNumber) {
		CabDriverRest carDriver = new CabDriverRest();
		carDriver.setAccountType(AccountType.CUSTOMER.toString());
		carDriver.setCarName(carName);
		carDriver.setCarNumber(carNumber);
		carDriver.setFirstName(firstName);
		carDriver.setMobileNumber(mobileNumber);
		return carDriver;
	}

}
