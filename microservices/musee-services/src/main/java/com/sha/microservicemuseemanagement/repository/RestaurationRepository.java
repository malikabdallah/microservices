package com.sha.microservicemuseemanagement.repository;


import com.sha.microservicemuseemanagement.model.Restauration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurationRepository extends JpaRepository<Restauration,Long> {
    Restauration findById(long id);
}
