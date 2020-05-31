package com.sha.microservicemuseemanagement.controller;

import com.sha.microservicemuseemanagement.intercomm.UserClient;
import com.sha.microservicemuseemanagement.model.Billet;
import com.sha.microservicemuseemanagement.model.Restauration;
import com.sha.microservicemuseemanagement.repository.CommentRepo;
import com.sha.microservicemuseemanagement.repository.ExpositionRepository;
import com.sha.microservicemuseemanagement.repository.OeuvreRepository;
import com.sha.microservicemuseemanagement.repository.SalleRepository;
import com.sha.microservicemuseemanagement.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MuseeController {
    @Autowired
    private UserClient userClient;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private ExpositionService expositionService;

    @Autowired
    private SalleService salleService;

    @Autowired
    private OeuvreService oeuvreService;

    @Autowired
    private BilletService billetService;

    @Autowired
    private RestaurationService restaurationService;

    @Autowired
    private Environment env;

    @Autowired
    private OeuvreRepository oeuvreRepository;


    @Autowired
    private ExpositionRepository expositionRepository;

    @Value("${spring.application.name}")
    private String serviceId;


    /*Billet*/

    @GetMapping(value = "/billets")
    public ResponseEntity<?> findAllBillet()
    {
        return ResponseEntity.ok(billetService.allBillet());
    }

    @GetMapping(value = "/billets/{billetId}")
    public ResponseEntity<?> findBilletById(@PathVariable long billetId)
    {
        return ResponseEntity.ok(billetService.getBilletById(billetId));
    }
    @Autowired
    private SalleRepository salleRepository;

    @GetMapping(value = "/test")
    public void test(){

        this.expositionRepository.deleteAll();
        this.oeuvreRepository.deleteAll();
        this.salleRepository.deleteAll();
    }
    @PostMapping(value = "/billets")
    public ResponseEntity<?> enregistrerBillet(@RequestBody Billet billet)
    {

        return new ResponseEntity<>(billetService.AjouterUnBillet(billet),HttpStatus.CREATED);
    }

    /*restauration*/

    @GetMapping(value = "/restaurations")
    public ResponseEntity<?> findAllRestauration()
    {
        return ResponseEntity.ok(restaurationService.allRestauration());
    }

    @GetMapping(value = "/restaurations/{restaurationId}")
    public ResponseEntity<?> findRestaurationById(@PathVariable long restaurationId)
    {
        return ResponseEntity.ok(restaurationService.getRestaurationById(restaurationId));
    }

    @PostMapping(value = "/restaurations")
    public ResponseEntity<?> enregistrerRestauration(@RequestBody Restauration restauration )
    {
        return new ResponseEntity<>(restaurationService.ajouterRestauration(restauration),HttpStatus.CREATED);
    }


    @Autowired
    CommentRepo commentRepo;


    @GetMapping(value = "/commentaires/{id}")
    public ResponseEntity<?>getCommentaireById(@PathVariable long id){
        return ResponseEntity.ok(commentRepo.findById(id));
    }

    @GetMapping(value = "/commentaires")
    public ResponseEntity<?>getCommentaires(){
        return ResponseEntity.ok(commentRepo.findAll());
    }
}
