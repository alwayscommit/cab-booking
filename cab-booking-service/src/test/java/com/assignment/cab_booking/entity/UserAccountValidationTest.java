package com.assignment.cab_booking.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
import com.assignment.cab_booking.model.AccountType;

@RunWith(SpringRunner.class)
class UserAccountValidationTest {

	private static Validator validator;

	private UserAccountEntity userAccount;

	@BeforeAll
	public static void setup() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@BeforeEach
	void contextLoads() {
		userAccount = new UserAccountEntity();
		userAccount.setFirstName("Aakash");
		userAccount.setLastName("Ranglani");
		userAccount.setAccountType(AccountType.CUSTOMER);
		userAccount.setCreatedOn(new Date());
		userAccount.setEncryptedPassword("okbye");
		userAccount.setMobileNumber("7506020591");
	}

	@Test
	public void invalidMobileNumberTest() {
		userAccount.setMobileNumber("abcd");
		assertEquals(ValidationConstants.MOBILE_NUMBER_INVALID_MESSAGE,
				validator.validate(this.userAccount).iterator().next().getMessage());

		userAccount.setMobileNumber("917506500591");
		assertEquals(ValidationConstants.MOBILE_NUMBER_INVALID_MESSAGE,
				validator.validate(this.userAccount).iterator().next().getMessage());
	}

	@Test
	public void invalidFirstNameTest() {
		userAccount.setFirstName(null);
		assertEquals(ValidationConstants.FIRST_NAME_INVALID_MESSAGE,
				validator.validate(this.userAccount).iterator().next().getMessage());

		userAccount.setFirstName("");
		assertEquals(ValidationConstants.FIRST_NAME_INVALID_MESSAGE,
				validator.validate(this.userAccount).iterator().next().getMessage());
	}

	@Test
	public void invalidLastNameTest() {
		userAccount.setLastName(null);
		assertEquals(ValidationConstants.LAST_NAME_INVALID_MESSAGE,
				validator.validate(this.userAccount).iterator().next().getMessage());

		userAccount.setLastName("");
		assertEquals(ValidationConstants.LAST_NAME_INVALID_MESSAGE,
				validator.validate(this.userAccount).iterator().next().getMessage());
	}

	@Test
	public void invalidPasswordTest() {
		userAccount.setEncryptedPassword(null);
		assertEquals(ValidationConstants.PASSWORD_INVALID_MESSAGE,
				validator.validate(this.userAccount).iterator().next().getMessage());

		userAccount.setEncryptedPassword("");
		assertEquals(ValidationConstants.PASSWORD_INVALID_MESSAGE,
				validator.validate(this.userAccount).iterator().next().getMessage());
	}

	@Test
	public void invalidAccountTypeTest() {
		userAccount.setAccountType(null);
		assertEquals(ValidationConstants.ACCOUNT_TYPE_INVALID_MESSAGE,
				validator.validate(this.userAccount).iterator().next().getMessage());
	}

}
