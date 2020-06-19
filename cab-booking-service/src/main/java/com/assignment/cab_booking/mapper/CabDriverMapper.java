package com.assignment.cab_booking.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.assignment.cab_booking.entity.CarDriverEntity;
import com.assignment.cab_booking.entity.UserAccountEntity;
import com.assignment.cab_booking.model.dto.CabDriverDTO;
import com.assignment.cab_booking.model.dto.LocationDTO;
import com.assignment.cab_booking.model.request.CabDriverRequest;
import com.assignment.cab_booking.model.request.LocationRequest;
import com.assignment.cab_booking.model.response.CabDriverRest;

@Component
public class CabDriverMapper {

	@Autowired
	private ModelMapper modelMapper;

	public LocationDTO mapToLocationDTO(LocationRequest locationRequest) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
		return modelMapper.map(locationRequest, LocationDTO.class);
	}

	public CabDriverRest mapToDriverResponse(CabDriverDTO driverDTO) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
		return modelMapper.map(driverDTO, CabDriverRest.class);
	}

	public List<CabDriverRest> mapToResponseList(List<CabDriverDTO> cabDriverList) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		return modelMapper.map(cabDriverList, new TypeToken<List<CabDriverRest>>() {
		}.getType());
	}
	
	public CabDriverDTO mapToDTO(CabDriverRequest driverRequest) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
		return modelMapper.map(driverRequest, CabDriverDTO.class);
	}

	public CabDriverRest mapToRest(CabDriverDTO savedCabDetails) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
		return modelMapper.map(savedCabDetails, CabDriverRest.class);
	}

	public UserAccountEntity mapToUserEntity(CabDriverDTO carDriverDTO) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		return modelMapper.map(carDriverDTO, UserAccountEntity.class);
	}

	public CarDriverEntity mapToCarEntity(CabDriverDTO carDriverDTO) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
		return modelMapper.map(carDriverDTO, CarDriverEntity.class);
	}

	public CabDriverDTO mapToDTO(CarDriverEntity savedCar) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		return modelMapper.map(savedCar, CabDriverDTO.class);
	}
	
	public CabDriverDTO mapToDTO(UserAccountEntity driverDetails) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
		return modelMapper.map(driverDetails, CabDriverDTO.class);
	}

	public List<CabDriverDTO> mapToListDTO(List<CarDriverEntity> availableCarList) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		return modelMapper.map(availableCarList, new TypeToken<List<CabDriverDTO>>() {
		}.getType());
	}

}
