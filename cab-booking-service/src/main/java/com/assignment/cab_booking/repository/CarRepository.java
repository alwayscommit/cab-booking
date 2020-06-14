package com.assignment.cab_booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.assignment.cab_booking.entity.BookingEntity;
import com.assignment.cab_booking.entity.CarEntity;

@Repository
public interface CarRepository extends CrudRepository<CarEntity, Long> {

	List<CarEntity> findAllByCarStatus(String carStatus);

	// Enum CarStatus 0 = Available, 1=Busy

	// Spherical Law of Cosines Formula
	// TODO Native Query in-place, beats the point of Hibernate in a way. Need to
	// figure out a better way.
	@Query(value = "SELECT *, ( 6371 * acos ( cos ( radians(?1) ) * cos( radians( latitude ) ) * cos( radians( longitude ) - radians(?2) ) + sin ( radians(?1) ) * sin( radians( latitude ) ) ) ) AS distance FROM car HAVING distance < ?3 AND car_status='AVAILABLE' ORDER BY distance LIMIT 1;", nativeQuery = true)
	CarEntity findAvailableCarWithinRadius(Double latitude, Double longitude, Integer kilometers);

	Iterable<CarEntity> findAllByBookingState(String string);

//	@Query("Select car.drivenBy.firstName, car.drivenBy.mobileNumber, car.carStatus from CarEntity car, BookingEntity booking where car.carId=booking.carEntity.carId")
	@Query("Select customer, "
			+ "car.drivenBy.firstName, car.drivenBy.mobileNumber, "
			+ "car.carStatus " 
			+ "FROM CarEntity car "
			+ "LEFT OUTER JOIN car.booking booking ON booking.state='ACTIVE'"
			+ "LEFT OUTER JOIN booking.customerDetails customer")
	 List<BookingEntity> findCabDetails();

	CarEntity findByDrivenByMobileNumber(String driverNumber);

//	@Query(value = "SELECT c, ( 6371 * acos ( cos ( radians(?1) ) * cos( radians( c.latitude ) ) * cos( radians( c.longitude ) - radians(?2) ) + sin ( radians(?1) ) * sin( radians( c.latitude ) ) ) ) AS distance FROM CarEntity c WHERE distance < 5 ORDER BY distance", nativeQuery = false)
//	List<CarEntity> findCarWithin5Kms(Double latitude, Double longitude);
}
