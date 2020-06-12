package com.assignment.cab_booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.cab_booking.dto.DriverDTO;
import com.assignment.cab_booking.service.DriverService;

@RestController
@RequestMapping("/cab-service")
public class DriverRegistrationController {

	private DriverService driverService;

	@Autowired
	public DriverRegistrationController(DriverService driverService) {
		this.driverService = driverService;
	}

	@PostMapping(value = "/driver", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<DriverDTO> registerDriver(DriverDTO driverDTO) {
		DriverDTO savedDriver = driverService.registerDriver(driverDTO);
		return new ResponseEntity<DriverDTO>(savedDriver, HttpStatus.OK);
	}

}
