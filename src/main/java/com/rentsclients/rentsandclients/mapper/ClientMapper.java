package com.rentsclients.rentsandclients.mapper;

import com.rentsclients.rentsandclients.DTOS.ClientDTO;
import com.rentsclients.rentsandclients.Entity.ClientEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    ClientDTO toClientDto(ClientEntity client);
    ClientEntity toClientEntity(ClientDTO clientDTO);
}