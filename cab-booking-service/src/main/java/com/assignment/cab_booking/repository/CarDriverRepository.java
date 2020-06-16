package com.assignment.cab_booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.assignment.cab_booking.entity.CarDriverEntity;

@Repository
public interface CarDriverRepository extends CrudRepository<CarDriverEntity, Long> {

	List<CarDriverEntity> findAllByCarStatus(String carStatus);

	// Spherical Law of Cosines Formula
	// TODO Native Query in-place, beats the point of Hibernate in a way.
	// Need to figure out a better way.
	@Query(value = "SELECT *, ( 6371 * acos ( cos ( radians(?1) ) * cos( radians( latitude ) ) * cos( radians( longitude ) - radians(?2) ) + sin ( radians(?1) ) * sin( radians( latitude ) ) ) ) AS distance FROM  car HAVING distance < ?3 AND car_status='AVAILABLE' ORDER BY distance LIMIT 1;", nativeQuery = true)
	CarDriverEntity findBestCab(Double latitude, Double longitude, Integer kilometers);

	@Query(value = "SELECT *, ( 6371 * acos ( cos ( radians(?1) ) * cos( radians( latitude ) ) * cos( radians( longitude ) - radians(?2) ) + sin ( radians(?1) ) * sin( radians( latitude ) ) ) ) AS distance FROM car HAVING distance < ?3 AND car_status='AVAILABLE' LIMIT 10;", nativeQuery = true)
	List<CarDriverEntity> findAvailableCabs(Double latitude, Double longitude, Integer kilometers);

	CarDriverEntity findByDrivenByMobileNumber(String driverNumber);

}
