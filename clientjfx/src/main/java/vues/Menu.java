package vues;

import controleur.Controleur;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import modele.facade;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Menu implements Vue, Initializable {
    private Controleur controleur;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @Override
    public void show() {
        this.stage.setScene(this.scene);
        this.stage.show();
    }

    @Override
    public void chargerDonnees() {

    }



    public static Menu creer(Stage stage) {
        URL location = Menu.class.getResource("menu.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Menu vue = fxmlLoader.getController();
        vue.setStage(stage);
        Scene scene = new Scene(root);
        vue.setScene(scene);
        return vue;
    }



    private void setScene(Scene scene) {
        this.scene = scene;
    }
    private void setStage(Stage stage) {
        this.stage = stage;
    }


    private Scene scene;
    private Stage stage;

    @Override
    public void initialiserControleur(Controleur controleur) {
        this.controleur=controleur;
    }

    public void goconsultersalle(MouseEvent mouseEvent) {
        this.controleur.lancerConsulterSalle();
    }

    public void ajoutersalle(MouseEvent mouseEvent) {
        this.controleur.lancerVueAjouterSalle();
    }

    public void consulterOeuvre(MouseEvent mouseEvent) {
        this.controleur.consulterOeuvres();
    }

    public void oeuvreconsulter(MouseEvent mouseEvent) {
        System.out.println("consulter oeuvres");
        this.controleur.consulterOeuvres();
    }

    public void acheterOeuvre(MouseEvent mouseEvent) {
        controleur.AcheterOeuvre();
    }

    public void emprunterOeuvre(MouseEvent mouseEvent) {
        controleur.emprunterOeuvre();
    }

    public void creerExposition(MouseEvent mouseEvent) {
        controleur.creerExposition();
    }

    public void organiserExposition(MouseEvent mouseEvent) {
        controleur.organiserExpo();
    }

    public void consulterExposition(MouseEvent mouseEvent) {
        controleur.lancerConsulterExposition();
    }

    public void deconnexion(MouseEvent mouseEvent) {
        facade facade=new facade();
        facade.logout();
        controleur.afficherLogin();
    }
}
