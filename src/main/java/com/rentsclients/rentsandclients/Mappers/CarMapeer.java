package com.rentsclients.rentsandclients.Mappers;

import com.rentsclients.rentsandclients.DTOS.CarDTO;
import com.rentsclients.rentsandclients.Entity.CarEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CarMapeer {
    CarMapeer Instance = Mappers.getMapper(CarMapeer.class);

    CarEntity carDtoToCar(CarDTO carDTO);

    CarDTO carToCardto(CarEntity carEntity);
}
