package com.rentsclients.rentsandclients.Repository;

import com.rentsclients.rentsandclients.DTOS.ClientDTO;
import com.rentsclients.rentsandclients.Entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
    List<ClientEntity> findByActivatedFalse();
    List<ClientEntity> findByActivatedTrue();

    boolean existsByNif(int nif);

    Optional<ClientEntity> findByNif(Integer nif);
}
