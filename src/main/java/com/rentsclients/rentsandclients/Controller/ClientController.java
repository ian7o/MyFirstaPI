package com.rentsclients.rentsandclients.Controller;

import com.rentsclients.rentsandclients.DTOS.ClientDTO;
import com.rentsclients.rentsandclients.Entity.ClientEntity;
import com.rentsclients.rentsandclients.Exceptions.ClientNotFoundException;
import com.rentsclients.rentsandclients.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/client")
public class ClientController {

    //    @Autowired
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }


    @PostMapping
    public ResponseEntity<?> createClient(@RequestBody ClientDTO clientDTO) {
        try {
            clientService.createAClient(clientDTO);
            return ResponseEntity.ok("Client created");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateClientByID(@PathVariable("id") long id, @RequestBody ClientDTO clientDTO) {
        try {
            clientService.updateClientByID(id, clientDTO);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Client updated");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(e.getMessage());
        }
    }

    @PutMapping("updateClientFirstNameAndLastName/{id}")
    public ResponseEntity<?> updateClientFirstNameAndLastName(@PathVariable("id") long id, @RequestBody ClientDTO clientDTO) {
        try {
            clientService.updateClientFirstNameAndLastName(id, clientDTO);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Client first and last names updated");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("ID Not found");
        }
    }

    @PutMapping("/activeOrDeactivateClient/{id}")
    public ResponseEntity<?> activeOrDeactivateClientByID(@PathVariable("id") long id, @RequestBody ClientDTO clientDTO) {
        try {
            clientService.activateOrDeactivateClientByID(id, clientDTO);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Client updated");
        } catch (ClientNotFoundException e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("ID Not found");
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllClients() {
        ClientEntity clientEntity = new ClientEntity();
        try {
            return ResponseEntity.ok(clientService.getAllClients(clientEntity));
        } catch (RuntimeException e) {
            System.out.println("Error in getAllClients: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }

    //
    @GetMapping("/{id}")
    public ResponseEntity<?> getASpecifiqueClientID(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(clientService.getASpecificClientByID(id));
        } catch (Exception e) {
            System.out.println("Error in getASpecifiqueClientID: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    //
//        @GetMapping("/deactivatedAccounts")
//    public ResponseEntity<?> getActivatedAccounts() {
//        try {
//            return ResponseEntity.status(HttpStatus.ACCEPTED).body(clientService.getActivatedAccounts());
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.PRECONDITION_REQUIRED).body(e.getMessage());
//        }
//    }
//
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClientByID(@PathVariable("id") Long id) {
        try {
            clientService.deleteClientByID(id);
            return ResponseEntity.ok("Client deleted");
        } catch (Exception e) {
            System.out.println("Error in deleteClientByID: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(e.getMessage());
        }

    }
}