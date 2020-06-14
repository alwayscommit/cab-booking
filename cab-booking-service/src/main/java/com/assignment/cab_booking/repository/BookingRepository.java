package com.assignment.cab_booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.assignment.cab_booking.entity.BookingEntity;

@Repository
public interface BookingRepository extends CrudRepository<BookingEntity, Long> {

	@Query(value = "Select booking.referenceNo, carEntity.drivenBy.firstName, carEntity.carStatus from BookingEntity booking, CarEntity carEntity where ")
	List<BookingEntity> findAllCabs();

}
