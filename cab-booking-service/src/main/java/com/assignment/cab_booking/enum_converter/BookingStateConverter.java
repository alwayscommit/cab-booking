package com.assignment.cab_booking.enum_converter;

import org.springframework.core.convert.converter.Converter;

import com.assignment.cab_booking.constants.ExceptionConstants;
import com.assignment.cab_booking.exception.BookingServiceException;
import com.assignment.cab_booking.model.BookingStateRequest;

public class BookingStateConverter implements Converter<String, BookingStateRequest> {

	@Override
	public BookingStateRequest convert(String source) {
		try {
			return BookingStateRequest.valueOf(source.toUpperCase());
		} catch (IllegalArgumentException e) {
			throw new BookingServiceException(ExceptionConstants.BAD_ENUM_BOOKING_STATUS_REQUEST);
		}
	}
}
