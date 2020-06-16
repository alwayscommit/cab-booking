package com.assignment.cab_booking.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
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

	@Autowired
	public CabDriverController(CabDriverService driverService) {
		this.driverService = driverService;
	}

	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CabDriverRest> registerCabDriver(@RequestBody CabDriverRequest driverRequest) {
		LOGGER.info(String.format("Creating Cab Driver with Mobile Number :: %s", driverRequest.getMobileNumber()));

		CabDriverDTO driverDTO = mapToCabDriverDTO(driverRequest);

		CabDriverDTO savedCabDetails = driverService.registerDriver(driverDTO);

		CabDriverRest cabResponse = mapToCabDriverRest(savedCabDetails);

		return new ResponseEntity<CabDriverRest>(cabResponse, HttpStatus.CREATED);
	}

	@GetMapping(value = "/near", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<CabDriverRest>> getNearByAvailableCabDrivers(@RequestParam Double latitude,
			@RequestParam Double longitude) {
		LOGGER.info(String.format("Retrieving nearby available cab drivers :: %s, %s", latitude, longitude));

		List<CabDriverDTO> cabDriverList = driverService
				.getNearByAvailableCabDrivers(new LocationDTO(latitude, longitude));

		List<CabDriverRest> availableCabDrivers = mapToCabDriverResponseList(cabDriverList);

		return new ResponseEntity<List<CabDriverRest>>(availableCabDrivers, HttpStatus.OK);
	}

	@GetMapping(path = "/{cabDriverNumber}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CabDriverRest> getDriver(@PathVariable String cabDriverNumber) {
		LOGGER.info(String.format("Retrieving cab driver details ::%s", cabDriverNumber));
		CabDriverDTO cab = driverService.getDriver(cabDriverNumber);

		if (cab == null) {
			return new ResponseEntity<CabDriverRest>(HttpStatus.NO_CONTENT);
		}

		CabDriverRest cabResponse = mapToDriverResponse(cab);
		return new ResponseEntity<CabDriverRest>(cabResponse, HttpStatus.OK);
	}

	@PutMapping(path = "/{cabDriverNumber}/location", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CabDriverRest> updateDriverCarLocation(@PathVariable String cabDriverNumber,
			@RequestBody LocationRequest locationRequest) {
		LOGGER.info(String.format("Updating cab driver location ::%s", cabDriverNumber));
		LocationDTO locationDto = mapToLocationDTO(locationRequest);

		CabDriverDTO driver = driverService.updateDriverCarLocation(cabDriverNumber, locationDto);

		CabDriverRest driverResponse = mapToDriverResponse(driver);
		return new ResponseEntity<CabDriverRest>(driverResponse, HttpStatus.OK);
	}

	private LocationDTO mapToLocationDTO(LocationRequest locationRequest) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(locationRequest, LocationDTO.class);
	}

	private CabDriverRest mapToDriverResponse(CabDriverDTO driverDTO) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(driverDTO, CabDriverRest.class);
	}

	private List<CabDriverRest> mapToCabDriverResponseList(List<CabDriverDTO> cabDriverList) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		return modelMapper.map(cabDriverList, new TypeToken<List<CabDriverRest>>() {
		}.getType());
	}

	private CabDriverDTO mapToCabDriverDTO(CabDriverRequest driverRequest) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(driverRequest, CabDriverDTO.class);
	}

	private CabDriverRest mapToCabDriverRest(CabDriverDTO savedCabDetails) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(savedCabDetails, CabDriverRest.class);
	}
}
