package com.assignment.cab_booking.model.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.assignment.cab_booking.constants.ApplicationConstants;

public class BookingRequest {

	@NotNull
	@Max(ApplicationConstants.MAX_LATITUDE_VALUE)
	@Min(ApplicationConstants.MIN_LATITUDE_VALUE)
	private Double startLatitude;

	@NotNull
	@Max(ApplicationConstants.MAX_LONGITUDE_VALUE)
	@Min(ApplicationConstants.MIN_LONGITUDE_VALUE)
	private Double startLongitude;

	@NotNull
	@Max(ApplicationConstants.MAX_LATITUDE_VALUE)
	@Min(ApplicationConstants.MIN_LATITUDE_VALUE)
	private Double endLatitude;

	@NotNull
	@Max(ApplicationConstants.MAX_LONGITUDE_VALUE)
	@Min(ApplicationConstants.MIN_LONGITUDE_VALUE)
	private Double endLongitude;

	@NotBlank
	private String customerUserId;
	
	@NotBlank
	@Max(4)
	private String numberOfPassengers;
	@NotBlank
	private String startAddress;
	@NotBlank
	private String destinationAddress;

	public String getStartAddress() {
		return startAddress;
	}

	public void setStartAddress(String startAddress) {
		this.startAddress = startAddress;
	}

	public String getDestinationAddress() {
		return destinationAddress;
	}

	public void setDestinationAddress(String destinationAddress) {
		this.destinationAddress = destinationAddress;
	}

	public Double getStartLatitude() {
		return startLatitude;
	}

	public void setStartLatitude(Double startLatitude) {
		this.startLatitude = startLatitude;
	}

	public Double getStartLongitude() {
		return startLongitude;
	}

	public void setStartLongitude(Double startLongitude) {
		this.startLongitude = startLongitude;
	}

	public Double getEndLatitude() {
		return endLatitude;
	}

	public void setEndLatitude(Double endLatitude) {
		this.endLatitude = endLatitude;
	}

	public Double getEndLongitude() {
		return endLongitude;
	}

	public void setEndLongitude(Double endLongitude) {
		this.endLongitude = endLongitude;
	}

	public String getCustomerUserId() {
		return customerUserId;
	}

	public void setCustomerUserId(String customerUserId) {
		this.customerUserId = customerUserId;
	}

	public String getNumberOfPassengers() {
		return numberOfPassengers;
	}

	public void setNumberOfPassengers(String numberOfPassengers) {
		this.numberOfPassengers = numberOfPassengers;
	}

}
