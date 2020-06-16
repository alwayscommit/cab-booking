package com.assignment.cab_booking.service.impl;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.assignment.cab_booking.constants.ApplicationConstants;
import com.assignment.cab_booking.entity.CarDriverEntity;
import com.assignment.cab_booking.entity.UserAccountEntity;
import com.assignment.cab_booking.model.AccountType;
import com.assignment.cab_booking.model.CarStatus;
import com.assignment.cab_booking.model.dto.CabDriverDTO;
import com.assignment.cab_booking.model.dto.LocationDTO;
import com.assignment.cab_booking.repository.CarDriverRepository;
import com.assignment.cab_booking.repository.UserAccountRepository;
import com.assignment.cab_booking.service.CabDriverService;

@Service
public class CabDriverServiceImpl implements CabDriverService {

	private UserAccountRepository userAccountRepo;

	private CarDriverRepository carRepo;

	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	public CabDriverServiceImpl(UserAccountRepository userAccountRepo, CarDriverRepository carRepo,
			BCryptPasswordEncoder passwordEncoder) {
		this.userAccountRepo = userAccountRepo;
		this.carRepo = carRepo;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public CabDriverDTO registerDriver(CabDriverDTO carDriverDTO) {

		ModelMapper modelMapper = new ModelMapper();
		UserAccountEntity driverAccount = modelMapper.map(carDriverDTO, UserAccountEntity.class);
		driverAccount.setEncryptedPassword(passwordEncoder.encode(carDriverDTO.getPassword()));
		driverAccount.setAccountType(AccountType.DRIVER.toString());
		driverAccount.setCreatedOn(new Date());

		CarDriverEntity carEntity = modelMapper.map(carDriverDTO, CarDriverEntity.class);
		carEntity.setCarStatus(CarStatus.AVAILABLE.toString());
		carEntity.setDrivenBy(driverAccount);

		CarDriverEntity savedCar = carRepo.save(carEntity);
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		CabDriverDTO driverResponse = modelMapper.map(savedCar, CabDriverDTO.class);

		return driverResponse;
	}

	@Override
	public List<CabDriverDTO> getNearByAvailableCabDrivers(LocationDTO locationDto) {
		List<CarDriverEntity> availableCarList = carRepo.findAvailableCabs(locationDto.getLatitude(),
				locationDto.getLongitude(), ApplicationConstants.SEARCH_RADIUS_IN_KILOMETERS);

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		List<CabDriverDTO> availableDriversDTO = modelMapper.map(availableCarList, new TypeToken<List<CabDriverDTO>>() {
		}.getType());
		return availableDriversDTO;
	}

	@Override
	public CabDriverDTO getDriver(String mobileNumber) {
		UserAccountEntity driverDetails = userAccountRepo.findByMobileNumberAndAccountType(mobileNumber,
				AccountType.DRIVER.toString());
		
		if(driverDetails==null) {
			return null;
		}
		
		ModelMapper modelMapper = new ModelMapper();
		CabDriverDTO driverDTO = modelMapper.map(driverDetails, CabDriverDTO.class);
		return driverDTO;
	}

	@Override
	public CabDriverDTO updateDriverCarLocation(String driverNumber, LocationDTO locationDto) {
		CarDriverEntity carEntity = carRepo.findByDrivenByMobileNumber(driverNumber);
		carEntity.setLatitude(locationDto.getLatitude());
		carEntity.setLongitude(locationDto.getLongitude());
		ModelMapper modelMapper = new ModelMapper();
		CarDriverEntity updatedCarLocation = carRepo.save(carEntity);
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		CabDriverDTO updatedCarLocationDto = modelMapper.map(updatedCarLocation, CabDriverDTO.class);
		return updatedCarLocationDto;
	}

}
