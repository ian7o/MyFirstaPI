package com.rentsclients.rentsandclients.Repository;

import com.rentsclients.rentsandclients.Entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

    Optional<ClientEntity> findByaccountIsActivated(String accountIsActivated);
}
