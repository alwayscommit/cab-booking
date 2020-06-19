package com.assignment.cab_booking.graphql.resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.assignment.cab_booking.graphql.service.UserServiceGQL;
import com.assignment.cab_booking.model.dto.UserDTO;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;

@Component
public class UserMutation implements GraphQLMutationResolver {
	
	@Autowired
	private UserServiceGQL userService;
	
	public UserDTO createUser(String mobileNumber, String encryptedPassword, String firstName, String lastName) {
		return this.userService.createUser(mobileNumber, encryptedPassword, firstName, lastName);
	}

}
