package com.assignment.cab_booking.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.assignment.cab_booking.constants.ValidationConstants;

public class CabDriverRequest {

	@NotBlank
	@Size(min=10, max=10, message=ValidationConstants.MOBILE_NUMBER_INVALID_MESSAGE)
	private String mobileNumber;
	@NotBlank
	private String password;
	@NotBlank
	private String firstName;
	@NotBlank
	private String lastName;
	@NotBlank
	private String carName;
	@NotBlank
	@Size(min=6, max=10)
	private String carNumber;

	public CabDriverRequest(String mobileNumber, String password, String firstName, String lastName, String carName,
			String carNumber) {
		this.mobileNumber = mobileNumber;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.carName = carName;
		this.carNumber = carNumber;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

}
