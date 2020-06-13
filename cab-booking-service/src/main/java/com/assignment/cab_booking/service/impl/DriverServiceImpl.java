package com.assignment.cab_booking.service.impl;

import java.sql.Date;
import java.time.Instant;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.assignment.cab_booking.entity.AccountType;
import com.assignment.cab_booking.entity.CarEntity;
import com.assignment.cab_booking.entity.CarStatus;
import com.assignment.cab_booking.entity.UserAccountEntity;
import com.assignment.cab_booking.model.dto.DriverDTO;
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
	public DriverDTO registerDriver(DriverDTO driverDTO) {

		ModelMapper modelMapper = new ModelMapper();
		UserAccountEntity driverAccount = modelMapper.map(driverDTO, UserAccountEntity.class);
		driverAccount.setEncryptedPassword(passwordEncoder.encode(driverDTO.getPassword()));
		driverAccount.setAccountType(AccountType.DRIVER);
		driverAccount.setCreatedOn(Date.from(Instant.now()));
		
		CarEntity carEntity = modelMapper.map(driverDTO, CarEntity.class);
		carEntity.setCarStatus(CarStatus.AVAILABLE);
		carEntity.setDrivenBy(driverAccount);

		CarEntity savedCar = carRepo.save(carEntity);
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		DriverDTO driverResponse = modelMapper.map(savedCar, DriverDTO.class);
		
		return driverResponse;
	}

	@Override
	public List<DriverDTO> getDrivers() {
		return null;
	}

	@Override
	public DriverDTO getDriver(String id) {
		return null;
	}

}
