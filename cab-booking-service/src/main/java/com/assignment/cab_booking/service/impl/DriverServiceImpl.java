package com.assignment.cab_booking.service.impl;

import java.sql.Date;
import java.time.Instant;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.assignment.cab_booking.entity.CarEntity;
import com.assignment.cab_booking.entity.UserAccountEntity;
import com.assignment.cab_booking.model.AccountType;
import com.assignment.cab_booking.model.CarStatus;
import com.assignment.cab_booking.model.dto.CarDriverDTO;
import com.assignment.cab_booking.model.dto.LocationDTO;
import com.assignment.cab_booking.repository.CarRepository;
import com.assignment.cab_booking.repository.UserAccountRepository;
import com.assignment.cab_booking.service.DriverService;

@Service
public class DriverServiceImpl implements DriverService {

	private UserAccountRepository userAccountRepo;

	private CarRepository carRepo;

	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	public DriverServiceImpl(UserAccountRepository userAccountRepo, CarRepository carRepo,
			BCryptPasswordEncoder passwordEncoder) {
		this.userAccountRepo = userAccountRepo;
		this.carRepo = carRepo;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public CarDriverDTO registerDriver(CarDriverDTO carDriverDTO) {

		ModelMapper modelMapper = new ModelMapper();
		UserAccountEntity driverAccount = modelMapper.map(carDriverDTO, UserAccountEntity.class);
		driverAccount.setEncryptedPassword(passwordEncoder.encode(carDriverDTO.getPassword()));
		driverAccount.setAccountType(AccountType.DRIVER.toString());
		driverAccount.setCreatedOn(Date.from(Instant.now()));

		CarEntity carEntity = modelMapper.map(carDriverDTO, CarEntity.class);
		carEntity.setCarStatus(CarStatus.AVAILABLE.toString());
		carEntity.setDrivenBy(driverAccount);

		CarEntity savedCar = carRepo.save(carEntity);
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		CarDriverDTO driverResponse = modelMapper.map(savedCar, CarDriverDTO.class);

		return driverResponse;
	}

	@Override
	public List<CarDriverDTO> getAvailableDrivers() {
		List<CarEntity> availableCarList = carRepo.findAllByCarStatus(CarStatus.AVAILABLE.toString());
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		List<CarDriverDTO> availableDriversDTO = modelMapper.map(availableCarList, new TypeToken<List<CarDriverDTO>>() {
		}.getType());
		return availableDriversDTO;
	}

	@Override
	public CarDriverDTO getDriver(String mobileNumber) {
		UserAccountEntity driverDetails = userAccountRepo.findByMobileNumber(mobileNumber);
		ModelMapper modelMapper = new ModelMapper();
		CarDriverDTO driverDTO = modelMapper.map(driverDetails, CarDriverDTO.class);
		return driverDTO;
	}

	@Override
	public CarDriverDTO updateDriverCarLocation(String driverNumber, LocationDTO locationDto) {
		CarEntity carEntity = carRepo.findByDrivenByMobileNumber(driverNumber);
		carEntity.setLatitude(locationDto.getLatitude());
		carEntity.setLongitude(locationDto.getLongitude());
		ModelMapper modelMapper = new ModelMapper();
		CarEntity updatedCarLocation = carRepo.save(carEntity);
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		CarDriverDTO updatedCarLocationDto = modelMapper.map(updatedCarLocation, CarDriverDTO.class);
		return updatedCarLocationDto;
	}

}
