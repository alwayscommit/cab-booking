package com.assignment.cab_booking.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class CabSummary {

	private String driverFirstName;
	private String driverNumber;
	private String customerFirstName;
	private String customerNumber;
	private String carStatus;

	public CabSummary(String customerFirstName, String customerNumber, String driverFirstName,
			 String driverNumber, String carStatus) {
		this.driverFirstName = driverFirstName;
		this.driverNumber = driverNumber;
		this.customerFirstName = customerFirstName;
		this.customerNumber = customerNumber;
		this.carStatus = carStatus;
	}

	public String getDriverFirstName() {
		return driverFirstName;
	}

	public void setDriverFirstName(String driverFirstName) {
		this.driverFirstName = driverFirstName;
	}

	public String getDriverNumber() {
		return driverNumber;
	}

	public void setDriverNumber(String driverNumber) {
		this.driverNumber = driverNumber;
	}

	public String getCustomerFirstName() {
		return customerFirstName;
	}

	public void setCustomerFirstName(String customerFirstName) {
		this.customerFirstName = customerFirstName;
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

}
