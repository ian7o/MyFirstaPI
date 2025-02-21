package com.rentsclients.rentsandclients.service;

import com.rentsclients.rentsandclients.DTOS.ClientDTO;
import com.rentsclients.rentsandclients.Exceptions.ClientNotFoundException;
import com.rentsclients.rentsandclients.Entity.ClientEntity;
import com.rentsclients.rentsandclients.Repository.ClientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    public ClientDTO createAClient(ClientDTO clientDTO) {

        ClientEntity converterInEntity = new ClientEntity(
                null,
                clientDTO.getFirstName(),
                clientDTO.getLastName(),
                clientDTO.getNif(),
                clientDTO.getActivated()
        );

        if (clientRepository.existsByNif(converterInEntity.getNif())) {
            throw new ClientNotFoundException("A client with this nif already exists.");
        }

        ClientEntity clientEntity = clientRepository.save(converterInEntity);
        return new ClientDTO(
                clientEntity.getClientid(),
                clientDTO.getFirstName(),
                clientDTO.getLastName(),
                clientDTO.getNif(),
                clientDTO.getActivated());
    }

    public void updateClientByID(long id, ClientDTO clientDTO) {
       ClientEntity clientEntity = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Client not found"));

       clientEntity.setClientid(id);
       clientEntity.setFirstName(clientDTO.getFirstName());
       clientEntity.setLastName(clientDTO.getLastName());
       clientEntity.setNif(clientEntity.getNif());
       clientEntity.setActivated(clientDTO.getActivated());

        clientRepository.save(clientEntity);


//        ClientEntity saved = clientRepository.save(converterInClient);

//        return new ClientDTO(
//                saved.getClientid(),
//                saved.getFirstName(),
//                saved.getLastName(),
//                saved.getNif(),
//                saved.isActivated()
//        );
    }

    //
    public void updateClientFirstNameAndLastName(long id, ClientDTO clientDTO) {
        ClientEntity findClient = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Client not found"));

        findClient.setFirstName(clientDTO.getFirstName());
        findClient.setLastName(clientDTO.getLastName());

        clientRepository.save(findClient);
    }

    //
    public void activateOrDeactivateClientByID(long id, ClientDTO clientDTO) {
        ClientEntity findClient = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Client not found"));

        findClient.setActivated(clientDTO.getActivated());

        clientRepository.save(findClient);

    }

    //
//
    public List<ClientEntity> getAllClients(ClientEntity client) {
        return clientRepository.findAll();
    }

    //
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

    //
//
//
//
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
//
    public void deleteClientByID(Long id) {
        clientRepository.findById(id).orElseThrow(() ->new ClientNotFoundException("Client not found. Cannot be deleted"));
        clientRepository.deleteById(id);
    }

}