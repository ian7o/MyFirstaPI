package com.rentsclients.rentsandclients.Controller;

import com.rentsclients.rentsandclients.Entity.CarEntity;
import com.rentsclients.rentsandclients.Entity.ClientEntity;
import com.rentsclients.rentsandclients.Entity.RentalsEntity;
import com.rentsclients.rentsandclients.Repository.CarRepository;
import com.rentsclients.rentsandclients.Repository.ClientRepository;
import com.rentsclients.rentsandclients.Repository.RentalRepository;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rental")
public class RentalController {

    private final RentalRepository rentalRepository;

    private final ClientRepository clientRepository;

    private final CarRepository carRepository;

    public RentalController(RentalRepository rentalRepository, ClientRepository clientRepository, CarRepository carRepository) {
        this.rentalRepository = rentalRepository;
        this.clientRepository = clientRepository;
        this.carRepository = carRepository;
    }

    @PostMapping
    public void createARents(@RequestBody RentalsEntity rentalsEntity) {
        rentalRepository.save(rentalsEntity);
    }

    @GetMapping
    public List<RentalsEntity> getAllRents() {
        return rentalRepository.findAll();
    }

//    @PutMapping("/updateAllRentsssInfos/{id}")
//    public void updateRentsByID(@PathVariable("id") long id, @RequestBody RentalsEntity rentalsEntity) {
//        rentalService.updateRentById(id);
//    }

    @PutMapping("/updateAllRentsssInfos/{id}")
    public void updateRentsByID(@PathVariable("id") long id, @RequestBody RentalsEntity rentalsEntity) {
        Optional<RentalsEntity> findClient = rentalRepository.findById(id);
        if (findClient.isPresent()) {
            RentalsEntity rentsToUpDate = findClient.get();
            rentsToUpDate.setCar(rentalsEntity.getCar());
            rentsToUpDate.setClient(rentalsEntity.getClient());
            rentalRepository.save(findClient.get());
        }
    }

    @GetMapping("/deactivatedAccounts/{accountIsActivated}")
    public String getDeactivatedAccounts(@PathVariable("accountIsActivated") String accountIsActivated) {
        List<ClientEntity> searchClients = clientRepository.findByaccountIsActivated(accountIsActivated);
        List<CarEntity> searchCars = carRepository.findBycarIsActivated(accountIsActivated);

        String carPlates = " ";
        if (!searchCars.isEmpty()) {
            for (CarEntity searchCar : searchCars) {
                carPlates += searchCar.getPlate() + ", ";
            }
        }

        String firstsAndLastsNames = " ";
        if (!searchClients.isEmpty()) {
            for (ClientEntity searchClient : searchClients) {
                firstsAndLastsNames += searchClient.getFirstName() + " " + searchClient.getLastName() + ", ";
            }

            return "clients activated: " + firstsAndLastsNames + "cars activated" + carPlates;
        }
        return "not find";
    }


    @DeleteMapping("/deleteASpecifiqueRentByID/{id}")
    public void deleteASpecifiqueRentByID(@PathVariable("id") Long id) {
        rentalRepository.deleteById(id);
    }
}
