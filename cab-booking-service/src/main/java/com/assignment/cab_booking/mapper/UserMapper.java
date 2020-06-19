package com.assignment.cab_booking.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.assignment.cab_booking.entity.UserAccountEntity;
import com.assignment.cab_booking.model.dto.UserDTO;
import com.assignment.cab_booking.model.request.UserRequest;
import com.assignment.cab_booking.model.response.UserRest;

@Component
public class UserMapper {

	@Autowired
	private ModelMapper modelMapper;

	public UserDTO mapToCustomerDTO(UserRequest customerRequest) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
		return modelMapper.map(customerRequest, UserDTO.class);
	}

	public UserRest mapToCustomerResposne(UserDTO savedCustomer) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
		return modelMapper.map(savedCustomer, UserRest.class);
	}
	
	public UserDTO mapToDTO(UserAccountEntity userEntity) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
		return modelMapper.map(userEntity, UserDTO.class);
	}

	public UserAccountEntity mapToEntity(UserDTO customerDTO) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
		return modelMapper.map(customerDTO, UserAccountEntity.class);
	}

}
