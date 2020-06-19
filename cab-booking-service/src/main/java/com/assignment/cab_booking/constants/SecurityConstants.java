package com.assignment.cab_booking.constants;

import com.assignment.cab_booking.AppProperties;
import com.assignment.cab_booking.SpringAppContext;

public class SecurityConstants {

	public static final long EXPIRATION_TIME = 864000000L; // 10d in milliseconds
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final String USER_ID = "UserId";
	public static final String APPLICATION_PROPERTIES = "appProperties";

	public static String getTokenSecret() {
		AppProperties appProperties = (AppProperties) SpringAppContext.getBean(APPLICATION_PROPERTIES);
		return appProperties.getTokenSecret();
	}

}
