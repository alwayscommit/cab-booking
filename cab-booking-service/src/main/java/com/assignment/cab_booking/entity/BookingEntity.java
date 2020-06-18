package com.assignment.cab_booking.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.assignment.cab_booking.constants.EntityConstants;
import com.assignment.cab_booking.constants.ValidationConstants;
import com.assignment.cab_booking.model.BookingState;

@Entity
@Table(name = EntityConstants.BOOKING)
public class BookingEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = EntityConstants.ID)
	private Long bookingId;

	@NotNull(message = ValidationConstants.BOOKING_REFERENE_MESSAGE)
	@Column(name = EntityConstants.REFERENCE_NO, unique = true)
	private String referenceNo;

	@NotNull(message = ValidationConstants.START_LOCATION_MESSAGE)
	@Column(name = EntityConstants.START_LATITUDE)
	private Double startLatitude;

	@NotNull(message = ValidationConstants.START_LOCATION_MESSAGE)
	@Column(name = EntityConstants.START_LONGITUDE)
	private Double startLongitude;

	@NotNull(message = ValidationConstants.DROP_LOCATION_MESSAGE)
	@Column(name = EntityConstants.END_LATITUDE)
	private Double endLatitude;

	@NotNull(message = ValidationConstants.DROP_LOCATION_MESSAGE)
	@Column(name = EntityConstants.END_LONGITUDE)
	private Double endLongitude;

	@NotNull(message = ValidationConstants.CAR_DETAILS_MESSAGE)
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "CAR_ID")
	private CarDriverEntity carEntity;

	@NotNull(message = ValidationConstants.CUSTOMER_DETAILS_MESSAGE)
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "CUSTOMER_ID")
	private UserAccountEntity customerDetails;

	@Enumerated(EnumType.STRING)
	@NotNull(message = ValidationConstants.BOOKING_STATE_MESSAGE)
	@Column(name = EntityConstants.STATE)
	private BookingState state;

	@NotNull(message = ValidationConstants.BOOKING_TIME_MESSAGE)
	@Column(name = EntityConstants.BOOKING_TIME)
	private Date bookingTime;

	@NotNull(message = ValidationConstants.NUMBER_OF_PASSENGERS_MESSAGE)
	@Column(name = EntityConstants.NUMBER_OF_PASSENGERS)
	private String numberOfPassengers;

	@NotNull(message = ValidationConstants.START_ADDRESS_MESSAGE)
	@Column(name = EntityConstants.START_ADDRESS)
	private String startAddress;

	@NotNull(message = ValidationConstants.DESTINATION_ADDRESS_MESSAGE)
	@Column(name = EntityConstants.DESTINATION_ADDRESS)
	private String destinationAddress;

	public String getStartAddress() {
		return startAddress;
	}

	public void setStartAddress(String startAddress) {
		this.startAddress = startAddress;
	}

	public String getDestinationAddress() {
		return destinationAddress;
	}

	public void setDestinationAddress(String destinationAddress) {
		this.destinationAddress = destinationAddress;
	}

	public Double getStartLatitude() {
		return startLatitude;
	}

	public void setStartLatitude(Double startLatitude) {
		this.startLatitude = startLatitude;
	}

	public Double getStartLongitude() {
		return startLongitude;
	}

	public void setStartLongitude(Double startLongitude) {
		this.startLongitude = startLongitude;
	}

	public Double getEndLatitude() {
		return endLatitude;
	}

	public void setEndLatitude(Double endLatitude) {
		this.endLatitude = endLatitude;
	}

	public Double getEndLongitude() {
		return endLongitude;
	}

	public void setEndLongitude(Double endLongitude) {
		this.endLongitude = endLongitude;
	}

	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long id) {
		this.bookingId = id;
	}

	public String getNumberOfPassengers() {
		return numberOfPassengers;
	}

	public void setNumberOfPassengers(String numberOfPassengers) {
		this.numberOfPassengers = numberOfPassengers;
	}

	public CarDriverEntity getCarEntity() {
		return carEntity;
	}

	public void setCarEntity(CarDriverEntity carEntity) {
		this.carEntity = carEntity;
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

	public String getReferenceNo() {
		return referenceNo;
	}

	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}

}
