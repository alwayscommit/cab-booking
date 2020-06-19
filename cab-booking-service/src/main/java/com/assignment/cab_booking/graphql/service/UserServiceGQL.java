package com.assignment.cab_booking.graphql.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.cab_booking.entity.UserAccountEntity;
import com.assignment.cab_booking.model.AccountType;
import com.assignment.cab_booking.model.dto.UserDTO;
import com.assignment.cab_booking.service.UserService;

@Service
public class UserServiceGQL {
	
	@Autowired
	private UserService userService;
	
	public UserDTO createUser(String mobileNumber, String encryptedPassword, String firstName, String lastName) {
		UserDTO userAccount = new UserDTO();
		userAccount.setFirstName(firstName);
		userAccount.setLastName(lastName);
		userAccount.setPassword(encryptedPassword);
		userAccount.setMobileNumber(mobileNumber);
		userAccount.setAccountType(AccountType.CUSTOMER);
		return userService.registerCustomer(userAccount);
	}
	
	public UserDTO getUser(String mobileNumber) {
		return userService.getUser(mobileNumber);
	}

}
