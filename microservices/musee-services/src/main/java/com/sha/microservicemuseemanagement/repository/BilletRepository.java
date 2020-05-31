package com.sha.microservicemuseemanagement.repository;

import com.sha.microservicemuseemanagement.model.Billet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BilletRepository extends JpaRepository<Billet,Long> {

    Billet findById(long id);
}
