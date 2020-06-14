package com.assignment.cab_booking.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.eq;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.assignment.cab_booking.entity.CarEntity;
import com.assignment.cab_booking.entity.UserAccountEntity;
import com.assignment.cab_booking.model.AccountType;
import com.assignment.cab_booking.model.CarStatus;
import com.assignment.cab_booking.model.dto.CarDriverDTO;
import com.assignment.cab_booking.repository.CarRepository;
import com.assignment.cab_booking.repository.UserAccountRepository;
import com.assignment.cab_booking.service.impl.DriverServiceImpl;

class DriverServiceImplTest {

	@Mock
	private UserAccountRepository userAccountRepo;

	@Mock
	private CarRepository carRepo;

	@Mock
	private BCryptPasswordEncoder passwordEncoder;

	@InjectMocks
	private DriverServiceImpl driverServiceImpl;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@BeforeClass
	public void setup() {
		this.driverServiceImpl = new DriverServiceImpl(userAccountRepo, carRepo, passwordEncoder);
	}

	@Test
	public void testMockRegisterDriver() {
		CarDriverDTO carDriverDTO = new CarDriverDTO("7506500444", "Aakash", "Ranglani", "aakash", AccountType.DRIVER,
				"Audi", CarStatus.AVAILABLE, "MH04JP0222", 19.231309, 72.982752, new Date());

		Mockito.when(passwordEncoder.encode(anyString())).thenReturn("Encrypted Password");
		Mockito.when(carRepo.save(Mockito.any())).thenReturn(new CarEntity());

		driverServiceImpl.registerDriver(carDriverDTO);

		verify(passwordEncoder, times(1)).encode(Mockito.anyString());
		verify(carRepo, times(1)).save(Mockito.any());
	}

	@Test
	public void testRegisterDriver() {
		CarDriverDTO carDriverDTO = new CarDriverDTO();
		carDriverDTO.setCarName("Maruti Swift666");
		carDriverDTO.setFirstName("Aakash666");
		carDriverDTO.setLastName("Ranglani666");
		carDriverDTO.setMobileNumber("7516504514");
		carDriverDTO.setPassword("aakash12");
		carDriverDTO.setCarNumber("MH04MB22222");

		UserAccountEntity userAccount = new UserAccountEntity();
		userAccount.setUserId(321L);
		userAccount.setAccountType(AccountType.DRIVER.toString());
		userAccount.setCreatedOn(new Date());
		userAccount.setFirstName("Aakash666");
		userAccount.setLastName("Ranglani666");
		userAccount.setMobileNumber("7516504514");
		CarEntity carDriver = new CarEntity();
		carDriver.setCarId(123L);
		carDriver.setCarName("Maruti Swift666");
		carDriver.setCarNumber("MH04MB22222");
		carDriver.setCarStatus(CarStatus.AVAILABLE.toString());
		carDriver.setDrivenBy(userAccount);

		Mockito.when(passwordEncoder.encode(anyString())).thenReturn("Encrypted Password");
		Mockito.when(carRepo.save(Mockito.any())).thenReturn(carDriver);

		CarDriverDTO savedCarDriver = driverServiceImpl.registerDriver(carDriverDTO);

		assertEquals(AccountType.DRIVER, savedCarDriver.getAccountType());
		assertEquals(carDriver.getDrivenBy().getUserId().longValue(), savedCarDriver.getUserId().longValue());
		assertEquals(carDriver.getCarId().longValue(), savedCarDriver.getCarId().longValue());
	}

	@Test
	public void testGetAllDrivers() {
		List<CarEntity> expectedCarList = new ArrayList<CarEntity>();
		expectedCarList.add(getCarEntity(123L, "aakash", "ranglani", "7506500591", 222L, "Maruti", "MH05GG1000"));
		expectedCarList.add(getCarEntity(135L, "aakash1", "ranglani2", "7506500592", 333L, "Maruti2", "MH05GG1002"));
		expectedCarList.add(getCarEntity(155L, "aakash2", "ranglani2", "7506500593", 444L, "Maruti3", "MH05GG1003"));

		Mockito.when(carRepo.findAllByCarStatus(Mockito.any())).thenReturn(expectedCarList);

		List<CarDriverDTO> actualCarList = driverServiceImpl.getAvailableDrivers();

		assertEquals(expectedCarList.size(), actualCarList.size());
		assertEquals(expectedCarList.get(0).getCarId().longValue(), actualCarList.get(0).getCarId().longValue());
		assertEquals(expectedCarList.get(1).getCarId().longValue(), actualCarList.get(1).getCarId().longValue());
		assertEquals(expectedCarList.get(2).getCarId().longValue(), actualCarList.get(2).getCarId().longValue());

		assertEquals(expectedCarList.get(0).getDrivenBy().getUserId().longValue(),
				actualCarList.get(0).getUserId().longValue());
		assertEquals(expectedCarList.get(1).getDrivenBy().getUserId().longValue(),
				actualCarList.get(1).getUserId().longValue());
		assertEquals(expectedCarList.get(2).getDrivenBy().getUserId().longValue(),
				actualCarList.get(2).getUserId().longValue());
	}

	@Test
	public void testGetDriver() {
		UserAccountEntity expectedAccount = new UserAccountEntity();
		expectedAccount.setUserId(321L);
		expectedAccount.setAccountType(AccountType.DRIVER.toString());
		expectedAccount.setCreatedOn(new Date());
		expectedAccount.setFirstName("Aakash666");
		expectedAccount.setLastName("Ranglani666");
		expectedAccount.setMobileNumber("7516504514");
		Mockito.when(userAccountRepo.findByMobileNumber(Mockito.anyString())).thenReturn(expectedAccount);

		CarDriverDTO actualAccount = driverServiceImpl.getDriver(Mockito.anyString());

		assertEquals(expectedAccount.getUserId().longValue(), actualAccount.getUserId().longValue());
		assertEquals(expectedAccount.getFirstName(), actualAccount.getFirstName());
		assertEquals(expectedAccount.getMobileNumber(), actualAccount.getMobileNumber());
	}

	private CarEntity getCarEntity(long userId, String firstName, String lastName, String mobileNumber, long carId,
			String carName, String carNumber) {
		UserAccountEntity userAccount = new UserAccountEntity();
		userAccount.setUserId(userId);
		userAccount.setAccountType(AccountType.DRIVER.toString());
		userAccount.setCreatedOn(new Date());
		userAccount.setFirstName(firstName);
		userAccount.setLastName(lastName);
		userAccount.setMobileNumber(mobileNumber);
		CarEntity carDriver = new CarEntity();
		carDriver.setCarId(carId);
		carDriver.setCarName(carName);
		carDriver.setCarNumber(carNumber);
		carDriver.setCarStatus(CarStatus.AVAILABLE.toString());
		carDriver.setDrivenBy(userAccount);
		return carDriver;
	}

}
