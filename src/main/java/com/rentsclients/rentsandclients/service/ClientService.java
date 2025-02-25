package com.rentsclients.rentsandclients.service;

import com.rentsclients.rentsandclients.DTOS.ClientDTO;
import com.rentsclients.rentsandclients.DTOS.ClientDtoOnlyForFirstAndLastNames;
import com.rentsclients.rentsandclients.Exceptions.ClientNotFoundException;
import com.rentsclients.rentsandclients.Entity.ClientEntity;
import com.rentsclients.rentsandclients.Exceptions.DuplicateClientNifException;
import com.rentsclients.rentsandclients.Repository.ClientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    public void createAClient(ClientDTO clientDTO) {
        ClientEntity converterInEntity = new ClientEntity(
                null,
                clientDTO.getFirstName(),
                clientDTO.getLastName(),
                clientDTO.getNif(),
                clientDTO.getActivated()
        );

        if (clientRepository.existsByNif(converterInEntity.getNif())) {
            throw new DuplicateClientNifException("A client with this nif already exists.");
        }

        clientRepository.save(converterInEntity);
    }

    public void updateClient(long id, ClientDTO clientDTO) {
        ClientEntity converterInEntity = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Client not found"));

        converterInEntity.setFirstName(clientDTO.getFirstName());
        converterInEntity.setLastName(clientDTO.getLastName());
        converterInEntity.setNif(clientDTO.getNif());
        converterInEntity.setActivated(clientDTO.getActivated());


        if (clientRepository.existsByNif(converterInEntity.getNif())) {
            throw new DuplicateClientNifException("A client with this nif already exists.");
        }

        clientRepository.save(converterInEntity);
    }

    public void updateClientFirstAndLastName(long id, ClientDTO clientDTO) {
        ClientEntity findClient = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Client not found"));

        findClient.setFirstName(clientDTO.getFirstName());
        findClient.setLastName(clientDTO.getLastName());

        clientRepository.save(findClient);
    }

    public void activateOrDeactivateClientByID(long id, ClientDTO clientDTO) {
        ClientEntity findClient = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Client not found"));

        findClient.setActivated(clientDTO.getActivated());

        clientRepository.save(findClient);

    }

    public List<ClientDtoOnlyForFirstAndLastNames> getDeactivatedAccounts() {
        List<ClientEntity> clients = clientRepository.findByActivatedFalse();
        List<ClientDtoOnlyForFirstAndLastNames> clientDtoOnlyForFirstAndLastNamesList = new ArrayList<>();

        clients.forEach(client -> clientDtoOnlyForFirstAndLastNamesList.add((
                new ClientDtoOnlyForFirstAndLastNames(
                        client.getFirstName(),
                        client.getLastName()
                )
        )));

        if (clientDtoOnlyForFirstAndLastNamesList.isEmpty()) {
            throw new RuntimeException("Nothing to show");
        }
        return clientDtoOnlyForFirstAndLastNamesList;
    }

    public List<ClientDTO> getAllClients() {
        List<ClientEntity> clientEntity = clientRepository.findAll();

        List<ClientDTO> clientDTOList = new ArrayList<>();

        clientEntity.forEach(client -> clientDTOList.add((new ClientDTO(
                client.getClientid(),
                client.getFirstName(),
                client.getLastName(),
                client.getNif(),
                client.isActivated()
        ))));

        return clientDTOList;
    }

    public ClientDTO getASpecificClientByID(Long id) {
        ClientEntity clientEntity = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Client not found"));

        return new ClientDTO(
                clientEntity.getClientid(),
                clientEntity.getFirstName(),
                clientEntity.getLastName(),
                clientEntity.getNif(),
                clientEntity.isActivated()
        );
    }



    public void deleteClientByID(Long id) {
        clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException("Client not found. Cannot be deleted"));
        clientRepository.deleteById(id);
    }

}