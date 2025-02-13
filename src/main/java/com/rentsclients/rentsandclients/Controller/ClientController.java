package com.rentsclients.rentsandclients.Controller;

import com.rentsclients.rentsandclients.DTOS.ClientDTO;
import com.rentsclients.rentsandclients.Entity.ClientEntity;
import com.rentsclients.rentsandclients.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> getASpecifiqueClientID(@PathVariable("id") Long id) {
        try {
            ClientDTO clientDTO = clientService.getASpecifiqueClientByID(id);
            return ResponseEntity.ok(clientDTO);
        } catch (Exception e) {
            System.out.println("error in getASpecifiqueClientID " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ID not found");
        }
    }


    @GetMapping
    public List<ClientEntity> getAllClients() {
        ClientEntity clientEntity = new ClientEntity();
        return clientService.getAllClients(clientEntity);
    }

    @PostMapping
    public ResponseEntity<ClientDTO> createClient(@RequestBody ClientDTO clientDTO) {
        ClientDTO saveClient = clientService.createAClient(clientDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveClient);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateClientByID(@PathVariable("id") long id, @RequestBody ClientDTO clientDTO) throws Exception {
        try {
            ClientDTO saveClient = clientService.updateClientByID(id, clientDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(saveClient);
        } catch (Exception e) {
            System.out.println("error in update: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("id Not find");
        }
    }

    @DeleteMapping("/{id}")
    public void deleteClientByID(@PathVariable("id") Long id) {
        clientService.deleteClientByID(id);
    }

    @GetMapping("/deactivatedAccounts")
    public ResponseEntity<?> getActivatedAccounts() {
        try {
            List<ClientDTO> searcc = clientService.getActivatedAccounts();
            if (searcc != null) {
                return ResponseEntity.ok().body(searcc);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Results");
            }
        } catch (Exception e) {
            System.out.println("errir in /deactivatedAccounts" + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Results");
        }
    }

    @PutMapping("updateClientFirstNameAndLastName/{id}")
    public ResponseEntity<?> updateClientFirstNameAndLastName(@PathVariable("id") long id, @RequestBody ClientDTO clientDTO) throws Exception {
        try {
            ClientDTO clientDTOUpdated = clientService.updateClientFirstNameAndLastName(id, clientDTO);
            if (clientDTOUpdated == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("notFind");
            } else {
                return ResponseEntity.ok().body(clientDTOUpdated);
            }
        } catch (Exception e) {
            System.out.println("error in updateClientFirstNameAndLastName: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Find ");
        }

    }

    @PutMapping("/activeOrDeactivateClient/{id}")
    public ResponseEntity<?> activeOrDeactivateClientByID(@PathVariable("id") long id, @RequestBody ClientDTO clientDTO) throws Exception {
        try {
            ClientDTO searchClient = clientService.activateOrDeactivateClientByID(id, clientDTO);
            if (searchClient == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ID not find");
            } else if (searchClient != null) {
                return ResponseEntity.status(HttpStatus.OK).body(searchClient);
            }
        }
        catch (Exception e){
            System.out.println("error in activeOrDeactivateClient: " + e.getMessage());
        }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ID not found");
    }
}