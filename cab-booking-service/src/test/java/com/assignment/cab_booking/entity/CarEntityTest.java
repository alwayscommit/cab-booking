package com.assignment.cab_booking.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.assignment.cab_booking.constants.ValidationConstants;
import com.assignment.cab_booking.model.CarStatus;

class CarEntityTest {

	private static Validator validator;

	private CarEntity car;

	@BeforeAll
	public static void setup() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@BeforeEach
	void contextLoads() {
		car = new CarEntity();
		car.setCarName("Ford W");
		car.setCarNumber("MH04XX1123");
		car.setCarStatus(CarStatus.AVAILABLE);
		car.setDrivenBy(new UserAccountEntity());
	}

	@Test
	public void validLocationTest() {
		assertEquals(false, validator.validate(this.car).iterator().hasNext());
	}
	
	@Test
	public void invalidCarNameTest() {

		car.setCarName("");
		assertEquals(ValidationConstants.CAR_NAME_MESSAGE,
				validator.validate(this.car).iterator().next().getMessage());
		
		car.setCarName(null);
		assertEquals(ValidationConstants.CAR_NAME_MESSAGE,
				validator.validate(this.car).iterator().next().getMessage());
		
	}
	
	@Test
	public void invalidCarNumberTest() {

		car.setCarNumber("");
		assertEquals(ValidationConstants.CAR_NUMBER_MESSAGE,
				validator.validate(this.car).iterator().next().getMessage());
		
		car.setCarNumber(null);
		assertEquals(ValidationConstants.CAR_NUMBER_MESSAGE,
				validator.validate(this.car).iterator().next().getMessage());
		
	}

	@Test
	public void invalidCarNumberMaxLengthTest() {

		car.setCarNumber("MH02SS11111");
		assertEquals(ValidationConstants.CAR_NUMBER_MAX_LENGTH_MESSAGE,
				validator.validate(this.car).iterator().next().getMessage());
		
	}
	
	@Test
	public void invalidCarStatusTest() {

		car.setCarStatus(null);
		assertEquals(ValidationConstants.CAR_STATUS_MESSAGE,
				validator.validate(this.car).iterator().next().getMessage());
		
	}
	
}
