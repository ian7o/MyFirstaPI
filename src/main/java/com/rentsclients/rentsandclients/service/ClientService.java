package com.rentsclients.rentsandclients.service;

import com.rentsclients.rentsandclients.Entity.ClientEntity;
import com.rentsclients.rentsandclients.Repository.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;


    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void createAClient(ClientEntity client) {
        this.clientRepository.save(client);
    }

    public List<ClientEntity> getAllClientsService(ClientEntity client) {
        List<ClientEntity> listWithAllClients = clientRepository.findAll();
        return listWithAllClients;
    }

    public ClientEntity getASpecifiqueClientIDService(@PathVariable("id") Long id) {
        ClientEntity clientRepository1 =clientRepository.findById(id).orElse(null);
        return clientRepository1;
    }


    public ClientEntity updateClientByIDService(@PathVariable("id") long id, @RequestBody ClientEntity clientEntity) {
        Optional<ClientEntity> findClient = clientRepository.findById(id);
        if (findClient.isPresent()) {
            ClientEntity clientToUpDate = findClient.get();
            clientToUpDate.setAccountIsActivated(clientEntity.getAccountIsActivated());
            clientToUpDate.setFirstName(clientEntity.getFirstName());
            clientToUpDate.setLastName(clientEntity.getLastName());
            clientToUpDate.setNif(clientEntity.getNif());
            return clientRepository.save(findClient.get());
        }
        return null;
    }

    public void deleteClientByIDService(@PathVariable("id") Long id) {
        clientRepository.deleteById(id);
    }

    public String getDeactivatedAccountsService(@PathVariable("accountIsActivated") String accountIsActivated) {
        List<ClientEntity> findd = clientRepository.findByaccountIsActivated(accountIsActivated);
        if (!findd.isEmpty()) {
            String firstsAndLastsNames = " ";
            for (int i = 0; i < findd.size(); i++) {
                firstsAndLastsNames += findd.get(i).getFirstName() + " " + findd.get(i).getLastName() + ", ";
            }
            return firstsAndLastsNames;
        }
        return "not find";
    }

    public ClientEntity updateClientFirstNameAndLastNameService (@PathVariable("id") long id, @RequestBody ClientEntity clientEntity) {
        Optional<ClientEntity> findClient = clientRepository.findById(id);
        if (findClient.isPresent()) {
            ClientEntity clientToUpDate = findClient.get();
            clientToUpDate.setFirstName(clientEntity.getFirstName());
            clientToUpDate.setLastName(clientEntity.getLastName());
            return clientRepository.save(findClient.get());
        }
        return null;
    }

    public ClientEntity activeOrDesativeCLientByIDService(@PathVariable("id") long id, @RequestBody ClientEntity clientEntity) {
        Optional<ClientEntity> findClient = clientRepository.findById(id);
        if (findClient.isPresent()) {
            ClientEntity clientToUpDate = findClient.get();
            clientToUpDate.setAccountIsActivated(clientEntity.getAccountIsActivated());
            return clientRepository.save(findClient.get());
        }
        return null;
    }
}