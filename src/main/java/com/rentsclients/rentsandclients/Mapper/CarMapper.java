package com.rentsclients.rentsandclients.Mapper;

import com.rentsclients.rentsandclients.DTOS.CarDTO;
import com.rentsclients.rentsandclients.Entity.CarEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CarMapper {

    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    CarEntity carDtoToCarEntity(CarDTO clientDTO);
    CarDTO carEntityToCarDto(CarEntity client);
}
