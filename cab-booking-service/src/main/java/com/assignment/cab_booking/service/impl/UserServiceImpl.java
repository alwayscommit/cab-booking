package com.assignment.cab_booking.service.impl;

import java.util.ArrayList;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.assignment.cab_booking.constants.ApplicationConstants;
import com.assignment.cab_booking.constants.ExceptionConstants;
import com.assignment.cab_booking.entity.UserAccountEntity;
import com.assignment.cab_booking.mapper.UserMapper;
import com.assignment.cab_booking.model.AccountType;
import com.assignment.cab_booking.model.dto.UserDTO;
import com.assignment.cab_booking.repository.UserAccountRepository;
import com.assignment.cab_booking.service.UserService;
import com.assignment.cab_booking.utils.ApplicationUtils;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	private UserAccountRepository userAccountRepo;

	private BCryptPasswordEncoder passwordEncoder;

	private UserMapper userMapper;
	
	private ApplicationUtils utils;

	@Autowired
	public UserServiceImpl(UserAccountRepository userAccountRepo, BCryptPasswordEncoder passwordEncoder,
			UserMapper customerMapper, ApplicationUtils utils) {
		this.userAccountRepo = userAccountRepo;
		this.passwordEncoder = passwordEncoder;
		this.userMapper = customerMapper;
		this.utils = utils;
	}

	@Override
	public UserDTO registerCustomer(UserDTO customerDTO) {

		UserAccountEntity customerAccount = userMapper.mapToEntity(customerDTO);

		setCustomerDetails(customerAccount, customerDTO);

		UserAccountEntity savedCustomer = userAccountRepo.save(customerAccount);
		return userMapper.mapToDTO(savedCustomer);
	}

	private void setCustomerDetails(UserAccountEntity customerAccount, UserDTO customerDTO) {
		customerAccount.setEncryptedPassword(passwordEncoder.encode(customerDTO.getPassword()));
		customerAccount.setAccountType(AccountType.CUSTOMER);
		customerAccount.setUserId(utils.generateUserId(ApplicationConstants.USER_ID_LENGTH));
		customerAccount.setCreatedOn(new Date());
	}

	@Override
	public UserDetails loadUserByUsername(String mobileNumber) throws UsernameNotFoundException {
		UserAccountEntity userEntity = userAccountRepo.findByMobileNumber(mobileNumber);
		if (userEntity == null) {
			LOGGER.info(String.format("Mobile number %s is not registered...", mobileNumber));
			throw new UsernameNotFoundException(ExceptionConstants.MOBILE_NUMBER_NOT_FOUND_EXCEPTION);
		}
		return new User(userEntity.getMobileNumber(), userEntity.getEncryptedPassword(), new ArrayList<>());
	}

	@Override
	public UserDTO getUser(String mobileNumber) {
		return userMapper.mapToDTO(userAccountRepo.findByMobileNumber(mobileNumber));
	}

}
