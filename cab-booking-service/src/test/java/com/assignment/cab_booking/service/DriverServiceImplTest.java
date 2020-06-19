package com.assignment.cab_booking.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
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

import com.assignment.cab_booking.entity.CarDriverEntity;
import com.assignment.cab_booking.entity.UserAccountEntity;
import com.assignment.cab_booking.mapper.CabDriverMapper;
import com.assignment.cab_booking.model.AccountType;
import com.assignment.cab_booking.model.CarStatus;
import com.assignment.cab_booking.model.dto.CabDriverDTO;
import com.assignment.cab_booking.repository.CarDriverRepository;
import com.assignment.cab_booking.repository.UserAccountRepository;
import com.assignment.cab_booking.service.impl.CabDriverServiceImpl;
import com.assignment.cab_booking.utils.ApplicationUtils;

class DriverServiceImplTest {

	@Mock
	private UserAccountRepository userAccountRepo;

	@Mock
	private CarDriverRepository carRepo;

	@Mock
	private BCryptPasswordEncoder passwordEncoder;

	@Mock
	private CabDriverMapper cabDriverMapper;
	
	@Mock
	private ApplicationUtils utils;

	@InjectMocks
	private CabDriverServiceImpl driverServiceImpl;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@BeforeClass
	public void setup() {
		this.driverServiceImpl = new CabDriverServiceImpl(userAccountRepo, carRepo, passwordEncoder, cabDriverMapper, utils);
	}

	@Test
	public void testMockRegisterDriver() {

		Mockito.when(passwordEncoder.encode(anyString())).thenReturn("Encrypted Password");
		Mockito.when(carRepo.save(Mockito.any())).thenReturn(getCarDriverEntityTestData());
		Mockito.when(cabDriverMapper.mapToUserEntity(Mockito.any())).thenReturn(getUserAccountTestData());
		Mockito.when(cabDriverMapper.mapToCarEntity(Mockito.any())).thenReturn(getCarDriverEntityTestData());
		Mockito.when(utils.generateUserId(10)).thenReturn("acdvda111");

		driverServiceImpl.registerDriver(mock(CabDriverDTO.class));

		verify(passwordEncoder, times(1)).encode(Mockito.any());
		verify(carRepo, times(1)).save(Mockito.any());
		verify(cabDriverMapper, times(1)).mapToUserEntity(Mockito.any());
		verify(cabDriverMapper, times(1)).mapToCarEntity(Mockito.any());

	}

	@Test
	public void testRegisterDriver() {

		CabDriverDTO carDriverDTO = getCabDriverDTOTestData();

		Mockito.when(utils.generateUserId(10)).thenReturn("String1234");
		Mockito.when(passwordEncoder.encode(anyString())).thenReturn("Encrypted Password");
		Mockito.when(carRepo.save(Mockito.any())).thenReturn(mock(CarDriverEntity.class));
		Mockito.when(cabDriverMapper.mapToUserEntity(Mockito.any())).thenReturn(getUserAccountTestData());
		Mockito.when(cabDriverMapper.mapToDTO(Mockito.any(CarDriverEntity.class))).thenReturn(carDriverDTO);
		Mockito.when(cabDriverMapper.mapToCarEntity(Mockito.any())).thenReturn(getCarDriverEntityTestData());

		CabDriverDTO actualCarDriver = driverServiceImpl.registerDriver(carDriverDTO);

		assertEquals(AccountType.DRIVER, actualCarDriver.getAccountType());
		assertEquals(carDriverDTO.getUserId(), actualCarDriver.getUserId());
		assertEquals(carDriverDTO.getCarId(), actualCarDriver.getCarId());
		verify(passwordEncoder, times(1)).encode(Mockito.any());
		verify(carRepo, times(1)).save(Mockito.any());
		verify(cabDriverMapper, times(1)).mapToUserEntity(Mockito.any());
		verify(cabDriverMapper, times(1)).mapToCarEntity(Mockito.any());
		verify(cabDriverMapper, times(1)).mapToDTO(Mockito.any(CarDriverEntity.class));
	}

	/*@Test
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
	}*/

	@Test
	public void testGetDriver() {

		Mockito.when(userAccountRepo.findByUserIdAndAccountType(Mockito.anyString(), Mockito.any()))
				.thenReturn(mock(UserAccountEntity.class));

		CabDriverDTO expectedAccount = getCabDriverDTOTestData();
		Mockito.when(cabDriverMapper.mapToDTO(Mockito.any(UserAccountEntity.class))).thenReturn(expectedAccount);

		CabDriverDTO actualAccount = driverServiceImpl.getDriver("7516504514");

		assertEquals(expectedAccount.getUserId(), actualAccount.getUserId());
		assertEquals(expectedAccount.getFirstName(), actualAccount.getFirstName());
		assertEquals(expectedAccount.getMobileNumber(), actualAccount.getMobileNumber());
	}

	private CarDriverEntity getCarDriverEntityTestData() {
		CarDriverEntity carDriver = new CarDriverEntity();
		carDriver.setCarId("String1234");
		carDriver.setCarName("Maruti Swift666");
		carDriver.setCarNumber("MH04MB22222");
		carDriver.setCarStatus(CarStatus.AVAILABLE);
		carDriver.setDrivenBy(getUserAccountTestData());
		return carDriver;
	}

	private UserAccountEntity getUserAccountTestData() {
		UserAccountEntity userAccount = new UserAccountEntity();
		userAccount.setUserId("String1234");
		userAccount.setAccountType(AccountType.DRIVER);
		userAccount.setCreatedOn(new Date());
		userAccount.setFirstName("Aakash666");
		userAccount.setLastName("Ranglani666");
		userAccount.setMobileNumber("7516504514");
		return userAccount;
	}

	private CabDriverDTO getCabDriverDTOTestData() {
		CabDriverDTO carDriverDTO = new CabDriverDTO();
		carDriverDTO.setAccountType(AccountType.DRIVER);
		carDriverDTO.setUserId("String1234");
		carDriverDTO.setCarId("aaaacssd22");
		carDriverDTO.setCarName("Maruti Swift666");
		carDriverDTO.setFirstName("Aakash666");
		carDriverDTO.setLastName("Ranglani666");
		carDriverDTO.setMobileNumber("7516504514");
		carDriverDTO.setPassword("aakash12");
		carDriverDTO.setCarNumber("MH04MB22222");
		return carDriverDTO;
	}

}
