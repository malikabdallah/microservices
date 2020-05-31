package com.sha.microservicemuseemanagement.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name ="restauration")
public class Restauration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "id_oeuvre")
    private Long idOeuvre;

    @Column (name = "motif")
    private String motif;

    @Column (name = "date_restauration")
    private Date dateRestauration;

    /* getters et setters*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdOeuvre() {
        return idOeuvre;
    }

    public void setIdOeuvre(Long idOeuvre) {
        this.idOeuvre = idOeuvre;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public Date getDateRestauration() {
        return dateRestauration;
    }

    public void setDateRestauration(Date dateRestauration) {
        this.dateRestauration = dateRestauration;
    }
}
