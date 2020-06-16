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
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.assignment.cab_booking.constants.ValidationConstants;
import com.assignment.cab_booking.model.BookingState;


@RunWith(SpringRunner.class)
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
		bookingEntity.setBookingTime(Date.from(Instant.now()).toString());
		bookingEntity.setCarEntity(new CarDriverEntity());
		bookingEntity.setCustomerDetails(new UserAccountEntity());
		bookingEntity.setStartLatitude(19.209449150428703);
		bookingEntity.setStartLongitude(72.94559519727788);
		bookingEntity.setEndLatitude(19.209449150428703);
		bookingEntity.setEndLongitude(72.94559519727788);
		bookingEntity.setNumberOfPassengers("2");
		bookingEntity.setState(BookingState.ACTIVE.toString());
		bookingEntity.setReferenceNo("abcd1234");
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

		bookingEntity.setStartLatitude(null);
		assertEquals(ValidationConstants.START_LOCATION_MESSAGE,
				validator.validate(this.bookingEntity).iterator().next().getMessage());

	}

	@Test
	public void invalidDropLocationTest() {

		bookingEntity.setStartLongitude(null);
		assertEquals(ValidationConstants.START_LOCATION_MESSAGE,
				validator.validate(this.bookingEntity).iterator().next().getMessage());

	}
	
	@Test
	public void invalidEndLocationLat() {

		bookingEntity.setEndLatitude(null);
		assertEquals(ValidationConstants.DROP_LOCATION_MESSAGE,
				validator.validate(this.bookingEntity).iterator().next().getMessage());

	}

	@Test
	public void invalidEndLocationLong() {

		bookingEntity.setEndLongitude(null);
		assertEquals(ValidationConstants.DROP_LOCATION_MESSAGE,
				validator.validate(this.bookingEntity).iterator().next().getMessage());

	}

	@Test
	public void invalidBookingState() {

		bookingEntity.setState(null);
		assertEquals(ValidationConstants.BOOKING_STATE_MESSAGE,
				validator.validate(this.bookingEntity).iterator().next().getMessage());

	}
	
	@Test
	public void invalidNumberOfPassengers() {

		bookingEntity.setNumberOfPassengers(null);
		assertEquals(ValidationConstants.NUMBER_OF_PASSENGERS_MESSAGE,
				validator.validate(this.bookingEntity).iterator().next().getMessage());

	}
}
