package com.rentsclients.rentsandclients.Repository;

import com.rentsclients.rentsandclients.Entity.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, Long> {
//    List<CarEntity> findByActivatedTrue();

    List<CarEntity> findByActivatedTrue();

}