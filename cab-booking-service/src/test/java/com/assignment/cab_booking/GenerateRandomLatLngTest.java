package com.assignment.cab_booking;

import java.util.Random;

import org.junit.jupiter.api.Test;

public class GenerateRandomLatLngTest {

	@Test
	public void randomLocationTest() {

		Location location = new Location();
		location.setLatitude(19.2309696);
		location.setLongitude(72.9824377);

		Location newLocation = generatedRandomLocationWithinRadius(10000, location);

		System.out.println(newLocation.getLatitude());
		System.out.println(newLocation.getLongitude());
	}

	protected static Location generatedRandomLocationWithinRadius(double radiusInMeters, Location currentLocation) {
		double x0 = currentLocation.getLongitude();
		double y0 = currentLocation.getLatitude();

		Random random = new Random();

		// Convert radius from meters to degrees.
		double radiusInDegrees = radiusInMeters / 111320f;

		// Get a random distance and a random angle.
		double u = random.nextDouble();
		double v = random.nextDouble();
		double w = radiusInDegrees * Math.sqrt(u);
		double t = 2 * Math.PI * v;
		// Get the x and y delta values.
		double x = w * Math.cos(t);
		double y = w * Math.sin(t);

		// Compensate the x value.
		double new_x = x / Math.cos(Math.toRadians(y0));

		double foundLatitude;
		double foundLongitude;

		foundLatitude = y0 + y;
		foundLongitude = x0 + new_x;

		Location newLocation = new Location();
		newLocation.setLatitude(foundLatitude);
		newLocation.setLongitude(foundLongitude);
		return newLocation;
	}

}

class Location {
	private double latitude;
	private double longitude;

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

}