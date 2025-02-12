package com.rentsclients.rentsandclients.Controller;

import com.rentsclients.rentsandclients.Entity.RentalsEntity;

import com.rentsclients.rentsandclients.service.RentalService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rental")
public class RentalController {

    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }


    @PostMapping
    public RentalsEntity createRent(@RequestBody RentalsEntity rentalsEntity) {
        return rentalService.createRent(rentalsEntity);
    }

    @GetMapping
    public List<RentalsEntity> getAllRents() {
        return rentalService.getAllRents();
    }

    @PutMapping("/{id}")
    public RentalsEntity updateRentsByID(@PathVariable("id") long id, @RequestBody RentalsEntity rentalsEntity) {
        return rentalService.updateRentsByID(id, rentalsEntity);
    }

    @GetMapping("/deactivatedRentsAndCars")
    public String getAllActiveClientsAndCars() {
        return rentalService.getAllActiveClientsAndCarsService();
    }

    @DeleteMapping("/deleteSpecifiqueRentID/{id}")
    public void deleteASpecifiqueRentByID(@PathVariable("id") Long id) {
        rentalService.deleteASpecifiqueRentByID(id);
    }

    @GetMapping("/tesee")
    public String getAllActiveClientsWithRentals(){
        return rentalService.getAllActiveClientsWithRentals();
    }
    @GetMapping("/api/plate")
    public String getPlateActiveClientVehicleDeactive(){
        return "cars plates with DeactivedCLients " +rentalService.getPlates();
    }
}
