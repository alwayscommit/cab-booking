package com.assignment.cab_booking.view;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import com.assignment.cab_booking.constants.EntityConstants;

@Entity
@Immutable
@Table(name = EntityConstants.VIEW_CUSTOMER_BOOKING_HISTORY)
public class CustomerBookingHistory {

	@Id
	@Column(name = EntityConstants.VIEW_REFERENCE_NO)
	private String referenceNo;
	@Column(name = EntityConstants.VIEW_CUSTOMER_USER_ID)
	private String customerUserId;
	@Column(name = EntityConstants.VIEW_CAR_NAME)
	private String carName;
	@Column(name = EntityConstants.VIEW_CAR_NUMBER)
	private String carNumber;
	@Column(name = EntityConstants.VIEW_DRIVER_NAME)
	private String driverName;
	@Column(name = EntityConstants.VIEW_DRIVER_NUMBER)
	private String driverNumber;
	@Column(name=EntityConstants.VIEW_START_ADDRESS)
	private String startLocation;
	@Column(name=EntityConstants.VIEW_DESTINATION_ADDRESS)
	private String endLocation;
	@Column(name=EntityConstants.VIEW_NO_OF_PASSENGERS)
	private String numberOfPassengers;
	@Column(name=EntityConstants.VIEW_BOOKING_TIME)
	private String bookingDate;

	public CustomerBookingHistory() {}
	
	public CustomerBookingHistory(String carName, String carNumber, String driverName, String driverNumber,
			String startLocation, String endLocation, String numberOfPassengers, String bookingDate) {
		this.carName = carName;
		this.carNumber = carNumber;
		this.driverName = driverName;
		this.driverNumber = driverNumber;
		this.startLocation = startLocation;
		this.endLocation = endLocation;
		this.numberOfPassengers = numberOfPassengers;
		this.bookingDate = bookingDate;
	}

	public String getReferenceNo() {
		return referenceNo;
	}

	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
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

	public String getStartLocation() {
		return startLocation;
	}

	public void setStartLocation(String startLocation) {
		this.startLocation = startLocation;
	}

	public String getEndLocation() {
		return endLocation;
	}

	public void setEndLocation(String endLocation) {
		this.endLocation = endLocation;
	}

	public String getNumberOfPassengers() {
		return numberOfPassengers;
	}

	public void setNumberOfPassengers(String numberOfPassengers) {
		this.numberOfPassengers = numberOfPassengers;
	}

	public String getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(String bookingDate) {
		this.bookingDate = bookingDate;
	}

}
