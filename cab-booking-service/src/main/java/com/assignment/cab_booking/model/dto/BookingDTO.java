package com.assignment.cab_booking.model.dto;

public class BookingDTO {

	private String referenceNo;
	private Double endLatitude;
	private Double endLongitude;
	private Double startLatitude;
	private Double startLongitude;
	private String numberOfPassengers;
	private CustomerDTO customerDto;
	private CabDriverDTO carDriverDTO;

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

	public CustomerDTO getCustomerDto() {
		return customerDto;
	}

	public void setCustomerDto(CustomerDTO customerDto) {
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
