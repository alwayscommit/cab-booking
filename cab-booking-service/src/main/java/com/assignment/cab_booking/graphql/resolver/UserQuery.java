package com.assignment.cab_booking.graphql.resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.assignment.cab_booking.graphql.service.UserServiceGQL;
import com.assignment.cab_booking.model.dto.UserDTO;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;

@Component
public class UserQuery implements GraphQLQueryResolver {

	@Autowired
	private UserServiceGQL userService;
	
	public UserDTO getUser(String mobileNumber) {
		return this.userService.getUser(mobileNumber);
	}
	
}
