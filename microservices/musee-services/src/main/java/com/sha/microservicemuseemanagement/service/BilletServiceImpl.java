package com.sha.microservicemuseemanagement.service;

import com.sha.microservicemuseemanagement.model.Billet;
import com.sha.microservicemuseemanagement.repository.BilletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BilletServiceImpl  implements BilletService {

    @Autowired
    private BilletRepository billetRepository;
    @Override
    public List<Billet> allBillet() {
        return billetRepository.findAll();
    }

    @Override
    public Billet getBilletById(long id) {
        return billetRepository.findById(id);
    }

    @Override
    public Billet AjouterUnBillet(Billet billet) {
        return billetRepository.save(billet);
    }
}
