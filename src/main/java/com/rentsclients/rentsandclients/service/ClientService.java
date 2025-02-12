package com.rentsclients.rentsandclients.service;

import com.rentsclients.rentsandclients.Entity.ClientEntity;
import com.rentsclients.rentsandclients.Repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ClientEntity createAClient(ClientEntity client) {
       return this.clientRepository.save(client);
    }

    public List<ClientEntity> getAllClients(ClientEntity client) {
        List<ClientEntity> listWithAllClients = clientRepository.findAll();
        return listWithAllClients;
    }

    public ClientEntity getASpecifiqueClientID( Long id) {
        ClientEntity clientRepository1 =clientRepository.findById(id).orElse(null);
        return clientRepository1;
    }


    public ClientEntity updateClientByID(long id,  ClientEntity clientEntity) {
        Optional<ClientEntity> findClient = clientRepository.findById(id);
        if (findClient.isPresent()) {
            findClient.get().setActivated(clientEntity.isActivated());
            findClient.get().setFirstName(clientEntity.getFirstName());
            findClient.get().setLastName(clientEntity.getLastName());
            findClient.get().setNif(clientEntity.getNif());
            return clientRepository.save(findClient.get());
        }
        return null;
    }

    public void deleteClientByID(Long id) {
        clientRepository.deleteById(id);
    }
//
    public String getActivatedAccounts() {
        List<ClientEntity> clients = clientRepository.findByActivatedFalse();
        if (!clients.isEmpty()) {
            String firstsAndLastsNames = " ";
            for (ClientEntity client : clients) {
                firstsAndLastsNames += client.getFirstName() + " " + client.getLastName() + ", ";
            }
            return firstsAndLastsNames;
        }
        return "not find";
    }

    public ClientEntity updateClientFirstNameAndLastName(long id,  ClientEntity clientEntity) {
        Optional<ClientEntity> findClient = clientRepository.findById(id);
        if (findClient.isPresent()) {
            findClient.get().setFirstName(clientEntity.getFirstName());
            ClientEntity clientToUpDate = findClient.get();
            clientToUpDate.setLastName(clientEntity.getLastName());
            return clientRepository.save(findClient.get());
        }
        return null;
    }

    public ClientEntity activateOrDeactivateClientByID(long id, ClientEntity clientEntity) {
        Optional<ClientEntity> findClient = clientRepository.findById(id);
        if (findClient.isPresent()) {
            findClient.get().setActivated(clientEntity.isActivated());
            return clientRepository.save(findClient.get());
        }
        return null;
    }
}