package com.assignment.cab_booking.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.assignment.cab_booking.view.CustomerBookingHistory;

@Repository
public interface BookingHistoryRepository extends CrudRepository<CustomerBookingHistory, String>{

	List<CustomerBookingHistory> findAllByCustomerNumber(String customerNumber);

}
