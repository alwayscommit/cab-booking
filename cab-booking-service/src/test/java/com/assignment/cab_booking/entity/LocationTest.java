package com.assignment.cab_booking.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.assignment.cab_booking.constants.ValidationConstants;

class LocationTest {

	private static Validator validator;

	private Location location;

	@BeforeAll
	public static void setup() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@BeforeEach
	void contextLoads() {
		location = new Location();
		location.setLatitude(19.231309);
		location.setLongitude(72.982752);
	}

	@Test
	public void validLocationTest() {
		assertEquals(false, validator.validate(this.location).iterator().hasNext());
	}
	
	@Test
	public void invalidLatitudeLocationTest() {

		location.setLatitude(91d);
		assertEquals(ValidationConstants.LATITUDE_RANGE_MESSAGE,
				validator.validate(this.location).iterator().next().getMessage());
		
		location.setLatitude(-91d);
		assertEquals(ValidationConstants.LATITUDE_RANGE_MESSAGE,
				validator.validate(this.location).iterator().next().getMessage());
		
	}
	
	@Test
	public void invalidLongitudeLocationTest() {

		location.setLongitude(181d);
		assertEquals(ValidationConstants.LONGITUDE_RANGE_MESSAGE,
				validator.validate(this.location).iterator().next().getMessage());
		
		location.setLatitude(-181d);
		assertEquals(ValidationConstants.LONGITUDE_RANGE_MESSAGE,
				validator.validate(this.location).iterator().next().getMessage());
		
	}

}
