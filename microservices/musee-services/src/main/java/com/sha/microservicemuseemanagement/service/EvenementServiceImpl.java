package com.sha.microservicemuseemanagement.service;

import com.sha.microservicemuseemanagement.model.Evenement;
import com.sha.microservicemuseemanagement.repository.EvenementRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvenementServiceImpl  implements EvenementService{



    @Autowired
    private EvenementRepo evenementRepo;


    @Override
    public Evenement enregistrerEvenement(Evenement evenement) {
        return evenementRepo.save(evenement);
    }

    @Override
    public List<Evenement> getEvenementByOeuvresId(long idoeuvres) {
        return evenementRepo.findByIdOeuvre(idoeuvres);
    }
}
