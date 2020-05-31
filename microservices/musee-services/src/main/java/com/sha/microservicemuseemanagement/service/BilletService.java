package com.sha.microservicemuseemanagement.service;

import com.sha.microservicemuseemanagement.model.Billet;

import java.util.List;

public interface BilletService {
    List<Billet> allBillet();
    Billet getBilletById(long id);
    Billet AjouterUnBillet(Billet billet);
}
