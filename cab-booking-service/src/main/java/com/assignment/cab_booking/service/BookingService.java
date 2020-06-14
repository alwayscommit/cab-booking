package com.assignment.cab_booking.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.assignment.cab_booking.entity.BookingEntity;
import com.assignment.cab_booking.entity.CarEntity;
import com.assignment.cab_booking.model.dto.BookingDTO;

public interface BookingService {

	BookingDTO bookCab(BookingDTO bookingDTO);

	List<BookingEntity> findAllCabs(Pageable pageable);
	
}
