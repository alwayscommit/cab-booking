package com.assignment.cab_booking.model.dto;

import com.assignment.cab_booking.model.BookingState;

public class BookingDTO {

	private String referenceNo;
	private Double endLatitude;
	private Double endLongitude;
	private Double startLatitude;
	private Double startLongitude;
	private String numberOfPassengers;
	private UserDTO customerDto;
	private CabDriverDTO carDriverDTO;
	private BookingState bookingState;
	private String startAddress;
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


	public BookingState getBookingState() {
		return bookingState;
	}

	public void setBookingState(BookingState bookingState) {
		this.bookingState = bookingState;
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

	public String getNumberOfPassengers() {
		return numberOfPassengers;
	}

	public void setNumberOfPassengers(String numberOfPassengers) {
		this.numberOfPassengers = numberOfPassengers;
	}

	public UserDTO getCustomerDto() {
		return customerDto;
	}

	public void setCustomerDto(UserDTO customerDto) {
		this.customerDto = customerDto;
	}

	public CabDriverDTO getCarDriverDTO() {
		return carDriverDTO;
	}

	public void setCarDriverDTO(CabDriverDTO carDriverDTO) {
		this.carDriverDTO = carDriverDTO;
	}

	public String getReferenceNo() {
		return referenceNo;
	}

	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
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

	@Override
	public String toString() {
		return "BookingDTO [referenceNo=" + referenceNo + ", endLatitude=" + endLatitude + ", endLongitude="
				+ endLongitude + ", startLatitude=" + startLatitude + ", startLongitude=" + startLongitude
				+ ", numberOfPassengers=" + numberOfPassengers + ", customerDto=" + customerDto + ", carDriverDTO="
				+ carDriverDTO + "]";
	}

}
