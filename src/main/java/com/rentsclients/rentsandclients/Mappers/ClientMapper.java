package com.rentsclients.rentsandclients.Mapper;


import com.rentsclients.rentsandclients.DTOS.ClientDTO;
import com.rentsclients.rentsandclients.DTOS.ClientDtoOnlyForActivated;
import com.rentsclients.rentsandclients.DTOS.ClientDtoOnlyForFirstAndLastNames;
import com.rentsclients.rentsandclients.Entity.ClientEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(componentModel = "spring")
public interface ClientMapper {


    ClientMapper Instance = Mappers.getMapper(ClientMapper.class);

    ClientEntity clientDtoToClientEntity(ClientDTO clientDTO);

    ClientDTO ClientEntityToClientDto(ClientEntity clientEntity);

    ClientDtoOnlyForFirstAndLastNames ClientEntityToClientDtoOnlyForFirstAndLastNames(ClientEntity clientEntity);

    void updateClientEntityFromClientDto (ClientDTO clientDTO, @MappingTarget ClientEntity clientEntity );

    void updateClientEntityFromClientDtoOnlyForFirstAndLastNames(ClientDtoOnlyForFirstAndLastNames clientDtoOnlyForFirstAndLastNames, @MappingTarget ClientEntity clientEntity);

    void updateClientEntityFromClientDtoOnlyForActivated(ClientDtoOnlyForActivated clientDtoOnlyForActivated, @MappingTarget ClientEntity clientEntity);

    List<ClientDTO> ClientEntityToClientDto(List<ClientEntity> client);

    List<ClientEntity> clientDtosClient(List<ClientDTO> clientDTOS);
}