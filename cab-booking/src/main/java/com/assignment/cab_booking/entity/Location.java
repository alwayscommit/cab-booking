package com.assignment.cab_booking.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Range;

import com.assignment.cab_booking.constants.EntityConstants;
import com.assignment.cab_booking.constants.ValidationConstants;

@Entity
@Table(name = EntityConstants.LOCATION)
public class Location {

	private static final long MIN_LATITUDE = -90;
	private static final long MAX_LATITUDE = 90;
	private static final long MIN_LONGITUDE = -180;
	private static final long MAX_LONGITUDE = 180;

	@Id
	@GeneratedValue
	@Column(name = EntityConstants.ID)
	private Long id;

	@Range(max = MAX_LATITUDE, min = Location.MIN_LATITUDE, message=ValidationConstants.LATITUDE_RANGE_MESSAGE)
	@Column(name = EntityConstants.LATITUDE)
	private Double latitude;

	@Range(max = MAX_LONGITUDE, min = MIN_LONGITUDE, message=ValidationConstants.LONGITUDE_RANGE_MESSAGE)
	@Column(name = EntityConstants.LONGITUDE)
	private Double longitude;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

}
