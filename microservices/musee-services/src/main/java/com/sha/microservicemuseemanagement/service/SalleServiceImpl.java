package com.sha.microservicemuseemanagement.service;

import com.sha.microservicemuseemanagement.model.Salle;
import com.sha.microservicemuseemanagement.repository.SalleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalleServiceImpl implements SalleService {

    @Autowired
    SalleRepository salleRepository;


    @Override
    public List<Salle> allSalle() {
        return  salleRepository.findAll();
    }

    @Override
    public Salle getSalleById(long id) {
        return salleRepository.findById(id);
    }

    @Override
    public Salle ajouterSalle(Salle salle) {
        return salleRepository.save(salle);

    }


    @Override
    public List<Salle> getSallesDisponibles() {
        return salleRepository.findByIsDisponibleTrue();
    }
}
