package com.sha.microservicemuseemanagement.service;

import com.sha.microservicemuseemanagement.model.Exposition;
import com.sha.microservicemuseemanagement.model.Oeuvre;
import com.sha.microservicemuseemanagement.repository.ExpositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpositionServiceImpl implements ExpositionService {


    @Autowired
    private ExpositionRepository expositionRepository;

    @Override
    public List<Exposition> allCourses() {
        return expositionRepository.findAll();

    }

    @Override
    public Exposition trouverExpositionParId(long id) {
        return  expositionRepository.findById(id);
    }


    @Override
    public Exposition enregistreExposition(Exposition transaction) {
        return  expositionRepository.save(transaction);
    }

    @Override
    public void ajouerOeuvreAUneExposition(long expositionId, Oeuvre oeuvre) {

    }

    /*
    @Override
    public void ajouerOeuvreAUneExposition(long expositionId, Oeuvre oeuvre) {
        Exposition exposition = trouverExpositionParId(expositionId);
        if (oeuvre.getRestaure().equals(false) || oeuvre.getExposition().equals(false)) {
            exposition.getOeuvres().add(oeuvre);
            expositionRepository.save(exposition);
        }
    }

     */
}
