package com.assignment.cab_booking.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.assignment.cab_booking.constants.EntityConstants;
import com.assignment.cab_booking.constants.ValidationConstants;

@Entity
@Table(name = EntityConstants.BOOKING)
public class BookingEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = EntityConstants.ID)
	private Long id;

	@NotNull(message = ValidationConstants.START_LOCATION_MESSAGE)
	@Column(name = EntityConstants.START_LOCATION)
	private Location startLocation;

	@NotNull(message=ValidationConstants.DROP_LOCATION_MESSAGE)
	@Column(name = EntityConstants.DROP_LOCATION)
	private Location dropLocation;

	@NotNull(message=ValidationConstants.CAR_DETAILS_MESSAGE)
	@Column(name = EntityConstants.CAR_DETAILS)
	private CarEntity carDetails;

	@NotNull(message = ValidationConstants.CUSTOMER_DETAILS_MESSAGE)
	@Column(name = EntityConstants.CUSTOMER_DETAILS)
	private UserAccountEntity customerDetails;

	@NotNull(message = ValidationConstants.BOOKING_STATE_MESSAGE)
	@Column(name = EntityConstants.STATE)
	private BookingState state;

	@NotNull(message = ValidationConstants.BOOKING_TIME_MESSAGE)
	@Column(name = EntityConstants.BOOKING_TIME)
	private Date bookingTime;

	@NotNull(message = ValidationConstants.BOOKING_COST_MESSAGE)
	@Column(name = EntityConstants.COST)
	private Integer cost;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Location getStartLocation() {
		return startLocation;
	}

	public void setStartLocation(Location startLocation) {
		this.startLocation = startLocation;
	}

	public Location getDropLocation() {
		return dropLocation;
	}

	public void setDropLocation(Location dropLocation) {
		this.dropLocation = dropLocation;
	}

	public CarEntity getCarDetails() {
		return carDetails;
	}

	public void setCarDetails(CarEntity carDetails) {
		this.carDetails = carDetails;
	}

	public UserAccountEntity getCustomerDetails() {
		return customerDetails;
	}

	public void setCustomerDetails(UserAccountEntity customerDetails) {
		this.customerDetails = customerDetails;
	}

	public BookingState getState() {
		return state;
	}

	public void setState(BookingState state) {
		this.state = state;
	}

	public Date getBookingTime() {
		return bookingTime;
	}

	public void setBookingTime(Date bookingTime) {
		this.bookingTime = bookingTime;
	}

	public Integer getCost() {
		return cost;
	}

	public void setCost(Integer cost) {
		this.cost = cost;
	}

}
