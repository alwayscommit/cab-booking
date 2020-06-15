package com.assignment.cab_booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.assignment.cab_booking.entity.BookingEntity;
import com.assignment.cab_booking.model.BookingState;
import com.assignment.cab_booking.view.CabStatus;

@Repository
public interface BookingRepository extends CrudRepository<BookingEntity, Long> {

	@Query(value = "Select new com.assignment.cab_booking.view.CabStatus(concat(customer.firstName,' ',customer.lastName), customer.mobileNumber, concat(car.drivenBy.firstName,' ',car.drivenBy.lastName), car.drivenBy.mobileNumber, car.carStatus, car.latitude, car.longitude ) "
			+ "FROM CarEntity car " + "LEFT OUTER JOIN car.booking booking " + "ON booking.state='ACTIVE' "
			+ "LEFT OUTER JOIN booking.customerDetails customer")
	List<CabStatus> findCabDetails();

	BookingEntity findByCustomerDetailsMobileNumberAndState(String customerNumber, String string);

}
