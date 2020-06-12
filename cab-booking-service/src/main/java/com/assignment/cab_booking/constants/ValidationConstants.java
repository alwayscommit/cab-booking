package com.assignment.cab_booking.constants;

public interface ValidationConstants {

	// UserAccount
	public static final String FIRST_NAME_INVALID_MESSAGE = "first name cannot be empty";
	public static final String LAST_NAME_INVALID_MESSAGE = "last name cannot be empty";
	public static final String PASSWORD_INVALID_MESSAGE = "password cannot be empty";
	public static final String MOBILE_NUMBER_INVALID_MESSAGE = "Mobile number must be numeric & 10 digits long";
	public static final String ACCOUNT_TYPE_INVALID_MESSAGE = "account type cannot be empty";

	// Location
	public static final String LATITUDE_RANGE_MESSAGE = "Latitude max/min ranged is 90/-90";
	public static final String LONGITUDE_RANGE_MESSAGE = "Longitude max/min ranged is 180/-180";

	// Car
	public static final String CAR_NAME_MESSAGE = "car name cannot be empty";
	public static final String CAR_NUMBER_MESSAGE = "car number cannot be empty";
	public static final String CAR_STATUS_MESSAGE = "car status cannot be null";
	public static final String CAR_NUMBER_MAX_LENGTH_MESSAGE = "car number cannot be more than 10 digits long";

	//Booking
	public static final String START_LOCATION_MESSAGE = "start location cannot be null";
	public static final String DROP_LOCATION_MESSAGE = "drop location cannot be null";
	public static final String CAR_DETAILS_MESSAGE = "car details cannot be null";
	public static final String CUSTOMER_DETAILS_MESSAGE = "customer details cannot be null";
	public static final String BOOKING_STATE_MESSAGE = "booking state cannot be null";
	public static final String BOOKING_TIME_MESSAGE = "booking time cannot be null";
	public static final String BOOKING_COST_MESSAGE = "booking cost cannot be null";
	
}
