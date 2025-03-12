package com.rentsclients.rentsandclients.Controller;

import com.rentsclients.rentsandclients.DTOS.ClientDTO;
import com.rentsclients.rentsandclients.DTOS.ClientDtoOnlyForActivated;
import com.rentsclients.rentsandclients.DTOS.ClientDtoOnlyForFirstAndLastNames;
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
            if (bindingResult.hasErrors()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getFieldError().getDefaultMessage() + " In: " + bindingResult.getFieldError().getRejectedValue());
            }
            clientService.createAClient(clientDTO);
            return ResponseEntity.ok("Client created");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateClient(@PathVariable("id") long id, @Valid @RequestBody ClientDTO clientDTO, BindingResult bindingResult) {
            if (bindingResult.hasErrors()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getFieldError().getDefaultMessage() + " In: " + bindingResult.getFieldError().getRejectedValue());
            }
            clientService.updateClient(id, clientDTO);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Client updated");
    }

    //vai ser um para todos eu acho apenas vai mudando confrome os ifs talvez
//    @PutMapping("/{id}/updateFirstAndLastNames")
    @PatchMapping("/updateFirstAndLastName/{id}")
    public ResponseEntity<?> updateClientFirstNameAndLastName(@PathVariable("id") long id, @Valid @RequestBody ClientDtoOnlyForFirstAndLastNames clientDTO, BindingResult bindingResult) {
            if (bindingResult.hasErrors()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getFieldError().getDefaultMessage() + " In: " + bindingResult.getFieldError().getRejectedValue());
            }
            clientService.updateClientFirstAndLastName(id, clientDTO);
            return ResponseEntity.status(HttpStatus.OK).body("Client first and last names updated");
    }

    @PatchMapping("{id}/activeOrDeactivate")
    public ResponseEntity<?> activeOrDeactivateClientByID(@PathVariable("id") long id, @RequestBody ClientDtoOnlyForActivated clientDtoOnlyForActivated) {
            clientService.activateOrDeactivateClientByID(id, clientDtoOnlyForActivated);
            return ResponseEntity.status(HttpStatus.OK).body("Client updated");
    }

    @GetMapping("/deactivated")
    public ResponseEntity<?> getDeactivatedAccounts() {
            List<ClientDtoOnlyForFirstAndLastNames> clients = clientService.getDeactivatedAccounts();
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(clients);
    }

    @GetMapping
    public ResponseEntity<?> getAllClients() {
            return ResponseEntity.ok(clientService.getAllClients());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getASpecifiqueClientID(@PathVariable("id") Long id) {
            return ResponseEntity.ok(clientService.getASpecificClientByID(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClientByID(@PathVariable("id") Long id) {
            clientService.deleteClientByID(id);
            return ResponseEntity.ok("Client deleted");
    }
}