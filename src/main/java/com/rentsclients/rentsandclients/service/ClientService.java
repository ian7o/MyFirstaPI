package com.rentsclients.rentsandclients.service;

import com.rentsclients.rentsandclients.DTOS.ClientDTO;
import com.rentsclients.rentsandclients.Exceptions.ClientNotFoundException;
import com.rentsclients.rentsandclients.Entity.ClientEntity;
import com.rentsclients.rentsandclients.Repository.ClientRepository;
import com.rentsclients.rentsandclients.mapper.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    private final ClientMapper clientMapper;

    @Autowired
    public ClientService(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    public ClientDTO createAClient(ClientDTO clientDTO) {
        ClientEntity converterInEntity = clientMapper.toClientEntity(clientDTO);

        if (clientRepository.existsByNif(converterInEntity.getNif())) {
            throw new ClientNotFoundException("A client with this nif already exists.");
        }

        clientRepository.save(converterInEntity);
        return clientDTO;
    }

    public List<ClientEntity> getAllClients(ClientEntity client) {
        return clientRepository.findAll();
    }

    public ClientDTO getASpecificClientByID(Long id) {
        return clientMapper.toClientDto(this.clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Client not found")));
    }


    public ClientDTO updateClientByID(long id, ClientDTO clientDTO) {
        ClientEntity searchClient = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Client not found"));

        ClientEntity client = clientMapper.toClientEntity(clientDTO);
        client.setClientid(searchClient.getClientid());

        ClientEntity saved = clientRepository.save(client);

        return clientMapper.toClientDto(saved);
    }

    public void deleteClientByID(Long id) {
        clientRepository.deleteById(id);
    }

//    public List<ClientDTO> getActivatedAccounts() {
//        List<ClientEntity> clients = clientRepository.findByActivatedFalse();
//        List<ClientDTO> clientDTOList = new ArrayList<>();
//
//        clients.forEach(client -> clientDTOList.add((
//                new ClientDTO(
//                     null,
//                        client.getFirstName(),
//                        client.getLastName(),
//                     null,
//                     null,
//                        null
//                )
//                )));
//
//        if (clientDTOList.isEmpty()){
//            throw new RuntimeException("Nothing to show");
//        }
//        return clientDTOList;
//    }


    public ClientDTO updateClientFirstNameAndLastName(long id, ClientDTO clientDTO) {
        ClientEntity findClient = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Client not found"));

        findClient.setFirstName(clientDTO.getFirstName());
        findClient.setLastName(clientDTO.getLastName());

        ClientEntity saved = clientRepository.save(findClient);

        return clientMapper.toClientDto(saved);
    }

    public ClientDTO activateOrDeactivateClientByID(long id, ClientDTO clientDTO) {
        ClientEntity findClient = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Client not found"));

        findClient.setActivated(clientDTO.getActivated());
        ClientEntity saved = clientRepository.save(findClient);

        return clientMapper.toClientDto(saved);
    }
}