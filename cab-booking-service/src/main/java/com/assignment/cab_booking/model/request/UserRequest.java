package com.assignment.cab_booking.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.assignment.cab_booking.constants.ValidationConstants;

public class UserRequest {

	@NotNull
	@Size(min=10, max=10, message=ValidationConstants.MOBILE_NUMBER_INVALID_MESSAGE)
	private String mobileNumber;
	@NotBlank
	private String password;
	@NotBlank
	private String firstName;
	@NotBlank
	private String lastName;
	
	public UserRequest() {}

	public UserRequest(String mobileNumber, String password, String firstName, String lastName) {
		super();
		this.mobileNumber = mobileNumber;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
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

}
