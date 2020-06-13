package com.assignment.cab_booking.service;

import java.util.List;

import com.assignment.cab_booking.model.dto.DriverDTO;

public interface DriverService {
	
	public DriverDTO registerDriver(DriverDTO driverDTO);

	public List<DriverDTO> getDrivers();

	public DriverDTO getDriver(String id); 

}
