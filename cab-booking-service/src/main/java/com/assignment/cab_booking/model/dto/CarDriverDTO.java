package com.assignment.cab_booking.model.dto;

import java.util.Date;

import com.assignment.cab_booking.model.AccountType;
import com.assignment.cab_booking.model.CarStatus;

public class CarDriverDTO {

	private Long userId;//Id from car table
	private String mobileNumber;
	private String firstName;
	private String lastName;
	private String password;
	private AccountType accountType;
	private Long carId;
	private String carName;
	private CarStatus carStatus;
	private String carNumber;
	private Double latitude;
	private Double longitude;
	private Date createdOn;
	
	public CarDriverDTO(String mobileNumber, String firstName, String lastName, String password,
			AccountType accountType, String carName, CarStatus carStatus, String carNumber, Double latitude,
			Double longitude, Date createdOn) {
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

	public CarDriverDTO() {
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

	public CarStatus getCarStatus() {
		return carStatus;
	}

	public void setCarStatus(String carStatus) {
		this.carStatus = CarStatus.valueOf(carStatus);
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

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getCarId() {
		return carId;
	}

	public void setCarId(Long carId) {
		this.carId = carId;
	}

	@Override
	public String toString() {
		return "DriverDTO [userId=" + userId + ", mobileNumber=" + mobileNumber + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", password=" + password + ", accountType=" + accountType + ", carId="
				+ carId + ", carName=" + carName + ", carStatus=" + carStatus + ", carNumber=" + carNumber
				+ ", latitude=" + latitude + ", longitude=" + longitude + ", createdOn=" + createdOn + "]";
	}

}
