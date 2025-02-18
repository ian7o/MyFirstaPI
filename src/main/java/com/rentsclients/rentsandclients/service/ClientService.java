package com.rentsclients.rentsandclients.service;

import com.rentsclients.rentsandclients.DTOS.ClientDTO;
import com.rentsclients.rentsandclients.Exceptions.ClientNotFoundException;
import com.rentsclients.rentsandclients.Mappers.CarMapp;
import com.rentsclients.rentsandclients.Mappers.ClientMapper;
import com.rentsclients.rentsandclients.Entity.ClientEntity;
import com.rentsclients.rentsandclients.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        ClientEntity converterInEntity = ClientMapper.Instance.clientDtoToClient(clientDTO);

        if (clientRepository.existsByNif(converterInEntity.getNif())) {
            throw new ClientNotFoundException("A client with this nif already exists.");
        }

        ClientEntity savedEntity = clientRepository.save(converterInEntity);
        return ClientMapper.Instance.clientToClientdto(savedEntity);
    }

    public List<ClientEntity> getAllClients(ClientEntity client) {
        return clientRepository.findAll();
    }

    public ClientDTO getASpecifiqueClientByID(Long id) {
        return ClientMapper.Instance.clientToClientdto(this.clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Client not found")));
    }


    public ClientDTO updateClientByID(long id, ClientDTO clientDTO) {
        ClientEntity searchClient = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Client not found"));

        ClientEntity client = ClientMapper.Instance.clientDtoToClient(clientDTO);

        if (clientRepository.existsByNif(client.getNif())) {
            throw new RuntimeException("A client with this nif already exists.");
        }

        ClientEntity saved = clientRepository.save(client);

        return ClientMapper.Instance.clientToClientdto(saved);
    }

    public void deleteClientByID(Long id) {
        clientRepository.deleteById(id);
    }

    public List<ClientDTO> getActivatedAccounts() {
        List<ClientEntity> clients = clientRepository.findByActivatedFalse();
        List<ClientDTO> clientDTOList = new ArrayList<>();

        clients.forEach(client -> clientDTOList.add(new ClientDTO(
                client.getClientID(),
                client.getFirstName(),
                client.getLastName()
        )));

        if (clientDTOList.isEmpty()){
            throw new RuntimeException("Nothing to show");
        }
        return clientDTOList;
    }


    public ClientDTO updateClientFirstNameAndLastName(long id, ClientDTO clientDTO) {
        ClientEntity findClient = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Client not found"));

        findClient.setFirstName(clientDTO.getFirstName());
        findClient.setLastName(clientDTO.getLastName());

        ClientEntity saved = clientRepository.save(findClient);

        return ClientMapper.Instance.clientToClientdto(saved);
    }

    public ClientDTO activateOrDeactivateClientByID(long id, ClientDTO clientDTO) {
        ClientEntity findClient = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Client not found"));

        findClient.setActivated(clientDTO.getActivated());
        ClientEntity saved = clientRepository.save(findClient);

        return ClientMapper.Instance.clientToClientdto(saved);
    }
}