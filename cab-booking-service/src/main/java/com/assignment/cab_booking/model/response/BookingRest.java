package com.assignment.cab_booking.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class BookingRest {

	private RideDetails rideDetails;
	private CustomerRest customerDetails;
	private CabDriverRest driverDetails;

	public RideDetails getRideDetails() {
		return rideDetails;
	}

	public void setRideDetails(RideDetails rideDetails) {
		this.rideDetails = rideDetails;
	}

	public CustomerRest getCustomerDetails() {
		return customerDetails;
	}

	public void setCustomerDetails(CustomerRest customerDetails) {
		this.customerDetails = customerDetails;
	}

	public CabDriverRest getDriverDetails() {
		return driverDetails;
	}

	public void setDriverDetails(CabDriverRest driverDetails) {
		this.driverDetails = driverDetails;
	}

}
