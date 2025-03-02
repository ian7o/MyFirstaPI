package com.rentsclients.rentsandclients.Mapper;

import com.rentsclients.rentsandclients.DTOS.CarDTO;
import com.rentsclients.rentsandclients.DTOS.CarPlateActivatedDto;
import com.rentsclients.rentsandclients.Entity.CarEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CarMapper {
    CarMapper Instance = Mappers.getMapper(CarMapper.class);

    CarEntity carDtoToCar(CarDTO carDTO);

    CarDTO carToCarDto(CarEntity carEntity);

    CarPlateActivatedDto carEntityToCarPlateActivatedDto(CarEntity carEntity);

    List<CarDTO> carToCarDto(List<CarEntity> carEntityList);

    List<CarEntity> carDtoToCarEntity(List<CarDTO> carDTOList);
}