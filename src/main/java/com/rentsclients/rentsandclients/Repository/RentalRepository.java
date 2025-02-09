package com.rentsclients.rentsandclients.Repository;

import com.rentsclients.rentsandclients.Entity.RentalsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<RentalsEntity, Long> {

}
