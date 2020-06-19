package com.assignment.cab_booking.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.assignment.cab_booking.model.dto.UserDTO;

public interface UserService extends UserDetailsService {
	
	public UserDTO registerCustomer(UserDTO customerDTO);

	public UserDTO getUser(String mobileNumber);

}
