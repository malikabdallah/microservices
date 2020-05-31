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
import modele.Exposition;
import modele.Salle;
import modele.SalleDto;
import modele.facade;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ChoisirSalle implements Vue, Initializable {

    public static Exposition exposition;
    public Label titre;
    public Label categorie;
    public Label datedeb;
    public Label datefi;
    public Button creer;
    public Label nomsalle;

    public void gotomenu(MouseEvent mouseEvent) {
        exposition=null;
        this.titre.setText("");
        this.categorie.setText("");
        this.datedeb.setText("");
        this.datefi.setText("");
        this.controleur.afficherMenu();
    }








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


    public static ChoisirSalle creer(Stage stage) {
        URL location = ChoisirSalle.class.getResource("choisirSalle.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ChoisirSalle vue = fxmlLoader.getController();
        vue.setStage(stage);
        Scene scene = new Scene(root);
        vue.setScene(scene);
        vue.initialiserBouton();
        return vue;
    }

    @Override
    public void show() {
        this.creer.setDisable(true);
        this.stage.setScene(this.scene);
        treeTableView.getSelectionModel().selectedItemProperty().addListener((Observable, oldValue, newValue)
                ->
                showDetailSalle(newValue));

        if(ChoisirSalle.exposition!=null) {
            this.titre.setText(ChoisirSalle.exposition.getTitle());
            this.categorie.setText(ChoisirSalle.exposition.getCategory());
            this.datedeb.setText(ChoisirSalle.exposition.getDate_debut());
            this.datefi.setText(ChoisirSalle.exposition.getDate_fin());
        }
        chargerSalles();
        this.stage.show();
    }

    private String idSalle;

    private void showDetailSalle(SalleDto newValue) {
        if(newValue!=null){

            this.idSalle=String.valueOf(newValue.id);
            this.nomsalle.setText(newValue.getNom());
            this.creer.setDisable(false);

        }
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
        facade facade2=new facade();
        for(Salle salle:facade2.getSallesDisponible()){

                liste.add(new SalleDto(salle.id,salle.nomSalle));


        }

        treeTableView.getItems().addAll(liste);



        // Add columns to TreeTable.




        treeTableView.getSelectionModel().selectedItemProperty().addListener((Observable, oldValue, newValue)
                ->
                showDetailSalle(newValue));

    }

    public TableColumn<SalleDto, String> empNoCol ;
    public  TableColumn<SalleDto, String> lastNameCol;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



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


    public void creerExposition(MouseEvent mouseEvent) {
        if(this.idSalle!=null){
            facade facade=new facade();
            Salle salle=facade.getSalleById(Long.parseLong(this.idSalle));
            if(salle==null){
                System.out.println("salle est null");
            }
            //ChoisirSalle.exposition.getSalle().add(salle);
            facade.creerUneExposition(ChoisirSalle.exposition);
            salle.setDisponible(false);
            salle.exposition=exposition;
            facade.updatesalle(salle);
            controleur.afficherMenu();
        }

    }
}
