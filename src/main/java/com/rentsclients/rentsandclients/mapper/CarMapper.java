package com.rentsclients.rentsandclients.mapper;

import com.rentsclients.rentsandclients.DTOS.CarDTO;
import com.rentsclients.rentsandclients.Entity.CarEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = ClientMapper.class)
public interface CarMapper {
    CarEntity toCarEntity(CarDTO carDTO);
    CarDTO toCarDto(CarEntity carEntity);
}
