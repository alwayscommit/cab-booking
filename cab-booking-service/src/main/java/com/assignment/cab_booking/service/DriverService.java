package com.assignment.cab_booking.service;

import java.util.List;

import com.assignment.cab_booking.model.dto.CarDriverDTO;

public interface DriverService {
	
	public CarDriverDTO registerDriver(CarDriverDTO driverDTO);

	public List<CarDriverDTO> getAvailableDrivers();

	public CarDriverDTO getDriver(String id); 

}
