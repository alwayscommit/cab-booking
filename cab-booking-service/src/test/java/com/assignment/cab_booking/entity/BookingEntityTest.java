package com.assignment.cab_booking.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;
import java.util.Date;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.assignment.cab_booking.constants.ValidationConstants;
import com.assignment.cab_booking.model.BookingState;

class BookingEntityTest {

	private static Validator validator;

	private BookingEntity bookingEntity;

	@BeforeAll
	public static void setup() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@BeforeEach
	void contextLoads() {
		bookingEntity = new BookingEntity();
		bookingEntity.setBookingTime(Date.from(Instant.now()));
		bookingEntity.setCarEntity(new CarEntity());
		bookingEntity.setCost(100);
		bookingEntity.setCustomerDetails(new UserAccountEntity());
		bookingEntity.setStartLocation(new Location());
		bookingEntity.setDropLocation(new Location());
		bookingEntity.setState(BookingState.ACTIVE);
	}

	@Test
	public void validBookingEntityTest() {
		assertEquals(false, validator.validate(this.bookingEntity).iterator().hasNext());
	}
	
	@Test
	public void invalidBookingTimeTest() {

		bookingEntity.setBookingTime(null);
		assertEquals(ValidationConstants.BOOKING_TIME_MESSAGE,
				validator.validate(this.bookingEntity).iterator().next().getMessage());
		
	}
	
	@Test
	public void invalidBookingCostTest() {

		bookingEntity.setCost(null);
		assertEquals(ValidationConstants.BOOKING_COST_MESSAGE,
				validator.validate(this.bookingEntity).iterator().next().getMessage());
		
	}
	
	@Test
	public void invalidCarDetailsTest() {

		bookingEntity.setCarEntity(null);
		assertEquals(ValidationConstants.CAR_DETAILS_MESSAGE,
				validator.validate(this.bookingEntity).iterator().next().getMessage());
		
	}
	
	@Test
	public void invalidCustomerDetailsTest() {

		bookingEntity.setCustomerDetails(null);
		assertEquals(ValidationConstants.CUSTOMER_DETAILS_MESSAGE,
				validator.validate(this.bookingEntity).iterator().next().getMessage());
		
	}
	
	@Test
	public void invalidStartLocationTest() {

		bookingEntity.setStartLocation(null);
		assertEquals(ValidationConstants.START_LOCATION_MESSAGE,
				validator.validate(this.bookingEntity).iterator().next().getMessage());
		
	}
	
	@Test
	public void invalidDropLocationTest() {

		bookingEntity.setDropLocation(null);
		assertEquals(ValidationConstants.DROP_LOCATION_MESSAGE,
				validator.validate(this.bookingEntity).iterator().next().getMessage());
		
	}
	
	@Test
	public void invalidBookingState() {

		bookingEntity.setState(null);
		assertEquals(ValidationConstants.BOOKING_STATE_MESSAGE,
				validator.validate(this.bookingEntity).iterator().next().getMessage());
		
	}
}
