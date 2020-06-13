package com.assignment.cab_booking.model.response;

import java.util.Date;

public class CustomerRest {

	private String mobileNumber;
	private String firstName;
	private String lastName;
	private String accountType;
	private Date createdOn;

	public CustomerRest() {}
	
	public CustomerRest(String mobileNumber, String firstName, String lastName, String accountType, Date createdOn) {
		this.mobileNumber = mobileNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.accountType = accountType;
		this.createdOn = createdOn;
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

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

}
