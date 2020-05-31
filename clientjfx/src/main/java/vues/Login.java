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

public class Login implements Vue, Initializable {


    public TextField pass;
    public TextField pseudo;
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



    public static Login creer(Stage stage) {
        URL location = Login.class.getResource("login.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Login vue = fxmlLoader.getController();
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


    public void login(MouseEvent mouseEvent) {

        facade facade=new facade();
        int status=facade.login(this.pseudo.getText(),this.pass.getText());
        System.out.println("status "+status);
        if(status==200){
            controleur.afficherMenu();
        }else{
           // Notification.affichageEchec("echec","probleme d authentification");
        }
    }
}
