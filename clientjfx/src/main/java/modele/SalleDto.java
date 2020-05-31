package modele;

public class SalleDto {

    public long id;
    public String nom;

    public SalleDto(long id,String nom) {
        this.nom = nom;
        this.id=id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
