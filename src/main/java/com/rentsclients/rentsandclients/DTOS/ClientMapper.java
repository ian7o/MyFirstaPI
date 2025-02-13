package com.rentsclients.rentsandclients.DTOS;

import com.rentsclients.rentsandclients.Entity.ClientEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ClientMapper {

    ClientMapper Instance = Mappers.getMapper(ClientMapper.class);

    ClientEntity clientDtoToClient(ClientDTO clientDTO);

    ClientDTO clientToClient(ClientEntity student);

}