package com.assignment.cab_booking.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.assignment.cab_booking.entity.UserAccountEntity;
import com.assignment.cab_booking.mapper.CustomerMapper;
import com.assignment.cab_booking.model.AccountType;
import com.assignment.cab_booking.model.dto.CustomerDTO;
import com.assignment.cab_booking.repository.UserAccountRepository;
import com.assignment.cab_booking.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	private UserAccountRepository userAccountRepo;

	private BCryptPasswordEncoder passwordEncoder;

	private CustomerMapper customerMapper;

	@Autowired
	public CustomerServiceImpl(UserAccountRepository userAccountRepo, BCryptPasswordEncoder passwordEncoder,
			CustomerMapper customerMapper) {
		this.userAccountRepo = userAccountRepo;
		this.passwordEncoder = passwordEncoder;
		this.customerMapper = customerMapper;
	}

	@Override
	public CustomerDTO registerCustomer(CustomerDTO customerDTO) {

		UserAccountEntity customerAccount = customerMapper.mapToEntity(customerDTO);

		setCustomerDetails(customerAccount, customerDTO);

		UserAccountEntity savedCustomer = userAccountRepo.save(customerAccount);
		return customerMapper.mapToDTO(savedCustomer);
	}

	private void setCustomerDetails(UserAccountEntity customerAccount, CustomerDTO customerDTO) {
		customerAccount.setEncryptedPassword(passwordEncoder.encode(customerDTO.getPassword()));
		customerAccount.setAccountType(AccountType.CUSTOMER.toString());
		customerAccount.setCreatedOn(new Date());
	}

}
