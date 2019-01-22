package de.rumait.spedition;

import java.io.IOException;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import de.rumait.databse.Database;
import de.rumait.mainLogin.LoginController;
import de.rumait.mainLogin.LoginMain;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SpeditionController implements Initializable {

	Database database = new Database();
	
	
	//------Buttons werden erstellt-----------------------------------------
	@FXML
    private Label showUserLabel;

    @FXML
    private JFXButton btnAusloggen;

    @FXML
    private JFXButton shopButton;

    @FXML
    private JFXButton speditionButton;
    @FXML 
    private JFXButton btnUebersicht;

    @FXML
    private JFXButton paketeButton;
    @FXML
    private AnchorPane rootPane;
    
    @FXML
    private JFXButton btnSpeditionAnlegen;

    @FXML
    private JFXButton btnSpeditionLöschen;

    @FXML
    private TableView<SpeditionModel> tabelSpedition;

    @FXML
    private TableColumn<SpeditionModel, String> rowSpeditionsID;

    @FXML
    private TableColumn<SpeditionModel	, String> rowBenutzerName;

    @FXML
    private TableColumn<SpeditionModel, String> rowStrasse;

    @FXML
    private TableColumn<SpeditionModel, String> rowSpeditionsName;
    
    @FXML
    private JFXButton btnRefreash;
    
    ObservableList<SpeditionModel> list = FXCollections.observableArrayList();
    
    
    
    
	//---------------------------------------------------------------------
	
    //---------------Methoden für die verschiedenen Fenster----------------------------
    
	
	//-----Methode ShopFensterOeffnen wird nach Klick ausgef�hrt------------
    
   public void btnUebersichtPressed(ActionEvent event) throws Exception {
    	
    	AnchorPane sceneUebersichtPressed = FXMLLoader.load(getClass().getResource("/de/rumait/uebersicht/mainUebersichtWindow.fxml"));
		rootPane.getChildren().setAll(sceneUebersichtPressed);

    }
    
	public void shopPressed(ActionEvent event) throws Exception {
		
		
		AnchorPane sceneShopPressed = FXMLLoader.load(getClass().getResource("/de/rumait/shop/mainShopWindow.fxml"));
		rootPane.getChildren().setAll(sceneShopPressed);
		
	}

	
	//----Methode SpeditoinFensterOeffnen wird nach Klick ausgef�hrt--------
	public void speditionPressed(ActionEvent event) throws Exception {
		
		AnchorPane sceneSpeditionPressed = FXMLLoader.load(getClass().getResource("/de/rumait/spedition/mainSpeditionWindow.fxml"));
		rootPane.getChildren().setAll(sceneSpeditionPressed);
		
	}
	
	//----Methode PaketeFensterOeffnen wird nach Klick ausgef�hrt--------
	public void paketePressed(ActionEvent event) throws Exception {
		
		AnchorPane scenePaketePressed = FXMLLoader.load(getClass().getResource("/de/rumait/pakete/mainPaketeWindow.fxml"));
		rootPane.getChildren().setAll(scenePaketePressed);
		
	}
	//---Methode Ausloggen und LoginFenster starten----------------------
	@FXML
    public void btnAusloggenPressed(ActionEvent event) throws IOException {
		
		Stage openStage = (Stage) shopButton.getScene().getWindow();
		openStage.close();
		
		LoginMain loginWindow = new LoginMain();
		loginWindow.Loginstarten();

    }
	
	
	//-------------Methoden: Fenster �ffnen-----------------------------
	//-------------Shop, Spedition, Pakete------------------------------
	
	
	//-------Shop Fenster wird ge�ffnet---------------------------------
	public void shopFensterOeffnen() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/de/rumait/shop/mainShopWindow.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();
			Stage shopStage = new Stage();
			shopStage.setScene(new Scene(root1));
			shopStage.show();
		} catch (Exception e) {
			System.out.println("Shop: Fenster konnte nicht ge�ffnet werden: " +e);
		}

	}
	
	//-------Spedition Fenster wird ge�ffnet----------------------------
	public void speditionFensterOeffnen() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/de/rumait/spedition/mainSpeditionWindow.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();
			Stage speditionStage = new Stage();
			speditionStage.setScene(new Scene(root1));
			speditionStage.show();
		} catch (Exception e) {
			System.out.println("Spedition: Fenster konnte nicht ge�ffnet werden: " +e);
		}

	}
	
	//-------Pakete Fenster wird ge�ffnet----------------------------
	public void paketeFensterOeffnen() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/de/rumait/pakete/mainPaketeWindow.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();
			Stage paketeStage = new Stage();
			paketeStage.setScene(new Scene(root1));
			paketeStage.show();
		} catch (Exception e) {
			System.out.println("Pakete: Fenster konnte nicht ge�ffnet werden: " +e);
		}

	}
	
	
	@FXML
    void speditionAnlegenPressed(ActionEvent event) throws IOException {
		
		Stage mainWindow = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/de/rumait/spedition/speditionAnlegen.fxml"));
		Scene scene = new Scene(root);
		
		mainWindow.setScene(scene);
		mainWindow.setTitle("Spedition anlegen");
		mainWindow.show();
    }
	
	
	@FXML
	void speditionLöschenPressed(ActionEvent event) throws IOException {
		
		Stage mainWindow = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/de/rumait/spedition/speditionLoeschen.fxml"));
		Scene scene = new Scene(root);
		
		mainWindow.setScene(scene);
		mainWindow.setTitle("Spedition Löschen");
		mainWindow.show();
	    }
	
	@FXML
    void btnRefreashPressed(ActionEvent event) {
		
		refreashTableView();

    }
	
	
	

	
	
	//Methode um TAbleView mit Daten zu Füllen 
	
	
	public void getDataFromDatabase() {
		
		try {
			
			if(database.checkConnection()) {
				
				Connection connection = database.getConnection();
				ResultSet rSet = connection.createStatement().executeQuery("SELECT * from Spedition");
				
				while (rSet.next()) {
					list.add(new SpeditionModel(rSet.getString("idSpedition"), rSet.getString("SpeditionsName"), rSet.getString("Benutzername"), rSet.getString("Strasse")));
				}
			rowSpeditionsID.setCellValueFactory(new PropertyValueFactory<>("spediID"));
			rowSpeditionsName.setCellValueFactory(new PropertyValueFactory<>("spediName"));
			rowBenutzerName.setCellValueFactory(new PropertyValueFactory<>("spediUserName"));
			rowStrasse.setCellValueFactory(new PropertyValueFactory<>("spediStrasse"));
			
			
			
			tabelSpedition.setItems(list);	
			
		
			
			}
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Fehler beim einfügen in die Liste");
		}
		
	}
	
	public void refreashTableView() {
		tabelSpedition.getItems().clear();
		getDataFromDatabase();
	}
	
	
	


//---------initialize: Füllt das Label mit dem Benutzten der sich eingeloggt hat---------
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		showUserLabel.setText(LoginController.username);
		refreashTableView();
		
	}
	
	
	
	
	//-------------------------------------------------------------------
}
