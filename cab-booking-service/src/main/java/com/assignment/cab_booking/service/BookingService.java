package com.assignment.cab_booking.service;

import java.util.List;

import com.assignment.cab_booking.entity.BookingEntity;
import com.assignment.cab_booking.entity.CarEntity;
import com.assignment.cab_booking.model.dto.BookingDTO;
import com.assignment.cab_booking.view.CabSummary;

public interface BookingService {

	BookingDTO bookCab(BookingDTO bookingDTO);

	List<CabSummary> findAllCabs();
	
}
