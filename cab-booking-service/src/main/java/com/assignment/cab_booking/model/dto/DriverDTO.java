package com.assignment.cab_booking.model.dto;

import java.util.Date;

import com.assignment.cab_booking.entity.AccountType;
import com.assignment.cab_booking.entity.CarStatus;

public class DriverDTO {

	private Long id;
	private String mobileNumber;
	private String firstName;
	private String lastName;
	private String password;
	private AccountType accountType;
	private String carName;
	private CarStatus carStatus;
	private String carNumber;
	private String latitude;
	private String longitude;
	private Date createdOn;
	
	public DriverDTO(Long id, String mobileNumber, String firstName, String lastName, String password,
			AccountType accountType, String carName, CarStatus carStatus, String carNumber, String latitude,
			String longitude, Date createdOn) {
		this.mobileNumber = mobileNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.accountType = accountType;
		this.carName = carName;
		this.carStatus = carStatus;
		this.carNumber = carNumber;
		this.latitude = latitude;
		this.longitude = longitude;
		this.createdOn = createdOn;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public DriverDTO() {
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public CarStatus getCarStatus() {
		return carStatus;
	}

	public void setCarStatus(CarStatus carStatus) {
		this.carStatus = carStatus;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "DriverDTO [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", password=" + password
				+ ", carName=" + carName + ", carStatus=" + carStatus + ", carNumber=" + carNumber + ", mobileNumber="
				+ mobileNumber + ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}

}
