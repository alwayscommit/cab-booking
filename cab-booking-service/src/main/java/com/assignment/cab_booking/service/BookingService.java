package com.assignment.cab_booking.service;

import java.util.List;

import com.assignment.cab_booking.model.dto.BookingDTO;
import com.assignment.cab_booking.view.CabBookingStatus;

public interface BookingService {

	BookingDTO bookCab(BookingDTO bookingDTO);

	List<CabBookingStatus> findAllCabBookingStatus();

	BookingDTO completeBooking(String cabDriverNumber);
	
}
