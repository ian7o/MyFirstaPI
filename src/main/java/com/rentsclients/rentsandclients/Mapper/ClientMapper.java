package com.rentsclients.rentsandclients.Mapper;


import com.rentsclients.rentsandclients.DTOS.ClientDTO;
import com.rentsclients.rentsandclients.Entity.ClientEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ClientMapper {

    ClientMapper Instance = Mappers.getMapper(ClientMapper.class);

    ClientEntity clientDtoToClient(ClientDTO clientDTO);

    ClientDTO clientToClient(ClientEntity student);

    List<ClientDTO> clientToClientDto(List<ClientEntity> client);

    List<ClientEntity> clientDtosClient(List<ClientDTO> clientDTOS);
}