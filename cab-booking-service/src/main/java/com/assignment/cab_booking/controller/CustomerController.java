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

import com.assignment.cab_booking.mapper.CustomerMapper;
import com.assignment.cab_booking.model.dto.CustomerDTO;
import com.assignment.cab_booking.model.request.CustomerRequest;
import com.assignment.cab_booking.model.response.CustomerRest;
import com.assignment.cab_booking.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

	private CustomerService customerService;

	@Autowired
	private CustomerMapper customerMapper;

	@Autowired
	public CustomerController(CustomerService customerService, CustomerMapper customerMapper) {
		this.customerService = customerService;
		this.customerMapper = customerMapper;
	}

	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CustomerRest> registerDriver(@Valid @RequestBody CustomerRequest customerRequest) {
		LOGGER.info(String.format("Creating Customer with Mobile Number :: %s", customerRequest.getMobileNumber()));
		CustomerDTO customerDTO = customerMapper.mapToCustomerDTO(customerRequest);

		CustomerDTO savedCustomer = customerService.registerCustomer(customerDTO);

		CustomerRest customerResponse = customerMapper.mapToCustomerResposne(savedCustomer);
		return new ResponseEntity<CustomerRest>(customerResponse, HttpStatus.CREATED);
	}

}
