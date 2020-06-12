package com.assignment.cab_booking.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum AccountType {
	
	@JsonProperty("customer")
	CUSTOMER, 
	@JsonProperty("driver")
	DRIVER;

}
