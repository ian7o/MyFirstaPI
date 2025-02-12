package com.rentsclients.rentsandclients.Repository;

import com.rentsclients.rentsandclients.Entity.RentalsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<RentalsEntity, Long> {

    List<RentalsEntity> findByClientActivatedTrue();
    List<RentalsEntity> findByClientActivatedFalse();
}