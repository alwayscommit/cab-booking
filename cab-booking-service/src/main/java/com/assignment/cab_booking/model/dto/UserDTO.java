package com.assignment.cab_booking.model.dto;

import java.util.Date;

import com.assignment.cab_booking.model.AccountType;

public class UserDTO {

	private String userId;
	private String mobileNumber;
	private String firstName;
	private String lastName;
	private String password;
	private AccountType accountType;
	private Date createdOn;

	public UserDTO() {
	}

	public UserDTO(String userId, String mobileNumber, String firstName, String lastName, String password,
			AccountType accountType, Double latitude, Double longitude, Date createdOn) {
		this.userId = userId;
		this.mobileNumber = mobileNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.accountType = accountType;
		this.createdOn = createdOn;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	@Override
	public String toString() {
		return "CustomerDTO [userId=" + userId + ", mobileNumber=" + mobileNumber + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", password=" + password + ", accountType=" + accountType + ", latitude="
				 + ", createdOn=" + createdOn + "]";
	}

}
