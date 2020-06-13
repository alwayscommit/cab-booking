package com.assignment.cab_booking.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.assignment.cab_booking.entity.CarEntity;

@Repository
public interface CarRepository extends CrudRepository<CarEntity, Long>{

}
