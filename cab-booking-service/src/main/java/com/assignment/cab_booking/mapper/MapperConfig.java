package com.assignment.cab_booking.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();

		BookingMapper bookingMapper = new BookingMapper();

		modelMapper.addMappings(bookingMapper.bookingDtoToRestMapping());
		modelMapper.addMappings(bookingMapper.bookingEntityToDtoMapping());
		modelMapper.addMappings(bookingMapper.bookingDtoToEntity());
		modelMapper.addMappings(bookingMapper.bookingReqToDto());

		return modelMapper;
	}

}
