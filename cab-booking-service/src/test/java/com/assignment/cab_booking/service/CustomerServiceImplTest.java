package com.assignment.cab_booking.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.assignment.cab_booking.entity.UserAccountEntity;
import com.assignment.cab_booking.model.AccountType;
import com.assignment.cab_booking.model.dto.CustomerDTO;
import com.assignment.cab_booking.repository.UserAccountRepository;
import com.assignment.cab_booking.service.impl.CustomerServiceImpl;

class CustomerServiceImplTest {

	@Mock
	private UserAccountRepository userAccountRepo;

	@Mock
	private BCryptPasswordEncoder passwordEncoder;

	@InjectMocks
	private CustomerServiceImpl customerServiceImpl;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}


	@Test
	public void testMockregisterCustomer() {
		CustomerDTO registerCustomerDTO = new CustomerDTO();
		registerCustomerDTO.setFirstName("Aakash666");
		registerCustomerDTO.setLastName("Ranglani666");
		registerCustomerDTO.setMobileNumber("7516504514");
		registerCustomerDTO.setPassword("aakash12");

		UserAccountEntity userAccount = new UserAccountEntity();
		userAccount.setUserId(321L);
		userAccount.setAccountType(AccountType.CUSTOMER.toString());
		userAccount.setCreatedOn(new Date());
		userAccount.setFirstName("Aakash666");
		userAccount.setLastName("Ranglani666");
		userAccount.setMobileNumber("7516504514");

		Mockito.when(passwordEncoder.encode(anyString())).thenReturn("Encrypted Password");
		Mockito.when(userAccountRepo.save(Mockito.any())).thenReturn(userAccount);

		customerServiceImpl.registerCustomer(registerCustomerDTO);

		verify(passwordEncoder, times(1)).encode(Mockito.anyString());
		verify(userAccountRepo, times(1)).save(Mockito.any());
	}

	@Test
	public void testRegisterCustomer() {
		CustomerDTO expectedCustomerDTO = new CustomerDTO();
		expectedCustomerDTO.setFirstName("Aakash666");
		expectedCustomerDTO.setLastName("Ranglani666");
		expectedCustomerDTO.setMobileNumber("7516504514");
		expectedCustomerDTO.setPassword("aakash12");

		UserAccountEntity userAccount = new UserAccountEntity();
		userAccount.setUserId(321L);
		userAccount.setAccountType(AccountType.CUSTOMER.toString());
		userAccount.setCreatedOn(new Date());
		userAccount.setFirstName("Aakash666");
		userAccount.setLastName("Ranglani666");
		userAccount.setMobileNumber("7516504514");

		Mockito.when(passwordEncoder.encode(anyString())).thenReturn("Encrypted Password");
		Mockito.when(userAccountRepo.save(Mockito.any())).thenReturn(userAccount);

		CustomerDTO actualCustomerDTO = customerServiceImpl.registerCustomer(expectedCustomerDTO);
		
		assertEquals(AccountType.CUSTOMER, actualCustomerDTO.getAccountType());
		assertEquals(userAccount.getUserId().longValue(), actualCustomerDTO.getUserId().longValue());
		assertEquals(expectedCustomerDTO.getFirstName(), actualCustomerDTO.getFirstName());
		assertEquals(expectedCustomerDTO.getLastName(), actualCustomerDTO.getLastName());
	}

}
