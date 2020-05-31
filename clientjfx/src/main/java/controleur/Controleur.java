package controleur;

import javafx.stage.Stage;

import vues.*;

public class Controleur {
    private ChoisirSalle choisirSalle;
    private VueAccueil vueAccueil;
    private AcheterOeuvre acheterOeuvre;
    private Ajoutsalle ajoutsalle;
    private Expose expose;
    private EmprunterOeuvre emprunterOeuvre;
    private Menu menuMusee;
    private ConsulterOeuvre consulterOeuvre;
    private CreerExposition creerExposition;
    private OrganiserExposition organiserExposition;
    private ConsulterExposition consulterExposition;
    private Historique historique;
    private Login login;


    public void organiserExpo(){
        organiserExposition.show();
    }

    public void lancerConsulterExposition(){
        consulterExposition.show();
    }
    public Controleur(Stage stage) {
        historique=Historique.creer(stage);
        historique.initialiserControleur(this);
        consulterExposition=ConsulterExposition.creer(stage);
        consulterExposition.initialiserControleur(this);
        organiserExposition=OrganiserExposition.creer(stage);
        organiserExposition.initialiserControleur(this);
        choisirSalle=ChoisirSalle.creer(stage);
        choisirSalle.initialiserControleur(this);
        creerExposition=CreerExposition.creer(stage);
        creerExposition.initialiserControleur(this);
        emprunterOeuvre=EmprunterOeuvre.creer(stage);
        emprunterOeuvre.initialiserControleur(this);
        menuMusee=Menu.creer(stage);
        menuMusee.initialiserControleur(this);
        vueAccueil = VueAccueil.creer(stage);
        vueAccueil.initialiserControleur(this);
        ajoutsalle=Ajoutsalle.creer(stage);
        ajoutsalle.initialiserControleur(this);
        expose=Expose.creer(stage);
        expose.initialiserControleur(this);
        consulterOeuvre=ConsulterOeuvre.creer(stage);
        consulterOeuvre.initialiserControleur(this);
        acheterOeuvre=AcheterOeuvre.creer(stage);
        acheterOeuvre.initialiserControleur(this);
        login=Login.creer(stage);
        login.initialiserControleur(this);
    }



    public void emprunterOeuvre(){
        emprunterOeuvre.show();
    }

    public void exposerOeuvre(){
        expose.show();
    }

    public void run() {
        login.show();
        //menuMusee.show();
    }







    public void afficherMenu() {
        menuMusee.show();
    }

    public void lancerConsulterSalle() {
        vueAccueil.show();
    }

    public void AcheterOeuvre(){
        acheterOeuvre.show();
    }

    public void lancerVueAjouterSalle() {
        ajoutsalle.show();
    }

    public void consulterOeuvres() {
        consulterOeuvre.show();
    }

    public void creerExposition() {
        creerExposition.show();
    }

    public void lancerChoisirSalle() {
        choisirSalle.show();
    }

    public void afficherLogin() {
        login.show();
    }

    public void lancerHistorique() {
        historique.show();
    }
}
