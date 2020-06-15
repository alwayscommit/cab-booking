package com.assignment.cab_booking.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class CabStatus {

	private String driverName;
	private String driverNumber;
	private String customerName;
	private String customerNumber;
	private String carStatus;
	private Double carLatitude;
	private Double carLongitude;

	public CabStatus(String customerName, String customerNumber, String driverName, String driverNumber, 
			String carStatus, Double carLatitude, Double carLongitude) {
		this.driverName = driverName;
		this.driverNumber = driverNumber;
		this.customerName = customerName;
		this.customerNumber = customerNumber;
		this.carStatus = carStatus;
		this.carLatitude = carLatitude;
		this.carLongitude = carLongitude;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getDriverNumber() {
		return driverNumber;
	}

	public void setDriverNumber(String driverNumber) {
		this.driverNumber = driverNumber;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	public String getCarStatus() {
		return carStatus;
	}

	public void setCarStatus(String carStatus) {
		this.carStatus = carStatus;
	}

	public Double getCarLatitude() {
		return carLatitude;
	}

	public void setCarLatitude(Double carLatitude) {
		this.carLatitude = carLatitude;
	}

	public Double getCarLongitude() {
		return carLongitude;
	}

	public void setCarLongitude(Double carLongitude) {
		this.carLongitude = carLongitude;
	}

}
