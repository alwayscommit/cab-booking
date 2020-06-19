package com.assignment.cab_booking.service;

import java.util.List;

import com.assignment.cab_booking.model.BookingState;
import com.assignment.cab_booking.model.dto.BookingDTO;
import com.assignment.cab_booking.view.CabBookingStatus;
import com.assignment.cab_booking.view.CustomerBookingHistory;

public interface BookingService {

	BookingDTO bookCab(BookingDTO bookingDTO);

	List<CabBookingStatus> findAllCabBookingStatus();

	BookingDTO updateBooking(String referenceNo, BookingState state);

	List<CustomerBookingHistory> getCustomerBookingHistory(String userId);
	
}
