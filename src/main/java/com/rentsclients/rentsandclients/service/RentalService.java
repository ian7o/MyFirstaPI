package com.rentsclients.rentsandclients.service;

import com.rentsclients.rentsandclients.Entity.CarEntity;
import com.rentsclients.rentsandclients.Entity.ClientEntity;
import com.rentsclients.rentsandclients.Entity.RentalsEntity;
import com.rentsclients.rentsandclients.Repository.CarRepository;
import com.rentsclients.rentsandclients.Repository.ClientRepository;
import com.rentsclients.rentsandclients.Repository.RentalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RentalService {
    private final RentalRepository rentalRepository;

    private final ClientRepository clientRepository;

    private final CarRepository carRepository;

    public RentalService(RentalRepository rentalRepository, ClientRepository clientRepository, CarRepository carRepository) {
        this.rentalRepository = rentalRepository;
        this.clientRepository = clientRepository;
        this.carRepository = carRepository;
    }

    public RentalsEntity createRent(RentalsEntity rentalsEntity) {
        return rentalRepository.save(rentalsEntity);
    }

    public List<RentalsEntity> getAllRents() {
        return rentalRepository.findAll();
    }

    public RentalsEntity updateRentsByID(long id, RentalsEntity rentalsEntity) {
        Optional<RentalsEntity> findRent = rentalRepository.findById(id);
        if (findRent.isPresent()) {
            findRent.get().setClient(rentalsEntity.getClient());
            findRent.get().setCar(rentalsEntity.getCar());
            findRent.get().setStartTime(rentalsEntity.getStartTime());
            findRent.get().setEndTime(rentalsEntity.getEndTime());
            return rentalRepository.save(findRent.get());
        }
        return null;
    }

    public String getAllActiveClientsAndCarsService() {
        List<ClientEntity> searchClients = clientRepository.findByActivatedTrue();
        List<CarEntity> searchCars = carRepository.findByActivatedTrue();

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

    public void deleteASpecifiqueRentByID(Long id) {
        rentalRepository.deleteById(id);
    }

    public String getAllActiveClientsWithRentals(){
        List<RentalsEntity> searchClient = rentalRepository.findByClientActivatedTrue();
        if (!searchClient.isEmpty()){
            String firstnames =" ";
            for (RentalsEntity aa : searchClient){
                firstnames += aa.getClient().getFirstName() +", ";
            }
            return firstnames;
        }
        return null;
    }

    public String getPlates(){
        List<RentalsEntity> entities = rentalRepository.findByClientActivatedFalse();
        if (!entities.isEmpty()){
            String plates= " ";
            for (RentalsEntity bb: entities) {
                plates += bb.getCar().getPlate();
            }
            return plates;
        }
        return null;
    }
}