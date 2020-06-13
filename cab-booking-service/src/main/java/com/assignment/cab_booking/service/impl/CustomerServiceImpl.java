package com.assignment.cab_booking.service.impl;

import java.sql.Date;
import java.time.Instant;

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
		ModelMapper modelMapper = new ModelMapper();
		UserAccountEntity customerAccount = modelMapper.map(customerDTO, UserAccountEntity.class);
		customerAccount.setEncryptedPassword(passwordEncoder.encode(customerDTO.getPassword()));
		customerAccount.setAccountType(AccountType.CUSTOMER);
		customerAccount.setCreatedOn(Date.from(Instant.now()));
		UserAccountEntity savedCustomer = userAccountRepo.save(customerAccount);
		CustomerDTO customerDto = modelMapper.map(savedCustomer, CustomerDTO.class);
		return customerDto;
	}

}
