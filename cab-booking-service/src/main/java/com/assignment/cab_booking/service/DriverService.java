package com.assignment.cab_booking.service;

import java.util.List;

import com.assignment.cab_booking.dto.DriverDTO;

public interface DriverService {
	
	public DriverDTO registerDriver(DriverDTO driver);

	public List<DriverDTO> getDrivers();

	public DriverDTO getDriver(String id); 

}
