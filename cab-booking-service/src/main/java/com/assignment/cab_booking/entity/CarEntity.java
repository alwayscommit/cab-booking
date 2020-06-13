package com.assignment.cab_booking.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.assignment.cab_booking.constants.EntityConstants;
import com.assignment.cab_booking.constants.ValidationConstants;

@Entity
@Table(name = EntityConstants.CAR)
public class CarEntity {

	@Id
	@GeneratedValue
	@Column(name = EntityConstants.ID)
	private long id;

	@NotEmpty(message=ValidationConstants.CAR_NAME_MESSAGE)
	@Column(name = EntityConstants.CAR_NAME)
	private String carName;

	@NotEmpty(message=ValidationConstants.CAR_NUMBER_MESSAGE)
	@Size(max = 10, message=ValidationConstants.CAR_NUMBER_MAX_LENGTH_MESSAGE)
	@Column(name = EntityConstants.CAR_NUMBER, unique=true)
	private String carNumber;

	@NotNull(message=ValidationConstants.CAR_STATUS_MESSAGE)
	@Column(name = EntityConstants.CAR_STATUS)
	private CarStatus carStatus;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="DRIVEN_BY_ACCOUNT")
	private UserAccountEntity drivenBy;

	@OneToOne
	@JoinColumn(name = EntityConstants.LOCATION)
	private Location location;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public CarStatus getCarStatus() {
		return carStatus;
	}

	public void setCarStatus(CarStatus carStatus) {
		this.carStatus = carStatus;
	}

	public UserAccountEntity getDrivenBy() {
		return drivenBy;
	}

	public void setDrivenBy(UserAccountEntity drivenBy) {
		this.drivenBy = drivenBy;
	}

}
