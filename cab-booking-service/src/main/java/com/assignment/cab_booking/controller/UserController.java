package com.assignment.cab_booking.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.cab_booking.mapper.UserMapper;
import com.assignment.cab_booking.model.dto.UserDTO;
import com.assignment.cab_booking.model.request.UserRequest;
import com.assignment.cab_booking.model.response.UserRest;
import com.assignment.cab_booking.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	private UserService customerService;

	@Autowired
	private UserMapper customerMapper;

	@Autowired
	public UserController(UserService customerService, UserMapper customerMapper) {
		this.customerService = customerService;
		this.customerMapper = customerMapper;
	}

	@PostMapping(value="/customer", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserRest> registerCustomer(@Valid @RequestBody UserRequest customerRequest) {
		LOGGER.info(String.format("Creating Customer with Mobile Number :: %s", customerRequest.getMobileNumber()));
		UserDTO customerDTO = customerMapper.mapToCustomerDTO(customerRequest);

		UserDTO savedCustomer = customerService.registerCustomer(customerDTO);

		UserRest customerResponse = customerMapper.mapToCustomerResposne(savedCustomer);
		return new ResponseEntity<UserRest>(customerResponse, HttpStatus.CREATED);
	}

}
