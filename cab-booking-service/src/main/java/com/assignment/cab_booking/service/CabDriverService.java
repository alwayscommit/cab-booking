package com.assignment.cab_booking.service;

import java.util.List;

import com.assignment.cab_booking.model.dto.CabDriverDTO;
import com.assignment.cab_booking.model.dto.LocationDTO;
import com.assignment.cab_booking.model.request.LocationRequest;

public interface CabDriverService {
	
	public CabDriverDTO registerDriver(CabDriverDTO driverDTO);

	public CabDriverDTO getDriver(String id);

	CabDriverDTO updateDriverCarLocation(String driverNumber, LocationDTO locationDto);

	public List<CabDriverDTO> getNearByAvailableCabDrivers(LocationDTO locationDTO); 

}
