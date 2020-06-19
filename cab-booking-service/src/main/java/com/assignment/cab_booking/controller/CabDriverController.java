package com.assignment.cab_booking.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.cab_booking.mapper.CabDriverMapper;
import com.assignment.cab_booking.model.dto.CabDriverDTO;
import com.assignment.cab_booking.model.dto.LocationDTO;
import com.assignment.cab_booking.model.request.CabDriverRequest;
import com.assignment.cab_booking.model.request.LocationRequest;
import com.assignment.cab_booking.model.response.CabDriverRest;
import com.assignment.cab_booking.service.CabDriverService;

@RestController
@RequestMapping("/cab-driver")
public class CabDriverController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CabDriverController.class);

	private CabDriverService driverService;

	private CabDriverMapper cabDriverMapper;

	@Autowired
	public CabDriverController(CabDriverService driverService, CabDriverMapper cabDriverMapper) {
		this.driverService = driverService;
		this.cabDriverMapper = cabDriverMapper;
	}

	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CabDriverRest> registerCabDriver(@Valid @RequestBody CabDriverRequest driverRequest) {
		LOGGER.info(String.format("Creating Cab Driver with Mobile Number :: %s", driverRequest.getMobileNumber()));

		CabDriverDTO driverDTO = cabDriverMapper.mapToDTO(driverRequest);

		CabDriverDTO savedCabDetails = driverService.registerDriver(driverDTO);

		CabDriverRest cabResponse = cabDriverMapper.mapToRest(savedCabDetails);

		return new ResponseEntity<CabDriverRest>(cabResponse, HttpStatus.CREATED);
	}

	@GetMapping(value = "/near", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<CabDriverRest>> getNearByAvailableCabDrivers(@RequestParam Double latitude,
			@RequestParam Double longitude) {
		LOGGER.info(String.format("Retrieving nearby available cab drivers :: %s, %s", latitude, longitude));

		List<CabDriverDTO> cabDriverList = driverService
				.getNearByAvailableCabDrivers(new LocationDTO(latitude, longitude));

		List<CabDriverRest> availableCabDrivers = cabDriverMapper.mapToResponseList(cabDriverList);

		return new ResponseEntity<List<CabDriverRest>>(availableCabDrivers, HttpStatus.OK);
	}

	@PutMapping(path = "/{driverId}/location", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CabDriverRest> updateDriverCarLocation(@PathVariable String driverId,
			@Valid @RequestBody LocationRequest locationRequest) {
		LOGGER.info(String.format("Updating cab driver location ::%s", driverId));
		LocationDTO locationDto = cabDriverMapper.mapToLocationDTO(locationRequest);

		CabDriverDTO driver = driverService.updateDriverCarLocation(driverId, locationDto);

		CabDriverRest driverResponse = cabDriverMapper.mapToDriverResponse(driver);
		return new ResponseEntity<CabDriverRest>(driverResponse, HttpStatus.OK);
	}

	@GetMapping(path = "/{driverId}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CabDriverRest> getDriver(@PathVariable String driverId) {
		LOGGER.info(String.format("Retrieving cab driver details driverId::%s", driverId));
		CabDriverDTO cab = driverService.getDriver(driverId);

		if (cab == null) {
			return new ResponseEntity<CabDriverRest>(HttpStatus.NO_CONTENT);
		}

		CabDriverRest cabResponse = cabDriverMapper.mapToDriverResponse(cab);
		return new ResponseEntity<CabDriverRest>(cabResponse, HttpStatus.OK);
	}
}
