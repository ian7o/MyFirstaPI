package com.rentsclients.rentsandclients.Mappers;

import com.rentsclients.rentsandclients.DTOS.CarDTO;
import com.rentsclients.rentsandclients.DTOS.CarDtoOnlyForActivated;
import com.rentsclients.rentsandclients.DTOS.CarPlateActivatedDto;
import com.rentsclients.rentsandclients.Entity.CarEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CarMapper {
    CarMapper Instance = Mappers.getMapper(CarMapper.class);

    CarEntity carDtoToCar(CarDTO carDTO);

    CarDTO carToCarDto(CarEntity carEntity);

    void updateCarEntityFromCarDTO(CarDTO carDTO, @MappingTarget CarEntity carEntity);

    void updateCarEntityFromCarDtoOnlyForActivated(CarDtoOnlyForActivated carDtoOnlyForActivated, @MappingTarget CarEntity carEntity);

    CarPlateActivatedDto carEntityToCarPlateActivatedDto(CarEntity carEntity);

    List<CarDTO> carToCarDto(List<CarEntity> carEntityList);

    List<CarEntity> carDtoToCarEntity(List<CarDTO> carDTOList);
}