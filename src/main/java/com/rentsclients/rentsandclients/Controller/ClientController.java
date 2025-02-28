package com.rentsclients.rentsandclients.Controller;

import com.rentsclients.rentsandclients.DTOS.ClientDTO;
import com.rentsclients.rentsandclients.DTOS.ClientDtoOnlyForFirstAndLastNames;
import com.rentsclients.rentsandclients.Entity.ClientEntity;
import com.rentsclients.rentsandclients.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/v1/client")
public class ClientController {

    private final ClientService clientService;




    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }


    @PostMapping
    public ResponseEntity<?> createClient(@Valid @RequestBody ClientDTO clientDTO, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getFieldError().getDefaultMessage() + " In: " + bindingResult.getFieldError().getRejectedValue());
            }
            clientService.createAClient(clientDTO);
            return ResponseEntity.ok("Client created");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateClient( @PathVariable("id") long id, @Valid @RequestBody ClientDTO clientDTO, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getFieldError().getDefaultMessage()  + " In: "+ bindingResult.getFieldError().getRejectedValue());
            }
            clientService.updateClient(id, clientDTO);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Client updated");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ssss");
        }
    }

    //vai ser um para todos eu acho apenas vai mudando confrome os ifs talvez
//    @PutMapping("/{id}/updateFirstAndLastNames")
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateClientFirstNameAndLastName(@PathVariable("id") long id, @Valid @RequestBody ClientDTO clientDTO, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getFieldError().getDefaultMessage() + " In: " + bindingResult.getFieldError().getRejectedValue());
            }
            clientService.updateClientFirstAndLastName(id, clientDTO);
            return ResponseEntity.status(HttpStatus.OK).body("Client first and last names updated");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("{id}/activeOrDeactivate")
    public ResponseEntity<?> activeOrDeactivateClientByID(@PathVariable("id") long id, @RequestBody ClientDTO clientDTO) {
        try {
            clientService.activateOrDeactivateClientByID(id, clientDTO);
            return ResponseEntity.status(HttpStatus.OK).body("Client updated");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/deactivated")
    public ResponseEntity<?> getDeactivatedAccounts() {
        try {
            List<ClientDtoOnlyForFirstAndLastNames> clients = clientService.getDeactivatedAccounts();
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(clients);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllClients() {
        ClientEntity clientEntity = new ClientEntity();
        try {
            return ResponseEntity.ok(clientService.getAllClients());
        } catch (RuntimeException e) {
            System.out.println("Error in getAllClients: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getASpecifiqueClientID(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(clientService.getASpecificClientByID(id));
        } catch (RuntimeException e) {
            System.out.println("Error in getASpecifiqueClientID: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClientByID(@PathVariable("id") Long id) {
        try {
            clientService.deleteClientByID(id);
            return ResponseEntity.ok("Client deleted");
        } catch (RuntimeException e) {
            System.out.println("Error in deleteClientByID: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }
}