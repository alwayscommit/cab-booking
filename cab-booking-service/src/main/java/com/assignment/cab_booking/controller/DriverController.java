package com.assignment.cab_booking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.cab_booking.dto.DriverDTO;
import com.assignment.cab_booking.service.DriverService;

@RestController
@RequestMapping("/driver")
public class DriverController {

	private DriverService driverService;

	@Autowired
	public DriverController(DriverService driverService) {
		this.driverService = driverService;
	}

	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<DriverDTO> registerDriver(DriverDTO driverDTO) {
		DriverDTO savedDriver = driverService.registerDriver(driverDTO);
		return new ResponseEntity<DriverDTO>(savedDriver, HttpStatus.CREATED);
	}

	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<DriverDTO>> getAllDrivers() {
		List<DriverDTO> driverList = driverService.getDrivers();
		return new ResponseEntity<List<DriverDTO>>(driverList, HttpStatus.OK);
	}
	
	@GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<DriverDTO> getDriver(@PathVariable String id) {
		DriverDTO driver = driverService.getDriver(id);
		return new ResponseEntity<DriverDTO>(driver, HttpStatus.OK);
	}
	
}
