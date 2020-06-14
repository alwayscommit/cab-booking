package com.assignment.cab_booking.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.assignment.cab_booking.entity.BookingEntity;
import com.assignment.cab_booking.entity.CarEntity;
import com.assignment.cab_booking.entity.UserAccountEntity;
import com.assignment.cab_booking.mapper.BookingMapper;
import com.assignment.cab_booking.model.AccountType;
import com.assignment.cab_booking.model.BookingState;
import com.assignment.cab_booking.model.CarStatus;
import com.assignment.cab_booking.model.dto.BookingDTO;
import com.assignment.cab_booking.repository.BookingRepository;
import com.assignment.cab_booking.repository.CarRepository;
import com.assignment.cab_booking.repository.UserAccountRepository;
import com.assignment.cab_booking.service.BookingService;
import com.assignment.cab_booking.utils.Utils;

@Service
public class BookingServiceImpl implements BookingService {

	private static final Integer SEARCH_RADIUS = 5;

	private UserAccountRepository userAccountRepo;

	private CarRepository carRepo;

	private BookingRepository bookingRepo;

	@Autowired
	private BookingMapper bookingMapper;

	@Autowired
	private Utils utils;

	@Autowired
	public BookingServiceImpl(UserAccountRepository userAccountRepository, CarRepository carRepository,
			BookingRepository bookingRepo) {
		this.userAccountRepo = userAccountRepository;
		this.carRepo = carRepository;
		this.bookingRepo = bookingRepo;
	}

	@Override
	public BookingDTO bookCab(BookingDTO bookingDTO) {
		ModelMapper mapper = new ModelMapper();
		mapper.addMappings(bookingMapper.bookingDtoToEntity());
		mapper.addMappings(bookingMapper.bookingEntityToDTOMapping());

		// get user
		UserAccountEntity customerAccount = userAccountRepo.findByMobileNumberAndAccountType(
				bookingDTO.getCustomerDto().getMobileNumber(), AccountType.CUSTOMER.toString());
		
		/*if(customerAccount==null) {
			throw new CabServiceException("You are not a registered customer!");
		}*/
		
		BookingEntity bookingEntity = mapper.map(bookingDTO, BookingEntity.class);

		// find best car
		CarEntity availableCab = findCab(bookingEntity.getStartLatitude(), bookingEntity.getStartLongitude());

		/*if(availableCab==null) {
			throw new CabServiceException("No cabs available! Please try again later.");
		}*/
		
		BookingEntity saveBooking = setupBookingDetails(bookingEntity, availableCab, customerAccount);

		BookingEntity bookedCar = bookingRepo.save(saveBooking);

		// Set the car status as busy
		availableCab.setCarStatus(CarStatus.BUSY.toString());
		carRepo.save(availableCab);

		
		BookingDTO bookedCarDto = mapper.map(bookedCar, BookingDTO.class);
		return bookedCarDto;
	}

	private BookingEntity setupBookingDetails(BookingEntity bookingEntity, CarEntity availableCab,
			UserAccountEntity customerAccount) {
		bookingEntity.setCarEntity(availableCab);
		bookingEntity.setCustomerDetails(customerAccount);
		bookingEntity.setReferenceNo(utils.generatedBookingReference(10));
		bookingEntity.setBookingTime(LocalDateTime.now().toString());
		bookingEntity.setState(BookingState.ACTIVE);
		return bookingEntity;
	}

	// Can be replaced with another Microservice that uses Kafka to track locations
	// of the cabs
	// Or an in-memory cache like Redis that also supports GeoSpatial Queries.
	private CarEntity findCab(Double latitude, Double longitude) {
		return carRepo.findAvailableCarWithinRadius(latitude, longitude, SEARCH_RADIUS);
	}

	@Override
	public List<BookingEntity> findAllCabs(Pageable pageable) {
		
		List<BookingEntity> bookingEntityList = bookingRepo.findAllCabs();
		return bookingEntityList;
	}

}
