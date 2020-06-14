package com.assignment.cab_booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.assignment.cab_booking.entity.BookingEntity;
import com.assignment.cab_booking.view.CabSummary;

@Repository
public interface BookingRepository extends CrudRepository<BookingEntity, Long> {

	@Query(value = "Select new com.assignment.cab_booking.view.CabSummary(customer.firstName, customer.mobileNumber, car.drivenBy.firstName, car.drivenBy.mobileNumber, car.carStatus ) "
			+ "FROM CarEntity car " + "LEFT OUTER JOIN car.booking booking " + "ON booking.state='ACTIVE' "
			+ "LEFT OUTER JOIN booking.customerDetails customer")
	List<CabSummary> findCabDetails();

}
