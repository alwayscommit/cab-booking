package com.assignment.cab_booking.service;

import java.util.List;

import com.assignment.cab_booking.model.dto.CarDriverDTO;
import com.assignment.cab_booking.model.dto.LocationDTO;

public interface DriverService {
	
	public CarDriverDTO registerDriver(CarDriverDTO driverDTO);

	public List<CarDriverDTO> getAvailableDrivers();

	public CarDriverDTO getDriver(String id);

	public CarDriverDTO updateDriverCarLocation(String driverNumber, LocationDTO locationDto); 

}
