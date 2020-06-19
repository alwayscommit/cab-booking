package com.assignment.cab_booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class AppProperties {

	private static final String TOKEN_SECRET_PROPERTY_KEY = "tokenSecret";

	@Autowired
	private Environment env;

	public String getTokenSecret() {
		return env.getProperty(TOKEN_SECRET_PROPERTY_KEY);
	}

}
