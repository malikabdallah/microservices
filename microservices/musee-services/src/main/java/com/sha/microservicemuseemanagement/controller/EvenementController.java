package com.sha.microservicemuseemanagement.controller;


import com.sha.microservicemuseemanagement.model.Evenement;
import com.sha.microservicemuseemanagement.service.EvenementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class EvenementController {





    @Autowired
    private EvenementService evenementService;

    @PostMapping("/evenements")
    public ResponseEntity<?> enregistrerEmprunt(@RequestBody Evenement evenement)
    {

        String aujourdhui = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(new Date());
        evenement.setDate(aujourdhui);


        return  new ResponseEntity<>(evenementService.enregistrerEvenement(evenement), HttpStatus.CREATED);
    }


    @GetMapping("/evenements/oeuvres/{idoeuvres}")
    public ResponseEntity<?>evenementsduneoeuvre(@PathVariable long idoeuvres){
        List<Evenement> list=evenementService.getEvenementByOeuvresId(idoeuvres);
        System.out.println("liste = "+list);
        return ResponseEntity.ok(list);

    }


}
