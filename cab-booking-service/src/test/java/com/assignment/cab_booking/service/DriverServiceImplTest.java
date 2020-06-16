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

import com.assignment.cab_booking.entity.CarDriverEntity;
import com.assignment.cab_booking.entity.UserAccountEntity;
import com.assignment.cab_booking.model.AccountType;
import com.assignment.cab_booking.model.CarStatus;
import com.assignment.cab_booking.model.dto.CabDriverDTO;
import com.assignment.cab_booking.model.dto.LocationDTO;
import com.assignment.cab_booking.repository.CarDriverRepository;
import com.assignment.cab_booking.repository.UserAccountRepository;
import com.assignment.cab_booking.service.impl.CabDriverServiceImpl;

class DriverServiceImplTest {

	@Mock
	private UserAccountRepository userAccountRepo;

	@Mock
	private CarDriverRepository carRepo;

	@Mock
	private BCryptPasswordEncoder passwordEncoder;

	@InjectMocks
	private CabDriverServiceImpl driverServiceImpl;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@BeforeClass
	public void setup() {
		this.driverServiceImpl = new CabDriverServiceImpl(userAccountRepo, carRepo, passwordEncoder);
	}

	@Test
	public void testMockRegisterDriver() {
		CabDriverDTO carDriverDTO = new CabDriverDTO("7506500444", "Aakash", "Ranglani", "aakash", AccountType.DRIVER,
				"Audi", CarStatus.AVAILABLE, "MH04JP0222", 19.231309, 72.982752, new Date());

		Mockito.when(passwordEncoder.encode(anyString())).thenReturn("Encrypted Password");
		Mockito.when(carRepo.save(Mockito.any())).thenReturn(getCarDriverEntityTestData());

		driverServiceImpl.registerDriver(carDriverDTO);

		verify(passwordEncoder, times(1)).encode(Mockito.anyString());
		verify(carRepo, times(1)).save(Mockito.any());
	}

	@Test
	public void testRegisterDriver() {

		CabDriverDTO carDriverDTO = getCabDriverDTOTestData();
		CarDriverEntity expectedCarDriver = getCarDriverEntityTestData();
		Mockito.when(passwordEncoder.encode(anyString())).thenReturn("Encrypted Password");
		Mockito.when(carRepo.save(Mockito.any())).thenReturn(expectedCarDriver);

		CabDriverDTO actualCarDriver = driverServiceImpl.registerDriver(carDriverDTO);

		assertEquals(AccountType.DRIVER, actualCarDriver.getAccountType());
		assertEquals(expectedCarDriver.getDrivenBy().getUserId().longValue(), actualCarDriver.getUserId().longValue());
		assertEquals(expectedCarDriver.getCarId().longValue(), actualCarDriver.getCarId().longValue());
	}

	private CabDriverDTO getCabDriverDTOTestData() {
		CabDriverDTO carDriverDTO = new CabDriverDTO();
		carDriverDTO.setCarName("Maruti Swift666");
		carDriverDTO.setFirstName("Aakash666");
		carDriverDTO.setLastName("Ranglani666");
		carDriverDTO.setMobileNumber("7516504514");
		carDriverDTO.setPassword("aakash12");
		carDriverDTO.setCarNumber("MH04MB22222");
		return carDriverDTO;
	}

	@Test
	public void testGetAllDrivers() {
		List<CarDriverEntity> expectedCarList = new ArrayList<CarDriverEntity>();
		expectedCarList.add(getCarEntityData(123L, "aakash", "ranglani", "7506500591", 222L, "Maruti", "MH05GG1000"));
		expectedCarList
				.add(getCarEntityData(135L, "aakash1", "ranglani2", "7506500592", 333L, "Maruti2", "MH05GG1002"));
		expectedCarList
				.add(getCarEntityData(155L, "aakash2", "ranglani2", "7506500593", 444L, "Maruti3", "MH05GG1003"));

		Mockito.when(carRepo.findAvailableCabs(Mockito.any(), Mockito.any(), Mockito.any()))
				.thenReturn(expectedCarList);

		List<CabDriverDTO> actualCarList = driverServiceImpl
				.getNearByAvailableCabDrivers(new LocationDTO(19.2309696, 72.9824377));

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
		Mockito.when(userAccountRepo.findByMobileNumberAndAccountType(Mockito.anyString(), Mockito.anyString()))
				.thenReturn(expectedAccount);

		CabDriverDTO actualAccount = driverServiceImpl.getDriver("7516504514");

		assertEquals(expectedAccount.getUserId().longValue(), actualAccount.getUserId().longValue());
		assertEquals(expectedAccount.getFirstName(), actualAccount.getFirstName());
		assertEquals(expectedAccount.getMobileNumber(), actualAccount.getMobileNumber());
	}

	private CarDriverEntity getCarEntityData(long userId, String firstName, String lastName, String mobileNumber,
			long carId, String carName, String carNumber) {
		UserAccountEntity userAccount = new UserAccountEntity();
		userAccount.setUserId(userId);
		userAccount.setAccountType(AccountType.DRIVER.toString());
		userAccount.setCreatedOn(new Date());
		userAccount.setFirstName(firstName);
		userAccount.setLastName(lastName);
		userAccount.setMobileNumber(mobileNumber);
		CarDriverEntity carDriver = new CarDriverEntity();
		carDriver.setCarId(carId);
		carDriver.setCarName(carName);
		carDriver.setCarNumber(carNumber);
		carDriver.setCarStatus(CarStatus.AVAILABLE.toString());
		carDriver.setDrivenBy(userAccount);
		return carDriver;
	}

	private CarDriverEntity getCarDriverEntityTestData() {
		CarDriverEntity carDriver = new CarDriverEntity();
		carDriver.setCarId(123L);
		carDriver.setCarName("Maruti Swift666");
		carDriver.setCarNumber("MH04MB22222");
		carDriver.setCarStatus(CarStatus.AVAILABLE.toString());
		carDriver.setDrivenBy(getUserAccountTestData());
		return carDriver;
	}

	private UserAccountEntity getUserAccountTestData() {
		UserAccountEntity userAccount = new UserAccountEntity();
		userAccount.setUserId(321L);
		userAccount.setAccountType(AccountType.DRIVER.toString());
		userAccount.setCreatedOn(new Date());
		userAccount.setFirstName("Aakash666");
		userAccount.setLastName("Ranglani666");
		userAccount.setMobileNumber("7516504514");
		return userAccount;
	}

}
