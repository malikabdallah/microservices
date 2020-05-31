package vues;

import controleur.Controleur;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import modele.Oeuvre;
import modele.Salle;
import modele.facade;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EmprunterOeuvre implements Vue, Initializable {
    public TextField nom;
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



    public static EmprunterOeuvre creer(Stage stage) {
        URL location = EmprunterOeuvre.class.getResource("emprunterOeuvre.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        EmprunterOeuvre vue = fxmlLoader.getController();
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



    public void gotomenu(MouseEvent mouseEvent) {
        controleur.afficherMenu();
    }


    public void acheter(MouseEvent mouseEvent) {

        facade facade=new facade();
        Oeuvre oeuvre=new Oeuvre();
        oeuvre.setNomOeuvre(this.nom.getText());
        facade.emprunterOeuvre(oeuvre);

    }
}


