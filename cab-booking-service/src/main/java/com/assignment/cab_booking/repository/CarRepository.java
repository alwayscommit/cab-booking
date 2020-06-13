package com.assignment.cab_booking.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.assignment.cab_booking.entity.CarEntity;
import com.assignment.cab_booking.model.CarStatus;

@Repository
public interface CarRepository extends CrudRepository<CarEntity, Long>{

	List<CarEntity> findAllByCarStatus(CarStatus available);

}
