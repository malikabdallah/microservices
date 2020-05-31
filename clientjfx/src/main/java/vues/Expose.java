package vues;

import controleur.Controleur;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import modele.Oeuvre;
import modele.Salle;
import modele.SalleDto;
import modele.facade;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class Expose implements Vue, Initializable {


    public static String ID;

    public ListView listeSalles;
    public Label id;
    public Label nom;
    public Label nomSalle;
    public Button expose;
    public Label idsalle;
    //public TreeTableView<Car> treetable;
    @FXML
    TableView<SalleDto> treeTableView = new TableView<>();

    @FXML
    TextField pseudo;

    @FXML
    Button go;

    private Stage stage;
    private Controleur controleur;


    private void setScene(Scene scene) {
        this.scene = scene;
    }
    private void setStage(Stage stage) {
        this.stage = stage;
    }

    private Scene scene;


    public static Expose creer(Stage stage) {
        URL location = VueAccueil.class.getResource("expose.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Expose vue = fxmlLoader.getController();
        vue.setStage(stage);
        Scene scene = new Scene(root);
        vue.setScene(scene);
        vue.initialiserBouton();
        return vue;
    }

    @Override
    public void show() {
        this.stage.setScene(this.scene);
        facade facade=new facade();
        Oeuvre oeuvre=facade.getOeuvreById(Long.parseLong(ID));
        this.id.setText(oeuvre.getId().toString());
        this.nom.setText(oeuvre.getNomOeuvre());
        this.chargerSalles();
        this.stage.show();
    }

    private void initialiserBouton() {
        //this.go.setOnAction(e -> connexion());
    }


    @Override
    public void chargerDonnees() {

    }

    @Override
    public void initialiserControleur(Controleur controleur) {
        this.controleur = controleur;
    }


    public void chargerSalles(){


        this.treeTableView.getItems().clear();

        // Root Item
        List<SalleDto> liste=new ArrayList<>();
        int cpt=0;
        facade facade2=new facade();
        for(Salle salle:facade2.getAllSalles()){
            if(cpt==0){

                liste.add(new SalleDto(salle.id,salle.nomSalle));
            }

        }

        treeTableView.getItems().addAll(liste);



        // Add columns to TreeTable.






    }

    public TableColumn<SalleDto, String> empNoCol ;
    public  TableColumn<SalleDto, String> lastNameCol;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.expose.setDisable(true);
        // Create column EmpNo (Data type of String).
        empNoCol //
                = new TableColumn<SalleDto, String>("nom");

        lastNameCol
                = new TableColumn<SalleDto, String>("id");




        // Defines how to fill data for each cell.
        // Get value from property of Employee.
        empNoCol.setCellValueFactory(new PropertyValueFactory<SalleDto, String>("nom"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<SalleDto, String>("id"));

        //

        treeTableView.getColumns().addAll( lastNameCol,empNoCol);


        treeTableView.getSelectionModel().selectedItemProperty().addListener((Observable, oldValue, newValue)
                ->
                showDetailsSalle(newValue));





    }

    private void showDetailsSalle(SalleDto newValue) {


        if(newValue!=null){
            this.nomSalle.setText(newValue.nom);
            this.idsalle.setText(String.valueOf(newValue.id));
            this.expose.setDisable(false);
        }

    }



    public void gotomenu(MouseEvent mouseEvent)
    {
        this.nomSalle.setText("");
        this.expose.setDisable(true);
        controleur.afficherMenu();
    }

    public void exposoeuvre(MouseEvent mouseEvent) {
        facade facade=new facade();
        facade.lieoeuvresalle(id.getText(),idsalle.getText());
        controleur.consulterOeuvres();

    }
}
