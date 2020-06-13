package com.assignment.cab_booking.model.response;

import java.util.Date;

public class DriverRest {

	private String mobileNumber;
	private String firstName;
	private String lastName;
	private String accountType;
	private String carName;
	private String carNumber;
	private String carStatus;
	private Date createdOn;
	
	public DriverRest() {}
	
	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getCarStatus() {
		return carStatus;
	}



	public void setCarStatus(String carStatus) {
		this.carStatus = carStatus;
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

	@Override
	public String toString() {
		return "DriverRest [mobileNumber=" + mobileNumber + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", accountType=" + accountType + ", carName=" + carName + ", carNumber=" + carNumber + ", carStatus="
				+ carStatus + ", createdOn=" + createdOn + "]";
	}

}
