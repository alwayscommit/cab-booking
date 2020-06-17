package com.assignment.cab_booking.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.cab_booking.constants.ApplicationConstants;
import com.assignment.cab_booking.constants.ExceptionConstants;
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

	private static final Logger LOGGER = LoggerFactory.getLogger(BookingServiceImpl.class);

	private UserAccountRepository userAccountRepo;

	private CarDriverRepository carRepo;

	private BookingRepository bookingRepo;

	private BookingMapper bookingMapper;

	@Autowired
	private Utils utils;

	@Autowired
	public BookingServiceImpl(UserAccountRepository userAccountRepository, CarDriverRepository carRepository,
			BookingRepository bookingRepo, BookingMapper bookingMapper) {
		this.userAccountRepo = userAccountRepository;
		this.carRepo = carRepository;
		this.bookingRepo = bookingRepo;
		this.bookingMapper = bookingMapper;
	}

	@Override
	public BookingDTO bookCab(BookingDTO bookingDTO) {
		String customerNumber = bookingDTO.getCustomerDto().getMobileNumber();

		if (customerHasActiveBooking(customerNumber)) {
			LOGGER.info(String.format("Customer %s already has an active booking...", customerNumber));
			throw new BookingServiceException(ExceptionConstants.SIMULTANEOUS_BOOKING_MESSAGE);
		}

		UserAccountEntity customerAccount = getCustomerAccount(customerNumber);

		// This check will not be there ideally as only logged in users will be able to
		// access this API.
		if (accountNotExist(customerAccount)) {
			throw new BookingServiceException(ExceptionConstants.CUSTOMER_NOT_REGISTERED_MESSAGE);
		}

		// find best car
		CarDriverEntity availableCab = findBestCab(bookingDTO.getStartLatitude(), bookingDTO.getStartLongitude());

		if (availableCab == null) {
			LOGGER.info("Cabs not found around the required location...");
			throw new BookingServiceException(ExceptionConstants.NO_CABS_AVAILABLE_MESSAGE);
		}

		BookingEntity saveBooking = setupBookingDetails(bookingDTO, availableCab, customerAccount);

		BookingEntity bookedCar = bookingRepo.save(saveBooking);

		return bookingMapper.mapToDTO(bookedCar);
	}

	@Override
	public List<CabBookingStatus> findAllCabBookingStatus() {
		return bookingRepo.findCabDetails();
	}

	private BookingEntity setupBookingDetails(BookingDTO bookingDTO, CarDriverEntity availableCab,
			UserAccountEntity customerAccount) {
		availableCab.setCarStatus(CarStatus.BUSY.toString());

		BookingEntity bookingEntity = bookingMapper.mapToEntity(bookingDTO);
		bookingEntity.setCarEntity(availableCab);
		bookingEntity.setCustomerDetails(customerAccount);
		bookingEntity.setReferenceNo(utils.generatedBookingReference(10));
		bookingEntity.setBookingTime(LocalDateTime.now().toString());
		bookingEntity.setState(BookingState.ACTIVE.toString());

		return bookingEntity;
	}

	private boolean accountNotExist(UserAccountEntity customerAccount) {
		return customerAccount == null;
	}

	private UserAccountEntity getCustomerAccount(String customerNumber) {
		return userAccountRepo.findByMobileNumberAndAccountType(customerNumber, AccountType.CUSTOMER.toString());
	}

	private boolean customerHasActiveBooking(String customerNumber) {
		return bookingRepo.findByCustomerDetailsMobileNumberAndState(customerNumber,
				BookingState.ACTIVE.toString()) != null;
	}

	// Can be replaced with another Microservice that uses Kafka to track locations
	// of the cabs
	// Or an in-memory cache like Redis that also supports GeoSpatial Queries.
	private CarDriverEntity findBestCab(Double latitude, Double longitude) {
		LOGGER.info("Searching for best cab for the given location...");
		return carRepo.findBestCab(latitude, longitude, ApplicationConstants.SEARCH_RADIUS_IN_KILOMETERS);
	}

	@Override
	public BookingDTO completeBooking(String cabDriverNumber) {
		BookingEntity activeBooking = bookingRepo.findByCarEntityDrivenByMobileNumberAndState(cabDriverNumber,
				BookingState.ACTIVE.toString());

		if (activeBooking == null) {
			LOGGER.info(String.format("Cab Driver %s does not have an active booking...", cabDriverNumber));
			throw new BookingServiceException(ExceptionConstants.NO_ACTIVE_BOOKING_MESSAGE);
		}

		activeBooking.setState(BookingState.COMPLETED.toString());
		activeBooking.getCarEntity().setCarStatus(CarStatus.AVAILABLE.toString());

		BookingEntity completedBooking = bookingRepo.save(activeBooking);

		return bookingMapper.mapToDTO(completedBooking);
	}

}
