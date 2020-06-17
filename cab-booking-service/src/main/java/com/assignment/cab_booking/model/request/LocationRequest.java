package com.assignment.cab_booking.model.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.assignment.cab_booking.constants.ApplicationConstants;

public class LocationRequest {

	@NotNull
	@Max(ApplicationConstants.MAX_LATITUDE_VALUE)
	@Min(ApplicationConstants.MIN_LATITUDE_VALUE)
	private Double latitude;
	@NotNull
	@Max(ApplicationConstants.MAX_LONGITUDE_VALUE)
	@Min(ApplicationConstants.MIN_LONGITUDE_VALUE)
	private Double longitude;
	
	public LocationRequest() {}

	public LocationRequest(Double latitude, Double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
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
