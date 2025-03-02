package com.rentsclients.rentsandclients.service;

import com.rentsclients.rentsandclients.DTOS.ClientDTO;
import com.rentsclients.rentsandclients.DTOS.ClientDtoOnlyForActivated;
import com.rentsclients.rentsandclients.DTOS.ClientDtoOnlyForFirstAndLastNames;
import com.rentsclients.rentsandclients.Exceptions.ClientFirstNameException;
import com.rentsclients.rentsandclients.Exceptions.ClientLastNameException;
import com.rentsclients.rentsandclients.Exceptions.ClientNotFoundException;
import com.rentsclients.rentsandclients.Entity.ClientEntity;
import com.rentsclients.rentsandclients.Exceptions.DuplicateClientNifException;
import com.rentsclients.rentsandclients.Mapper.ClientMapper;
import com.rentsclients.rentsandclients.Repository.ClientRepository;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {
    private ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public ClientService(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    public void validateNamesSize(String firstName, String lastName) {
        if (firstName.strip().replace(" ", "").length() < 3) {
            throw new ClientFirstNameException("The client first name size do not approved");
        }
        if (lastName.strip().replace(" ", "").length() < 3) {
            throw new ClientLastNameException("The client last name size do not approved");
        }
    }

    public void createAClient(ClientDTO clientDTO) {
        validateNamesSize(clientDTO.getFirstName(), clientDTO.getLastName());

        ClientEntity converterInEntity = clientMapper.clientDtoToClientEntity(clientDTO);

        if (clientRepository.existsByNif(converterInEntity.getNif())) {

            throw new DuplicateClientNifException("A client with this nif already exists.");
        }
        clientRepository.save(converterInEntity);
    }

    public void updateClient(long id, ClientDTO clientDTO) {
        validateNamesSize(clientDTO.getFirstName(), clientDTO.getLastName());

        ClientEntity converterInEntity = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Client not found"));

        clientMapper.updateClientEntityFromClientDto(clientDTO, converterInEntity);

        if (clientRepository.existsByNifAndClientidNot(converterInEntity.getNif(), id)) {
            throw new DuplicateClientNifException("A client with this nif already exists.");
        }
        clientRepository.save(converterInEntity);
    }


    public void updateClientFirstAndLastName(long id, ClientDtoOnlyForFirstAndLastNames clientDTO) {
        validateNamesSize(clientDTO.getFirstName(), clientDTO.getLastName());

        ClientEntity findClient = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Client not found"));

        clientMapper.updateClientEntityFromClientDtoOnlyForFirstAndLastNames(clientDTO, findClient);
        clientRepository.save(findClient);
    }

    public void activateOrDeactivateClientByID(long id, ClientDtoOnlyForActivated clientDTO) {
        ClientEntity findClient = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Client not found"));

        clientMapper.updateClientEntityFromClientDtoOnlyForActivated(clientDTO, findClient);
        clientRepository.save(findClient);
    }

    public List<ClientDtoOnlyForFirstAndLastNames> getDeactivatedAccounts() {
        List<ClientEntity> clients = clientRepository.findByActivatedFalse();
        List<ClientDtoOnlyForFirstAndLastNames> clientDtoOnlyForFirstAndLastNamesList = new ArrayList<>();

        clients.forEach(client -> clientDtoOnlyForFirstAndLastNamesList.add(clientMapper.ClientEntityToClientDtoOnlyForFirstAndLastNames(client)));

        if (clientDtoOnlyForFirstAndLastNamesList.isEmpty()) {
            throw new ClientNotFoundException("Nothing to show");
        }
        return clientDtoOnlyForFirstAndLastNamesList;
    }

    public List<ClientDTO> getAllClients() {
        List<ClientEntity> clientEntity = clientRepository.findAll();
        List<ClientDTO> clientDTOList = new ArrayList<>();

        clientEntity.forEach(client -> clientDTOList.add(clientMapper.ClientEntityToClientDto(client)));
        return clientDTOList;
    }

    public ClientDTO getASpecificClientByID(Long id) {
        ClientEntity clientEntity = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Client not found"));

        return clientMapper.ClientEntityToClientDto(clientEntity);
    }

    public void deleteClientByID(Long id) {
        clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException("Client not found. Cannot be deleted"));
        clientRepository.deleteById(id);
    }
}