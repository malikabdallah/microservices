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

public class VueAccueil implements Vue, Initializable {


    public ListView oeuvres;
    public Label exposition;
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


    public static VueAccueil creer(Stage stage) {
        URL location = VueAccueil.class.getResource("accueil.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        VueAccueil vue = fxmlLoader.getController();
        vue.setStage(stage);
        Scene scene = new Scene(root);
        vue.setScene(scene);
        vue.initialiserBouton();
        return vue;
    }

    @Override
    public void show() {
        this.stage.setScene(this.scene);
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
        List<SalleDto>liste=new ArrayList<>();
        int cpt=0;
        facade facade2=new facade();
        for(Salle salle:facade2.getAllSalles()){
            if(cpt==0){

                liste.add(new SalleDto(salle.id,salle.nomSalle));
            }

        }

        treeTableView.getItems().addAll(liste);



        // Add columns to TreeTable.




        treeTableView.getSelectionModel().selectedItemProperty().addListener((Observable, oldValue, newValue)
                ->
                showDetailsClient(newValue));

    }

   public TableColumn<SalleDto, String> empNoCol ;
    public  TableColumn<SalleDto, String> lastNameCol;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        treeTableView.getSelectionModel().selectedItemProperty().addListener((Observable, oldValue, newValue)
                ->
                remplirListe(newValue));

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

        treeTableView.getColumns().addAll(empNoCol, lastNameCol);



    }

    private void remplirListe(SalleDto newValue) {
        if(newValue!=null){
            oeuvres.getItems().clear();
            facade facade=new facade();
            List<Oeuvre>list=facade.getOeuvresSalles(String.valueOf(newValue.getId()));
            for(Oeuvre oeuvre:list){
                this.oeuvres.getItems().add(oeuvre.getNomOeuvre()+" "+oeuvre.getDate());
            }

            Salle salle=facade.getSalleById(newValue.id);
            if(salle.getExposition()!=null){
                this.exposition.setText(salle.exposition.getTitle());
            }

        }

    }

    private void showDetailsClient(SalleDto newValue) {

    }

    public void gotomenu(MouseEvent mouseEvent)
    {
        this.exposition.setText("");
        controleur.afficherMenu();
    }
}
