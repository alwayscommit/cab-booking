package com.assignment.cab_booking.constants;

public interface ExceptionConstants {
	
	public static final String DB_CONSTRAINT_MESSAGE = "Database constraint violated";
	
	public static final String SIMULTANEOUS_BOOKING_MESSAGE = "Simultaneous bookings are not allowed!";
	public static final String CUSTOMER_NOT_REGISTERED_MESSAGE = "You are not a registered customer!";
	public static final String NO_CABS_AVAILABLE_MESSAGE = "No cabs available! Please try again later.";
	
	public static final String CAB_DRIVER_CREATION_MESSAGE = "Unable to create driver";
}