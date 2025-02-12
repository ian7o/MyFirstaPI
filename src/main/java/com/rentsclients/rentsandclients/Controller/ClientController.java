package com.rentsclients.rentsandclients.Controller;

import com.rentsclients.rentsandclients.Entity.ClientEntity;
import com.rentsclients.rentsandclients.service.ClientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/{id}")
    public ClientEntity getClientById(@PathVariable("id") Long id) {
      return  clientService.getASpecifiqueClientID(id);
    }

    @GetMapping
    public List<ClientEntity> getAllClients() {
        ClientEntity clientEntity = new ClientEntity();
        return clientService.getAllClients(clientEntity);
    }

    @PostMapping
    public ClientEntity createClient(@RequestBody ClientEntity clientEntity) {
      return this.clientService.createAClient(clientEntity);
    }

    @PutMapping("/update/{id}")
    public ClientEntity updateClientByID(@PathVariable("id") long id, @RequestBody ClientEntity clientEntity) {
        return clientService.updateClientByID(id, clientEntity);
    }

    @DeleteMapping("/{id}")
    public void deleteClientByID(@PathVariable("id") Long id) {
        clientService.deleteClientByID(id);
    }

    @GetMapping("/deactivatedAccounts")
    public String getActivatedAccounts(){
       return clientService.getActivatedAccounts();
    }

    @PutMapping("updateClientFirstNameAndLastName/{id}")
    public ClientEntity updateClientFirstNameAndLastName(@PathVariable("id") long id, @RequestBody ClientEntity clientEntity) {
      return  clientService.updateClientFirstNameAndLastName(id, clientEntity);
    }

    @PutMapping("/activeOrDeactivateClient/{id}")
    public ClientEntity activeOrDeactivateClientByID(@PathVariable("id") long id,@RequestBody ClientEntity clientEntity) {
        return clientService.activateOrDeactivateClientByID(id, clientEntity);
    }
}