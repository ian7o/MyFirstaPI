package com.rentsclients.rentsandclients.service;

import com.rentsclients.rentsandclients.DTOS.ClientDTO;
import com.rentsclients.rentsandclients.Entity.ClientEntity;
import com.rentsclients.rentsandclients.Repository.ClientRepository;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.support.NullValue;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ClientDTO createAClient(ClientDTO clientDTO) {
        ClientEntity converterInEntity = new ClientEntity();
        converterInEntity.setFirstName(clientDTO.getFirstName());
        converterInEntity.setLastName(clientDTO.getLastName());
        converterInEntity.setNif(clientDTO.getNif());
        converterInEntity.setActivated(clientDTO.getActivated());

        ClientEntity savedEntity = clientRepository.save(converterInEntity);

        return new ClientDTO(
                savedEntity.getClientID(),
                savedEntity.getFirstName(),
                savedEntity.getLastName(),
                savedEntity.getNif(),
                savedEntity.isActivated()
        );

    }

    //num vou fazer aqui
    public List<ClientEntity> getAllClients(ClientEntity client) {
        List<ClientEntity> listWithAllClients = clientRepository.findAll();
        return listWithAllClients;
    }

    public ClientDTO getASpecifiqueClientByID(Long id) throws Exception {
        ClientEntity clientEntity;
        clientEntity = clientRepository.findById(id)
                .orElseThrow(() -> new Exception());

        return new ClientDTO(clientEntity.getClientID(), clientEntity.getFirstName(), clientEntity.getLastName(), clientEntity.getNif(), clientEntity.isActivated());
    }


    public ClientDTO updateClientByID(long id, ClientDTO clientDTO) throws Exception {
        ClientEntity searchClient = clientRepository.findById(id)
                .orElseThrow(() -> new Exception());

        searchClient.setActivated(clientDTO.getActivated());
        searchClient.setFirstName(clientDTO.getFirstName());
        searchClient.setLastName(clientDTO.getLastName());
        searchClient.setNif(clientDTO.getNif());

        ClientEntity saved = clientRepository.save(searchClient);

        return new ClientDTO(
                saved.getClientID(),
                saved.getFirstName(),
                saved.getLastName(),
                saved.getNif(),
                saved.isActivated()
        );
    }

    public void deleteClientByID(Long id) {
        clientRepository.deleteById(id);
    }

    public List<ClientDTO> getActivatedAccounts() {
        List<ClientEntity> clients = clientRepository.findByActivatedFalse();
        List<ClientDTO> clientDTOList = new ArrayList<>();
        if (!clients.isEmpty()) {
            for (ClientEntity client : clients) {
                ClientDTO oneClientDto = new ClientDTO(
                        null,
                        clients.getFirst().getFirstName(),
                        clients.getFirst().getLastName(),
                        //ele que se vire com o nif
                        null,
                        clients.getFirst().isActivated()
                );
                clientDTOList.add(oneClientDto);
            }
            return clientDTOList;
        }
        return null;
    }

    public ClientDTO updateClientFirstNameAndLastName(long id, ClientDTO clientDTO) throws Exception {
        ClientEntity findClient = clientRepository.findById(id)
                .orElseThrow(() -> new Exception());


        findClient.setFirstName(clientDTO.getFirstName());
        findClient.setLastName(clientDTO.getLastName());

        clientRepository.save(findClient);

        //ou criar um novo construtor (incorreto) ou criar um Bolean (que aceita nullo talvez o correto)
        return new ClientDTO(findClient.getClientID(), findClient.getFirstName(), findClient.getLastName(), findClient.getNif(), findClient.isActivated());
    }

    public ClientDTO activateOrDeactivateClientByID(long id, ClientDTO clientDTO) throws Exception {
        Optional<ClientEntity> findClient = clientRepository.findById(id);
        if (findClient.isPresent()) {
            ClientEntity client = findClient.get();
            client.setActivated(clientDTO.getActivated());

            clientRepository.save(client);

            return ClientDTO.builder()
                    .clientID(client.getClientID())
                    .firstName(client.getFirstName())
                    .lastName(client.getLastName())
                    .nif(client.getNif())
                    .activated(client.isActivated())
                    .build();
        }
        return null;
    }
}