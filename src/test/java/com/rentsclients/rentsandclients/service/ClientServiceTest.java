package com.rentsclients.rentsandclients.service;

import com.rentsclients.rentsandclients.DTOS.ClientDTO;
import com.rentsclients.rentsandclients.Entity.ClientEntity;
import com.rentsclients.rentsandclients.Exceptions.ClientNotFoundException;
import com.rentsclients.rentsandclients.Repository.ClientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    ClientRepository clientRepository;

    @InjectMocks
    ClientService clientService;

    @Test
    void when_GetAllStudentIsEmpty_ReturnEmpty() {
        when(clientRepository.findAll()).thenReturn(new ArrayList<>());

        List<ClientDTO> allClients = clientService.getAllClients();
        assertTrue(allClients.isEmpty());
//        assertThrows(RuntimeException.class, () -> clientService.getAllClients());
    }

    @Test
    void when_GetAllStudentsHaveResults_ReturnList() {
        List<ClientEntity> clientEntities = new ArrayList<>();

        clientEntities.add(new ClientEntity(1L, "cuk", "deCurioso", 321345321, true));

        when(clientRepository.findAll()).thenReturn(clientEntities);

        List<ClientDTO> clientDTOS = clientService.getAllClients();
        for (int i = 0; i < clientEntities.size(); i++) {
            assertEquals(clientEntities.get(i).getFirstName(), clientDTOS.get(i).getFirstName());
            assertEquals(clientEntities.get(i).getLastName(), clientDTOS.get(i).getLastName());
            assertEquals(clientEntities.get(i).getNif(), clientDTOS.get(i).getNif());
            assertEquals(clientEntities.get(i).isActivated(), clientDTOS.get(i).isActivated());
        }
    }

    @Test
    void when_getStudentByIdAndExists_then_return() {
        ClientEntity client = new ClientEntity(1L, "zee", "aberto", 123456789, true);
        when(clientRepository.findById(any(Long.class))).thenReturn(Optional.of(client));

        ClientDTO result = clientService.getASpecificClientByID(1L);
        assertEquals(client.getFirstName(), result.getFirstName());
        assertEquals(client.getLastName(), result.getLastName());
        assertEquals(client.getNif(), result.getNif());
    }

    @Test
    void when_GetStudentByIDNotExist_ThenReturnException() {
        when(clientRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ClientNotFoundException.class, () -> clientService.getASpecificClientByID(1L));
    }


}