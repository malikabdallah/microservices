package modele;


import com.fasterxml.jackson.annotation.JsonProperty;

public class Evenement {



    @JsonProperty("id")
    private Long id;


    @JsonProperty("idOeuvre")
    private  long idOeuvre;


    @JsonProperty("evenement")
    private String evenement;

    @JsonProperty("date")
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
