//package com.rentsclients.rentsandclients.Mappers;
//
//
//import com.rentsclients.rentsandclients.DTOS.ClientDTO;
//import com.rentsclients.rentsandclients.Entity.ClientEntity;
//import org.junit.jupiter.api.Test;
//import org.mapstruct.factory.Mappers;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class ClientMapperTest {
//
//    private ClientMapper clientMapper = Mappers.getMapper(ClientMapper.class);
//
//    @Test
//    public void testDTOToEntity(){
//        ClientDTO clientDTO = new ClientDTO();
//        clientDTO.setFirstName("joao");
//        clientDTO.setLastName("pereira");
//
//        ClientEntity clientEntity = clientMapper.clientDtoToClientEntity(clientDTO);
//
//        assertEquals(clientDTO.getFirstName(), clientEntity.getFirstName());
//    }
//
//    @Test
//    public void  testEntityToDto(){
//        ClientEntity clientEntity = new ClientEntity();
//        clientEntity.setFirstName("joao");
//        clientEntity.setLastName("pereira");
//
//        ClientDTO clientDTO = clientMapper.ClientEntityToClientDto(clientEntity);
//
//        assertEquals(clientDTO.getFirstName(), clientDTO.getFirstName());
//    }
//
//    @Test
//    public void testEntityToDtoIsEmpty(){
//        ClientEntity clientEntity = null;
//
//        ClientDTO clientDTO = clientMapper.ClientEntityToClientDto(clientEntity);
//
//        assertNull(clientDTO);
//
//    }
//
//    @Test
//    public void testDTOToEntityIsEmpty(){
//        ClientDTO clientDTO = null;
//
//        ClientEntity clientEntity = clientMapper.clientDtoToClientEntity(clientDTO);
//
//        assertNull(clientEntity);
//    }
//
//}