package com.assignment.cab_booking.utils;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class ApplicationUtils {

	private final Random RANDOM = new SecureRandom();
	private String ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public String generateBookingReference(int length) {
		return generateString(length);
	}
	
	public String generateUserId(int length) {
		return generateString(length);
	}
	
	public String generateCarId(int length) {
		return generateString(length);
	}

	private String generateString(int length) {
		StringBuilder string = new StringBuilder();

		for (int i = 0; i < length; i++) {
			string.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
		}

		return new String(string);
	}

}
