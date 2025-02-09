package com.rentsclients.rentsandclients.Controller;

import com.rentsclients.rentsandclients.Entity.ClientEntity;
import com.rentsclients.rentsandclients.Repository.ClientRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/client")
public class ClientController {
    private final ClientRepository repository;

    public ClientController(ClientRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public void createAClient(@RequestBody ClientEntity clientEntity) {
        repository.save(clientEntity);
    }

    @GetMapping
    public List<ClientEntity> getAllClients() {
        return repository.findAll();
    }

    @PutMapping("/{id}")
    public ClientEntity updateClientByID(@PathVariable("id") long id, @RequestBody ClientEntity clientEntity) {
        Optional<ClientEntity> findClient = repository.findById(id);
        if (findClient.isPresent()) {
            ClientEntity clientToUpDate = findClient.get();
            clientToUpDate.setAccountIsActivated(clientEntity.getAccountIsActivated());
            clientToUpDate.setFirstName(clientEntity.getFirstName());
            clientToUpDate.setLastName(clientEntity.getLastName());
            clientToUpDate.setNif(clientEntity.getNif());
            return repository.save(findClient.get());
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteASpecifiqueClientID(@PathVariable("id") Long id) {
        repository.deleteById(id);
    }

    @GetMapping("/getDeactivatedAccounts/{accountIsActivated}")
    public String getDeactivatedAccounts(@PathVariable("accountIsActivated") String accountIsActivated) {
        Optional<ClientEntity> findFirstAndLastNames = repository.findByaccountIsActivated(accountIsActivated);
        if (findFirstAndLastNames.isPresent()) {

            return findFirstAndLastNames.get().getFirstName() + " " + findFirstAndLastNames.get().getLastName();
        }
        return "not find";
    }

    @PutMapping("updateClientFirstNameAndLastName/{id}")
    public ClientEntity updateClientFirstNameAndLastName(@PathVariable("id") long id, @RequestBody ClientEntity clientEntity) {
        Optional<ClientEntity> findClient = repository.findById(id);
        if (findClient.isPresent()) {
            ClientEntity clientToUpDate = findClient.get();
            clientToUpDate.setFirstName(clientEntity.getFirstName());
            clientToUpDate.setLastName(clientEntity.getLastName());
            return repository.save(findClient.get());
        }
        return null;
    }

    @PutMapping("/activeOrDesativeClient/{id}")
    public ClientEntity activeOrDesativeCLientByID(@PathVariable("id") long id, @RequestBody ClientEntity clientEntity) {
        Optional<ClientEntity> findClient = repository.findById(id);
        if (findClient.isPresent()) {
            ClientEntity clientToUpDate = findClient.get();
            clientToUpDate.setAccountIsActivated(clientEntity.getAccountIsActivated());
            return repository.save(findClient.get());
        }
        return null;
    }

    @GetMapping("/getASpecifiqueClientID/{id}")
    public ClientEntity getASpecifiqueClientID(@PathVariable("id") Long id) {
        return repository.findById(id).orElse(null);
    }

}