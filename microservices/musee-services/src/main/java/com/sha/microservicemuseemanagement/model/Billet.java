package com.sha.microservicemuseemanagement.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name ="billet")
public class Billet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Exposition exposition;

    @Column(name = "is_utilise")
    private Boolean isUtilise;

    @Column (name = "qr_code")
    private String qrCode;

    public Long getId() {
        return id;
    }

    public Boolean getUtilise() {
        return isUtilise;
    }

    public void setUtilise(Boolean utilise) {
        isUtilise = utilise;
    }


}
