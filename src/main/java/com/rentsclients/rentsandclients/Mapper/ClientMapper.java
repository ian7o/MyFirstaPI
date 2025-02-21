package com.rentsclients.rentsandclients.Mapper;

import com.rentsclients.rentsandclients.DTOS.ClientDTO;
import com.rentsclients.rentsandclients.Entity.ClientEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    ClientEntity clientDtoToCLientEntity(ClientDTO clientDTO);
    ClientDTO clientEntityToClientDto(ClientEntity client);
}
