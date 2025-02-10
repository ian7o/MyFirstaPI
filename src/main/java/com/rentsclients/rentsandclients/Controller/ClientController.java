package com.rentsclients.rentsandclients.Controller;

import com.rentsclients.rentsandclients.Entity.ClientEntity;
import com.rentsclients.rentsandclients.service.ClientService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public void createAClient(@RequestBody ClientEntity clientEntity) {
       this.clientService.createAClient(clientEntity);
    }

    @GetMapping
    public void getAllClients() {
        ClientEntity clientEntity = new ClientEntity();
         clientService.getAllClientsService(clientEntity);
    }
        @GetMapping("/getASpecifiqueClientID/{id}")
    public void getASpecifiqueClientID(@PathVariable("id") Long id) {
        clientService.getASpecifiqueClientIDService(id);
    }

    @PutMapping("/updateAllClientInfos/{id}")
    public void updateClientByID(@PathVariable("id") long id, @RequestBody ClientEntity clientEntity) {
        clientService.updateClientByIDService(id, clientEntity);
    }

    @DeleteMapping("/{id}")
    public void deleteASpecifiqueClientID(@PathVariable("id") Long id) {
        clientService.deleteClientByIDService(id);
    }


    @GetMapping("/deactivatedAccounts/{accountIsActivated}")
    public void getDeactivatedAccounts(@PathVariable("accountIsActivated") String accountIsActivated) {
        clientService.getDeactivatedAccountsService(accountIsActivated);
    }

    @PutMapping("updateClientFirstNameAndLastName/{id}")
    public void updateClientFirstNameAndLastName(@PathVariable("id") long id, @RequestBody ClientEntity clientEntity) {
        clientService.updateClientFirstNameAndLastNameService(id,clientEntity);
    }

    @PutMapping("/activeOrDesativeClient/{id}")
    public void activeOrDesativeCLientByID(@PathVariable("id") long id, @RequestBody ClientEntity clientEntity) {
        clientService.activeOrDesativeCLientByIDService(id, clientEntity);
    }
}