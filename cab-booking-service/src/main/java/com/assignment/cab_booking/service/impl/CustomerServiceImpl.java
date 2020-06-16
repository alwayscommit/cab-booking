package com.assignment.cab_booking.service.impl;

import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.assignment.cab_booking.entity.UserAccountEntity;
import com.assignment.cab_booking.model.AccountType;
import com.assignment.cab_booking.model.dto.CustomerDTO;
import com.assignment.cab_booking.repository.UserAccountRepository;
import com.assignment.cab_booking.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	private UserAccountRepository userAccountRepo;

	private BCryptPasswordEncoder passwordEncoder;

	public CustomerServiceImpl(UserAccountRepository userAccountRepo, BCryptPasswordEncoder passwordEncoder) {
		this.userAccountRepo = userAccountRepo;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public CustomerDTO registerCustomer(CustomerDTO customerDTO) {

		UserAccountEntity customerAccount = mapToEntity(customerDTO);

		setCustomerDetails(customerAccount, customerDTO);

		UserAccountEntity savedCustomer = userAccountRepo.save(customerAccount);
		return mapToDTO(savedCustomer);
	}

	private void setCustomerDetails(UserAccountEntity customerAccount, CustomerDTO customerDTO) {
		customerAccount.setEncryptedPassword(passwordEncoder.encode(customerDTO.getPassword()));
		customerAccount.setAccountType(AccountType.CUSTOMER.toString());
		customerAccount.setCreatedOn(new Date());
	}

	private CustomerDTO mapToDTO(UserAccountEntity savedCustomer) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(savedCustomer, CustomerDTO.class);
	}

	private UserAccountEntity mapToEntity(CustomerDTO customerDTO) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(customerDTO, UserAccountEntity.class);
	}

}
