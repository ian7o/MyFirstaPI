package com.rentsclients.rentsandclients.Mappers;

import com.rentsclients.rentsandclients.DTOS.ClientDTO;
import com.rentsclients.rentsandclients.Entity.ClientEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClientMapper {

    ClientMapper Instance = Mappers.getMapper(ClientMapper.class);

    ClientEntity clientDtoToClient(ClientDTO clientDTO);

    ClientDTO clientToClientdto(ClientEntity student);

}