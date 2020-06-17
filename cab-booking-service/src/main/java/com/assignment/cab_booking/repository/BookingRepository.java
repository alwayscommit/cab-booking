package com.assignment.cab_booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.assignment.cab_booking.entity.BookingEntity;
import com.assignment.cab_booking.model.BookingState;
import com.assignment.cab_booking.view.CabBookingStatus;

@Repository
public interface BookingRepository extends CrudRepository<BookingEntity, Long> {

	@Query(value = "Select new com.assignment.cab_booking.view.CabBookingStatus(concat(customer.firstName,' ',customer.lastName), customer.mobileNumber, concat(car.drivenBy.firstName,' ',car.drivenBy.lastName), car.drivenBy.mobileNumber, car.carStatus, car.latitude, car.longitude ) "
			+ "FROM CarDriverEntity car " + "LEFT OUTER JOIN car.booking booking " + "ON booking.state='ACTIVE' "
			+ "LEFT OUTER JOIN booking.customerDetails customer")
	List<CabBookingStatus> findCabDetails();

	BookingEntity findByReferenceNo(String cabDriverNumber);

	BookingEntity findByCustomerDetailsMobileNumberAndState(String customerNumber, BookingState active);

}
