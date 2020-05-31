package com.sha.microservicemuseemanagement.service;

import com.sha.microservicemuseemanagement.model.Restauration;
import com.sha.microservicemuseemanagement.repository.RestaurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurationServiceImpl implements RestaurationService {
    @Autowired
    RestaurationRepository restaurationRepository;

    @Override
    public List<Restauration> allRestauration() {
        return restaurationRepository.findAll();
    }

    @Override
    public Restauration getRestaurationById(long id) {
        return restaurationRepository.findById(id);
    }

    @Override
    public Restauration ajouterRestauration(Restauration restauration) {
        return restaurationRepository.save(restauration);
    }
}
