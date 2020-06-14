package com.assignment.cab_booking.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.assignment.cab_booking.constants.EntityConstants;
import com.assignment.cab_booking.constants.ValidationConstants;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = EntityConstants.CAR)
public class CarEntity {
	
	@Id
	@GeneratedValue
	@Column(name = EntityConstants.ID)
	private Long carId;

	@NotEmpty(message=ValidationConstants.CAR_NAME_MESSAGE)
	@Column(name = EntityConstants.CAR_NAME)
	private String carName;

	@NotEmpty(message=ValidationConstants.CAR_NUMBER_MESSAGE)
	@Size(max = 10, message=ValidationConstants.CAR_NUMBER_MAX_LENGTH_MESSAGE)
	@Column(name = EntityConstants.CAR_NUMBER, unique=true)
	private String carNumber;

	@NotNull(message=ValidationConstants.CAR_STATUS_MESSAGE)
	@Column(name = EntityConstants.CAR_STATUS)
	private String carStatus;

	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
	@JoinColumn(name="DRIVEN_BY_ACCOUNT")
	private UserAccountEntity drivenBy;
	
	@OneToMany(mappedBy="carEntity")
	@JsonIgnoreProperties("carEntity")
	private List<BookingEntity> booking;
	
	@Column(name = EntityConstants.LATITUDE)
	private Double latitude;
	
	@Column(name = EntityConstants.LONGITUDE)
	private Double longitude;
	
	public List<BookingEntity> getBooking() {
		return booking;
	}

	public void setBooking(List<BookingEntity> booking) {
		this.booking = booking;
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

	public Long getCarId() {
		return carId;
	}

	public void setCarId(Long carId) {
		this.carId = carId;
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public String getCarStatus() {
		return carStatus;
	}

	public void setCarStatus(String carStatus) {
		this.carStatus = carStatus;
	}

	public UserAccountEntity getDrivenBy() {
		return drivenBy;
	}

	public void setDrivenBy(UserAccountEntity drivenBy) {
		this.drivenBy = drivenBy;
	}
	
}
