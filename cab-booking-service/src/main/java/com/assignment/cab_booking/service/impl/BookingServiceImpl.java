package com.assignment.cab_booking.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.cab_booking.constants.ApplicationConstants;
import com.assignment.cab_booking.entity.BookingEntity;
import com.assignment.cab_booking.entity.CarDriverEntity;
import com.assignment.cab_booking.entity.UserAccountEntity;
import com.assignment.cab_booking.exception.BookingServiceException;
import com.assignment.cab_booking.mapper.BookingMapper;
import com.assignment.cab_booking.model.AccountType;
import com.assignment.cab_booking.model.BookingState;
import com.assignment.cab_booking.model.CarStatus;
import com.assignment.cab_booking.model.dto.BookingDTO;
import com.assignment.cab_booking.repository.BookingRepository;
import com.assignment.cab_booking.repository.CarDriverRepository;
import com.assignment.cab_booking.repository.UserAccountRepository;
import com.assignment.cab_booking.service.BookingService;
import com.assignment.cab_booking.utils.Utils;
import com.assignment.cab_booking.view.CabBookingStatus;

@Service
public class BookingServiceImpl implements BookingService {

	private UserAccountRepository userAccountRepo;

	private CarDriverRepository carRepo;

	private BookingRepository bookingRepo;

	@Autowired
	private BookingMapper bookingMapper;

	@Autowired
	private Utils utils;

	@Autowired
	public BookingServiceImpl(UserAccountRepository userAccountRepository, CarDriverRepository carRepository,
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

		String customerNumber = bookingDTO.getCustomerDto().getMobileNumber();

		if (bookingRepo.findByCustomerDetailsMobileNumberAndState(customerNumber,
				BookingState.ACTIVE.toString()) != null) {
			throw new BookingServiceException("Simultaneous bookings are not allowed!");
		}

		// get user
		UserAccountEntity customerAccount = userAccountRepo.findByMobileNumberAndAccountType(customerNumber,
				AccountType.CUSTOMER.toString());

		if (customerAccount == null) {
			throw new BookingServiceException("You are not a registered customer!");
		}

		BookingEntity bookingEntity = mapper.map(bookingDTO, BookingEntity.class);

		// find best car
		CarDriverEntity availableCab = findBestCab(bookingEntity.getStartLatitude(), bookingEntity.getStartLongitude());

		if (availableCab == null) {
			throw new BookingServiceException("No cabs available! Please try again later.");
		}

		BookingEntity saveBooking = setupBookingDetails(bookingEntity, availableCab, customerAccount);

		BookingEntity bookedCar = bookingRepo.save(saveBooking);

		// Set the car status as busy
		availableCab.setCarStatus(CarStatus.BUSY.toString());
		carRepo.save(availableCab);

		BookingDTO bookedCarDto = mapper.map(bookedCar, BookingDTO.class);
		return bookedCarDto;
	}

	private BookingEntity setupBookingDetails(BookingEntity bookingEntity, CarDriverEntity availableCab,
			UserAccountEntity customerAccount) {
		bookingEntity.setCarEntity(availableCab);
		bookingEntity.setCustomerDetails(customerAccount);
		bookingEntity.setReferenceNo(utils.generatedBookingReference(10));
		bookingEntity.setBookingTime(LocalDateTime.now().toString());
		bookingEntity.setState(BookingState.ACTIVE.toString());
		return bookingEntity;
	}

	// Can be replaced with another Microservice that uses Kafka to track locations
	// of the cabs
	// Or an in-memory cache like Redis that also supports GeoSpatial Queries.
	private CarDriverEntity findBestCab(Double latitude, Double longitude) {
		return carRepo.findBestCab(latitude, longitude, ApplicationConstants.SEARCH_RADIUS_IN_KILOMETERS);
	}

	@Override
	public List<CabBookingStatus> findAllCabBookingStatus() {
		return bookingRepo.findCabDetails();
	}

}
