//package com.rentsclients.rentsandclients.Controller;
//
//import com.rentsclients.rentsandclients.DTOS.CarDTO;
//import com.rentsclients.rentsandclients.DTOS.RentalDTO;
//import com.rentsclients.rentsandclients.Entity.RentalsEntity;
//
//import com.rentsclients.rentsandclients.service.RentalService;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/rental")
//public class RentalController {
//
//    private final RentalService rentalService;
//
//    public RentalController(RentalService rentalService) {
//        this.rentalService = rentalService;
//    }
//
//
//    @PostMapping
//    public RentalDTO createRent(@RequestBody RentalDTO rentalDTO) {
//        return rentalService.createRent(rentalDTO);
//    }
//
//    @GetMapping
//    public List<RentalsEntity> getAllRents() {
//        return rentalService.getAllRents();
//    }
//
//    @PutMapping("/{id}")
//    public RentalDTO updateRentsByID(@PathVariable("id") long id, @RequestBody RentalDTO rentalDTO) {
//        return rentalService.updateRentsByID(id, rentalDTO);
//    }
//
//    @GetMapping("/deactivatedRentsAndCars")
//    public String getAllActiveClientsAndCars() {
//        return rentalService.getAllActiveClientsAndCarsService();
//    }
//
//    @DeleteMapping("/deleteSpecifiqueRentID/{id}")
//    public void deleteASpecifiqueRentByID(@PathVariable("id") Long id) {
//        rentalService.deleteASpecifiqueRentByID(id);
//    }
//
//    @GetMapping("/tesee")
//    public String getAllActiveClientsWithRentals(){
//        return rentalService.getAllActiveClientsWithRentals();
//    }
//    @GetMapping("/api/plate")
//    public String getPlateActiveClientVehicleDeactive(){
//        return rentalService.getPlates();
//    }
//}
