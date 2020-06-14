package com.assignment.cab_booking.mapper;

import java.time.LocalDateTime;

import org.apache.tomcat.util.net.SocketEvent;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import com.assignment.cab_booking.entity.BookingEntity;
import com.assignment.cab_booking.model.BookingState;
import com.assignment.cab_booking.model.CarStatus;
import com.assignment.cab_booking.model.dto.BookingDTO;
import com.assignment.cab_booking.model.request.BookingRequest;
import com.assignment.cab_booking.model.response.BookingRest;

@Component
public class BookingMapper {

	private PropertyMap<BookingRequest, BookingDTO> bookingReqToDTO = new PropertyMap<BookingRequest, BookingDTO>() {
		protected void configure() {

			// customer's mobile number
			map().getCustomerDto().setMobileNumber(source.getCustomerMobileNumber());

		}
	};

	private PropertyMap<BookingDTO, BookingRest> bookingDtoToRest = new PropertyMap<BookingDTO, BookingRest>() {
		protected void configure() {

			// customer
			map().getCustomerDetails().setFirstName(source.getCustomerDto().getFirstName());
			map().getCustomerDetails().setLastName(source.getCustomerDto().getLastName());
			map().getCustomerDetails().setMobileNumber(source.getCustomerDto().getMobileNumber());

			map().getRideDetails().setStartLatitude(source.getStartLatitude());
			map().getRideDetails().setStartLongitude(source.getStartLongitude());
			map().getRideDetails().setNumberOfPassengers(source.getNumberOfPassengers());
			map().getRideDetails().setReferenceNumber(source.getReferenceNo());
			map().getRideDetails().setEndLatitude(source.getEndLatitude());
			map().getRideDetails().setEndLongitude(source.getEndLongitude());

			map().getDriverDetails().setCarName(source.getCarDriverDTO().getCarName());
			map().getDriverDetails().setCarNumber(source.getCarDriverDTO().getCarNumber());
			map().getDriverDetails().setFirstName(source.getCarDriverDTO().getFirstName());
			map().getDriverDetails().setLastName(source.getCarDriverDTO().getLastName());
			map().getDriverDetails().setMobileNumber(source.getCarDriverDTO().getMobileNumber());
			map().getDriverDetails().setLatitude(source.getCarDriverDTO().getLatitude());
			map().getDriverDetails().setLongitude(source.getCarDriverDTO().getLongitude());
			map().getDriverDetails().setCarStatus(source.getCarDriverDTO().getCarStatus());
		}
	};

	private PropertyMap<BookingEntity, BookingDTO> bookingEntityToDTO = new PropertyMap<BookingEntity, BookingDTO>() {
		protected void configure() {

			// customer's details
			map().getCustomerDto().setFirstName(source.getCustomerDetails().getFirstName());
			map().getCustomerDto().setLastName(source.getCustomerDetails().getLastName());
			map().getCustomerDto().setMobileNumber(source.getCustomerDetails().getMobileNumber());

			// driver details
			map().getCarDriverDTO().setCarName(source.getCarEntity().getCarName());
			map().getCarDriverDTO().setCarNumber(source.getCarEntity().getCarNumber());
			map().getCarDriverDTO().setCarStatus(source.getCarEntity().getCarStatus());
			
			map().getCarDriverDTO().setLatitude(source.getCarEntity().getLatitude());
			map().getCarDriverDTO().setLongitude(source.getCarEntity().getLongitude());
			map().getCarDriverDTO().setMobileNumber(source.getCarEntity().getDrivenBy().getMobileNumber());
			map().getCarDriverDTO().setFirstName(source.getCarEntity().getDrivenBy().getFirstName());
			map().getCarDriverDTO().setLastName(source.getCarEntity().getDrivenBy().getLastName());
		}
	};

	private PropertyMap<BookingDTO, BookingEntity> bookingDtoToEntity = new PropertyMap<BookingDTO, BookingEntity>() {
		protected void configure() {

			// customer's details
			map().getCustomerDetails().setFirstName(source.getCustomerDto().getFirstName());
			map().getCustomerDetails().setLastName(source.getCustomerDto().getLastName());
			map().getCustomerDetails().setMobileNumber(source.getCustomerDto().getMobileNumber());

			// driver details
			map().getCarEntity().setCarName(source.getCarDriverDTO().getCarName());
			map().getCarEntity().setCarNumber(source.getCarDriverDTO().getCarNumber());

		}
	};

	public PropertyMap<BookingRequest, BookingDTO> bookingReqToDTOMapping() {
		return bookingReqToDTO;
	}

	public PropertyMap<BookingDTO, BookingRest> bookingDTOtoRestMapping() {
		return bookingDtoToRest;
	}

	public PropertyMap<BookingEntity, BookingDTO> bookingEntityToDTOMapping() {
		return bookingEntityToDTO;
	}

	public PropertyMap<BookingDTO, BookingEntity> bookingDtoToEntity() {
		return bookingDtoToEntity;
	}

}