package com.sha.microservicemuseemanagement.model;

import javax.persistence.*;


@Entity
@Table(name = "evenement")
public class Evenement {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @Column(name = "id_oeuvre")
    private  long idOeuvre;

    private String evenement;

    private String date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getIdOeuvre() {
        return idOeuvre;
    }

    public void setIdOeuvre(long idOeuvre) {
        this.idOeuvre = idOeuvre;
    }

    public String getEvenement() {
        return evenement;
    }

    public void setEvenement(String evenement) {
        this.evenement = evenement;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
