package com.assignment.cab_booking.service.impl;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.assignment.cab_booking.constants.ApplicationConstants;
import com.assignment.cab_booking.constants.ExceptionConstants;
import com.assignment.cab_booking.entity.CarDriverEntity;
import com.assignment.cab_booking.entity.UserAccountEntity;
import com.assignment.cab_booking.exception.CabDriverServiceException;
import com.assignment.cab_booking.mapper.CabDriverMapper;
import com.assignment.cab_booking.model.AccountType;
import com.assignment.cab_booking.model.CarStatus;
import com.assignment.cab_booking.model.dto.CabDriverDTO;
import com.assignment.cab_booking.model.dto.LocationDTO;
import com.assignment.cab_booking.repository.CarDriverRepository;
import com.assignment.cab_booking.repository.UserAccountRepository;
import com.assignment.cab_booking.service.CabDriverService;

@Service
public class CabDriverServiceImpl implements CabDriverService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CabDriverServiceImpl.class);

	private UserAccountRepository userAccountRepo;

	private CarDriverRepository carRepo;

	private BCryptPasswordEncoder passwordEncoder;

	private CabDriverMapper cabDriverMapper;

	@Autowired
	public CabDriverServiceImpl(UserAccountRepository userAccountRepo, CarDriverRepository carRepo,
			BCryptPasswordEncoder passwordEncoder, CabDriverMapper cabDriverMapper) {
		this.userAccountRepo = userAccountRepo;
		this.carRepo = carRepo;
		this.passwordEncoder = passwordEncoder;
		this.cabDriverMapper = cabDriverMapper;
	}

	@Override
	public CabDriverDTO registerDriver(CabDriverDTO carDriverDTO) {

		CarDriverEntity carEntity = setupCarDriverEntity(carDriverDTO);

		CarDriverEntity savedCar = carRepo.save(carEntity);

		if (savedCar == null) {
			LOGGER.error("Unable to create driver...");
			throw new CabDriverServiceException(ExceptionConstants.CAB_DRIVER_CREATION_MESSAGE);
		}

		CabDriverDTO driverResponse = cabDriverMapper.mapToDTO(savedCar);
		LOGGER.info(String.format("Car %s created successfully...", savedCar.getCarId()));
		return driverResponse;
	}

	@Override
	public List<CabDriverDTO> getNearByAvailableCabDrivers(LocationDTO locationDto) {
		List<CarDriverEntity> availableCarList = carRepo.findAvailableCabs(locationDto.getLatitude(),
				locationDto.getLongitude(), ApplicationConstants.SEARCH_RADIUS_IN_KILOMETERS);
		return cabDriverMapper.mapToListDTO(availableCarList);
	}

	@Override
	public CabDriverDTO getDriver(String mobileNumber) {
		UserAccountEntity driverDetails = userAccountRepo.findByMobileNumberAndAccountType(mobileNumber,
				AccountType.DRIVER.toString());

		if (driverDetails == null) {
			return null;
		}

		return cabDriverMapper.mapToDTO(driverDetails);
	}

	@Override
	public CabDriverDTO updateDriverCarLocation(String driverNumber, LocationDTO locationDto) {
		CarDriverEntity carEntity = carRepo.findByDrivenByMobileNumber(driverNumber);
		carEntity.setLatitude(locationDto.getLatitude());
		carEntity.setLongitude(locationDto.getLongitude());

		CarDriverEntity updatedCarLocation = carRepo.save(carEntity);

		return cabDriverMapper.mapToDTO(updatedCarLocation);
	}

	private CarDriverEntity setupCarDriverEntity(CabDriverDTO carDriverDTO) {
		// user details
		UserAccountEntity driverAccount = cabDriverMapper.mapToUserEntity(carDriverDTO);
		driverAccount.setEncryptedPassword(passwordEncoder.encode(carDriverDTO.getPassword()));
		driverAccount.setAccountType(AccountType.DRIVER.toString());
		driverAccount.setCreatedOn(new Date());

		// car details
		CarDriverEntity carEntity = cabDriverMapper.mapToCarEntity(carDriverDTO);
		carEntity.setCarStatus(CarStatus.AVAILABLE.toString());
		carEntity.setDrivenBy(driverAccount);
		return carEntity;
	}

}
