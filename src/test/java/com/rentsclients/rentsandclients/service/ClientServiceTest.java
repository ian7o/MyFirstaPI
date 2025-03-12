//package com.rentsclients.rentsandclients.service;
//
//import com.rentsclients.rentsandclients.DTOS.ClientDTO;
//import com.rentsclients.rentsandclients.Entity.ClientEntity;
//import com.rentsclients.rentsandclients.Exceptions.ClientNotFoundException;
//import com.rentsclients.rentsandclients.Mappers.ClientMapper;
//import com.rentsclients.rentsandclients.Mappers.ClientMapperTest;
//import com.rentsclients.rentsandclients.Repository.ClientRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//class ClientServiceTest {
//
//    @Mock
//    ClientRepository clientRepository;
//
//    @Mock
//    ClientMapper clientMapper;
//
//    @InjectMocks
//    ClientService clientService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//
//    /*
//    POST
//     */
//
//    /*
//    PUT
//     */
//
//    /*
//    GETS
//     */
//
//    /*
//    GET ALL
//     */
//    @Test
//    void when_GetAllStudentIsEmpty_ReturnEmpty() {
//        when(clientRepository.findAll()).thenReturn(new ArrayList<>());
//
//        List<ClientDTO> allClients = clientService.getAllClients();
//        assertTrue(allClients.isEmpty());
//// porque nao posso        assertThrows(RuntimeException.class, () -> clientService.getAllClients());
//    }
//
//    @Test
//    void when_GetAllStudentsHaveResults_ReturnList() {
//        ClientEntity clientEntities = new ClientEntity();
//
//        clientEntities.setFirstName("zee");
//        clientEntities.setLastName("campos");
//
//
//        when(clientMapper.ClientEntityToClientDto(clientEntities)).thenReturn((ClientDTO) Arrays.asList(clientEntities));
//        when(clientRepository.findAll()).thenReturn(Arrays.asList(clientEntities));
//
//        List<ClientDTO> clientDTOS = clientService.getAllClients();
//
//        assertEquals(clientEntities, clientDTOS);
//    }
//
//    /*
//    GET BY ID
//     */
//    @Test
//    void when_getStudentByIdAndExists_then_return() {
//        ClientEntity client = new ClientEntity(1L, "zee", "aberto", 123456789, true);
//        when(clientRepository.findById(any(Long.class))).thenReturn(Optional.of(client));
//
//        ClientDTO result = clientService.getASpecificClientByID(1L);
//
//        assertEquals(client.getFirstName(), result.getFirstName());
//        assertEquals(client.getLastName(), result.getLastName());
//        assertEquals(client.getNif(), result.getNif());
//
//        verify(clientRepository).findById(1L);
//    }
//
//    @Test
//    void when_GetStudentByIDNotExist_ThenReturnException() {
//        when(clientRepository.findById(1L)).thenReturn(Optional.empty());
//        assertThrows(ClientNotFoundException.class, () -> clientService.getASpecificClientByID(1L));
//    }
//
//}