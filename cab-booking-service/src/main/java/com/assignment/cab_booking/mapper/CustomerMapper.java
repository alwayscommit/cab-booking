package com.assignment.cab_booking.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.assignment.cab_booking.entity.UserAccountEntity;
import com.assignment.cab_booking.model.dto.CustomerDTO;
import com.assignment.cab_booking.model.request.CustomerRequest;
import com.assignment.cab_booking.model.response.CustomerRest;

@Component
public class CustomerMapper {

	@Autowired
	private ModelMapper modelMapper;

	public CustomerDTO mapToCustomerDTO(CustomerRequest customerRequest) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
		return modelMapper.map(customerRequest, CustomerDTO.class);
	}

	public CustomerRest mapToCustomerResposne(CustomerDTO savedCustomer) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
		return modelMapper.map(savedCustomer, CustomerRest.class);
	}
	
	public CustomerDTO mapToDTO(UserAccountEntity savedCustomer) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
		return modelMapper.map(savedCustomer, CustomerDTO.class);
	}

	public UserAccountEntity mapToEntity(CustomerDTO customerDTO) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
		return modelMapper.map(customerDTO, UserAccountEntity.class);
	}

}
