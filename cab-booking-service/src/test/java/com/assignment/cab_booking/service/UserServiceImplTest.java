package com.assignment.cab_booking.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.assignment.cab_booking.entity.UserAccountEntity;
import com.assignment.cab_booking.mapper.UserMapper;
import com.assignment.cab_booking.model.AccountType;
import com.assignment.cab_booking.model.dto.UserDTO;
import com.assignment.cab_booking.repository.UserAccountRepository;
import com.assignment.cab_booking.service.impl.UserServiceImpl;
import com.assignment.cab_booking.utils.ApplicationUtils;

class UserServiceImplTest {

	@Mock
	private UserAccountRepository userAccountRepo;

	@Mock
	private BCryptPasswordEncoder passwordEncoder;
	
	@Mock
	private UserMapper customerMapper;
	
	@Mock
	private ApplicationUtils utils;
	
	@InjectMocks
	private UserServiceImpl customerServiceImpl;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testMockregisterCustomer() {
		UserDTO registerCustomerDTO = new UserDTO();
		registerCustomerDTO.setFirstName("Aakash666");
		registerCustomerDTO.setLastName("Ranglani666");
		registerCustomerDTO.setMobileNumber("7516504514");
		registerCustomerDTO.setPassword("aakash12");

		UserAccountEntity userAccount = new UserAccountEntity();
		userAccount.setUserId("acdvda111");
		userAccount.setAccountType(AccountType.CUSTOMER);
		userAccount.setCreatedOn(new Date());
		userAccount.setFirstName("Aakash666");
		userAccount.setLastName("Ranglani666");
		userAccount.setMobileNumber("7516504514");

		Mockito.when(passwordEncoder.encode(anyString())).thenReturn("Encrypted Password");
		Mockito.when(userAccountRepo.save(Mockito.any())).thenReturn(userAccount);

		Mockito.when(customerMapper.mapToEntity(Mockito.any())).thenReturn(userAccount);
		Mockito.when(customerMapper.mapToDTO(Mockito.any())).thenReturn(registerCustomerDTO);
		Mockito.when(utils.generateUserId(10)).thenReturn("acdvda111");
		
		customerServiceImpl.registerCustomer(registerCustomerDTO);

		verify(passwordEncoder, times(1)).encode(Mockito.anyString());
		verify(userAccountRepo, times(1)).save(Mockito.any());
	}

	@Test
	public void testRegisterCustomer() {
		UserDTO expectedCustomerDTO = new UserDTO();
		expectedCustomerDTO.setFirstName("Aakash666");
		expectedCustomerDTO.setLastName("Ranglani666");
		expectedCustomerDTO.setMobileNumber("7516504514");
		expectedCustomerDTO.setPassword("aakash12");
		expectedCustomerDTO.setAccountType(AccountType.CUSTOMER);
		expectedCustomerDTO.setUserId("string1234");

		UserAccountEntity userAccount = new UserAccountEntity();
		userAccount.setUserId("string1234");
		userAccount.setAccountType(AccountType.CUSTOMER);
		userAccount.setCreatedOn(new Date());
		userAccount.setFirstName("Aakash666");
		userAccount.setLastName("Ranglani666");
		userAccount.setMobileNumber("7516504514");

		Mockito.when(passwordEncoder.encode(anyString())).thenReturn("Encrypted Password");
		Mockito.when(userAccountRepo.save(Mockito.any())).thenReturn(userAccount);

		Mockito.when(customerMapper.mapToEntity(Mockito.any())).thenReturn(userAccount);
		Mockito.when(customerMapper.mapToDTO(Mockito.any())).thenReturn(expectedCustomerDTO);
		Mockito.when(utils.generateUserId(10)).thenReturn("string1234");
		
		UserDTO actualCustomerDTO = customerServiceImpl.registerCustomer(expectedCustomerDTO);
		
		assertEquals(AccountType.CUSTOMER, actualCustomerDTO.getAccountType());
		assertEquals(userAccount.getUserId(), actualCustomerDTO.getUserId());
		assertEquals(expectedCustomerDTO.getFirstName(), actualCustomerDTO.getFirstName());
		assertEquals(expectedCustomerDTO.getLastName(), actualCustomerDTO.getLastName());
	}

}
