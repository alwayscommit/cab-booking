package com.assignment.cab_booking.dto;

import com.assignment.cab_booking.entity.CarStatus;

public class DriverDTO {

	private String driverId;
	private String driverName;
	private String carName;
	private CarStatus carStatus;
	private String carNumber;
	private String driverNumber;
	private String latitude;
	private String longitude;

	public DriverDTO() {
	}

	public DriverDTO(String driverName, String carName, CarStatus carStatus, String carNumber, String driverNumber,
			String latitude, String longitude) {
		this.driverName = driverName;
		this.carName = carName;
		this.carStatus = carStatus;
		this.carNumber = carNumber;
		this.driverNumber = driverNumber;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public String getDriverId() {
		return driverId;
	}

	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
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

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public String getDriverNumber() {
		return driverNumber;
	}

	public void setDriverNumber(String driverNumber) {
		this.driverNumber = driverNumber;
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
		return "DriverDTO [driverId=" + driverId + ", driverName=" + driverName + ", carName=" + carName
				+ ", carStatus=" + carStatus + ", carNumber=" + carNumber + ", driverNumber=" + driverNumber
				+ ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}

}
