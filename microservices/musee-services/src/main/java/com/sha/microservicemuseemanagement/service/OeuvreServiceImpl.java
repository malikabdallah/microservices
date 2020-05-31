package com.sha.microservicemuseemanagement.service;

import com.sha.microservicemuseemanagement.model.Oeuvre;
import com.sha.microservicemuseemanagement.repository.OeuvreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OeuvreServiceImpl implements OeuvreService {

    @Autowired
    OeuvreRepository oeuvreRepository;

    @Override
    public List<Oeuvre> allOeuvre() {
        return oeuvreRepository.findAll() ;
    }

    @Override
    public Oeuvre getOeuvreById(long id) {
        return oeuvreRepository.findById(id);
    }

    @Override
    public Oeuvre ajouterOeuvre(Oeuvre oeuvre) {
        return oeuvreRepository.save(oeuvre);
    }

    @Override
    public void supprimerOeuvre(Oeuvre oeuvre) {
        oeuvreRepository.delete(oeuvre);
    }

    @Override
    public List<Oeuvre> trouverOeuvresEnReserve() {
        return  oeuvreRepository.findByIsExposeFalse();
    }

    @Override
    public List<Oeuvre> trouveOeuvresExpose() {
        return oeuvreRepository.findByIsExposeTrue();
    }

    @Override
    public List<Oeuvre> trouverPret() {
        return  oeuvreRepository.findByisAcheteFalse();
    }


    @Override
    public List<Oeuvre> trouverOeuvresEnRestauration() {
        return oeuvreRepository.findByIsRestaureTrue();
    }


}
