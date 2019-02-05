package de.rumait.spedition;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import de.rumait.databse.Database;
import de.rumait.mainLogin.LoginController;
import de.rumait.mainLogin.LoginMain;
import de.rumait.shop.ShopModel;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SpeditionController implements Initializable {

	Database database = new Database();

	// ------Buttons werden erstellt-----------------------------------------
	@FXML
    private AnchorPane rootPane;
    @FXML
    private Label showUserLabel;
    @FXML
    private JFXButton btnAusloggen;
    @FXML
    private JFXButton paketeButton;
    @FXML
    private JFXButton btnUebersicht;
    @FXML
    private JFXButton shopButton;
    @FXML
    private JFXButton speditionButton;
    @FXML
    private JFXTextField tfSpeditionIDSuchen;
    @FXML
    private JFXTextField tfSpeditionNameSuchen;
    @FXML
    private JFXButton btnSpeditionSuchen;
    @FXML
    private JFXButton btnAlleSpeditionenSuchen;
    @FXML
    private JFXTextField tfSpediNameChange;
    @FXML
    private Label lblSpediIdShow;
    @FXML
    private JFXButton btnKunden;
    @FXML
    private JFXTextField tfBenutzernameChange;
    @FXML
    private JFXTextField tfPasswortChange;
    @FXML
    private JFXTextField tfStrasseChange;
    @FXML
    private JFXTextField tfPLZChange;
    @FXML
    private JFXTextField tfOrtChange;
    @FXML
    private JFXButton btnChange;
    @FXML
    private JFXTextField tfIdLoeschen;
    @FXML
    private JFXPasswordField tfPasswortAdminLoeschen;
    @FXML
    private JFXButton btnSpeditionLoeschen;
    @FXML
    private JFXButton btnNeueSpedition;
    @FXML
    private JFXTextField tfSpediName;
    @FXML
    private JFXTextField tfBenutzername;
    @FXML
    private JFXTextField tfPasswort;
    @FXML
    private JFXTextField tfStrasse;
    @FXML
    private JFXTextField tfPLZ;
    @FXML
    private JFXTextField tfOrt;
    @FXML
    private TableView<SpeditionModel> speditionTabelle;
    @FXML
    private TableColumn<SpeditionModel, String> rowSpeditionID;
    @FXML
    private TableColumn<SpeditionModel, String> rowSpeditionsName;
    @FXML
    private TableColumn<SpeditionModel, String> rowBenutzername;
    @FXML
    private TableColumn<SpeditionModel, String> rowPasswort;
    @FXML
    private TableColumn<SpeditionModel, String> rowStrasse;
    @FXML
    private TableColumn<SpeditionModel, String> rowPLZ;
    @FXML
    private TableColumn<SpeditionModel, String> rowOrt;
    
    private SpeditionModel speditionModel = new SpeditionModel();
    private Database dbZugriff = new Database();

	ObservableList<SpeditionModel> list = FXCollections.observableArrayList();

	// ---------------------------------------------------------------------

	// ---------------Methoden für die verschiedenen
	// Fenster----------------------------

	// -----Methode ShopFensterOeffnen wird nach Klick ausgef�hrt------------

	public void btnUebersichtPressed(ActionEvent event) throws Exception {

		AnchorPane sceneUebersichtPressed = FXMLLoader.load(getClass().getResource("/de/rumait/mainWindow/mainWindows.fxml"));
		rootPane.getChildren().setAll(sceneUebersichtPressed);

	}

	public void shopPressed(ActionEvent event) throws Exception {

		AnchorPane sceneShopPressed = FXMLLoader.load(getClass().getResource("/de/rumait/shop/mainShopWindow.fxml"));
		rootPane.getChildren().setAll(sceneShopPressed);

	}

	// ----Methode SpeditoinFensterOeffnen wird nach Klick ausgef�hrt--------
	public void speditionPressed(ActionEvent event) throws Exception {

		AnchorPane sceneSpeditionPressed = FXMLLoader
				.load(getClass().getResource("/de/rumait/spedition/mainSpeditionWindow.fxml"));
		rootPane.getChildren().setAll(sceneSpeditionPressed);

	}

	// ----Methode PaketeFensterOeffnen wird nach Klick ausgef�hrt--------
	public void paketePressed(ActionEvent event) throws Exception {

		AnchorPane scenePaketePressed = FXMLLoader
				.load(getClass().getResource("/de/rumait/pakete/mainPaketeWindow.fxml"));
		rootPane.getChildren().setAll(scenePaketePressed);

	}
	
	//-Methode Fenster Kunden öffnen
	@FXML
    void btnKundenPressed(ActionEvent event) throws IOException {
		
		AnchorPane sceneKundenPressed = FXMLLoader.load(getClass().getResource("/de/rumait/kunden/mainKundenWindow.fxml"));
		rootPane.getChildren().setAll(sceneKundenPressed);

    }

	// ---Methode Ausloggen und LoginFenster starten----------------------
	@FXML
	public void btnAusloggenPressed(ActionEvent event) throws IOException {

		Stage openStage = (Stage) shopButton.getScene().getWindow();
		openStage.close();

		LoginMain loginWindow = new LoginMain();
		loginWindow.Loginstarten();

	}
	
	
	@FXML
    void btnAlleSpeditionenSuchenPressed(ActionEvent event) throws SQLException {
		
		speditionTabelle.getItems().clear();
		
		if(dbZugriff.checkConnection()) {
			
			ResultSet abfrage = speditionModel.getAllSpeditionen(dbZugriff.getStatement());
			
			while (abfrage.next()) {
				
				list.add(new SpeditionModel(abfrage.getString(1), abfrage.getString(2), abfrage.getString(3), abfrage.getString(4), abfrage.getString(5), abfrage.getString(6), abfrage.getString(7)));
			}
			
			rowSpeditionID.setCellValueFactory(new PropertyValueFactory<>("spediID"));
			rowSpeditionsName.setCellValueFactory(new PropertyValueFactory<>("spediName"));
			rowBenutzername.setCellValueFactory(new PropertyValueFactory<>("spediBenutzerName"));
			rowPasswort.setCellValueFactory(new PropertyValueFactory<>("passwort"));
			rowStrasse.setCellValueFactory(new PropertyValueFactory<>("strasse"));
			rowPLZ.setCellValueFactory(new PropertyValueFactory<>("plz"));
			rowOrt.setCellValueFactory(new PropertyValueFactory<>("ort"));
			
			speditionTabelle.setItems(list);
		}
    }
	
	@FXML   
	void btnChangePressed(ActionEvent event) throws SQLException {
		
		String spediID = lblSpediIdShow.getText();
		String spediName = tfSpediNameChange.getText();
		String spediBenutzerName = tfBenutzernameChange.getText();
		String passwort = tfPasswortChange.getText();
		String strasse = tfStrasseChange.getText();
		String plz = tfPLZChange.getText();
		String ort = tfOrtChange.getText();
	
		if(dbZugriff.checkConnection()) {
			speditionModel.changeStationInfo(dbZugriff.getStatement(), spediID, spediName, spediBenutzerName, passwort, strasse, plz, ort);
		}
		
		tfSpediNameChange.setText("");
		tfBenutzernameChange.setText("");
		tfPasswortChange.setText("");
		tfStrasseChange.setText("");
		tfPLZChange.setText("");
		tfOrtChange.setText("");
	
	    }
	
	
  
	@FXML
	void btnNeueSpeditionPressed(ActionEvent event) throws SQLException {
		
		String spediName = tfSpediName.getText();
		String spediBenutzerName = tfBenutzername.getText();
		String passwort = tfPasswort.getText();
		String strasse = tfStrasse.getText();
		String plz = tfPLZ.getText();
		String ort = tfOrt.getText();
		
		if(dbZugriff.checkConnection()) {
			
			speditionModel.createShopMember(dbZugriff.getStatement(), ort, plz, spediBenutzerName, passwort, spediName, strasse);

			
		}
		
		tfSpediName.setText("");
		tfBenutzername.setText("");
		tfPasswort.setText("");
		tfStrasse.setText("");
		tfPLZ.setText("");
		tfOrt.setText("");

	    }

	   
	@FXML  
	void btnSpeditionSuchenPressed(ActionEvent event) throws SQLException {
		
		speditionTabelle.getItems().clear();
		
		String spediID = tfSpeditionIDSuchen.getText();
		String spediName = tfSpeditionNameSuchen.getText();
		
		if(dbZugriff.checkConnection()) {
			
		ResultSet abfrage = speditionModel.getSpeditionFromSearch(dbZugriff.getStatement(), spediID, spediName);

		while (abfrage.next()) {
			list.add(new SpeditionModel(abfrage.getString(1), abfrage.getString(2), abfrage.getString(3), abfrage.getString(4), abfrage.getString(5), abfrage.getString(6), abfrage.getString(7)));
		}
		
		rowSpeditionID.setCellValueFactory(new PropertyValueFactory<>("spediID"));
		rowSpeditionsName.setCellValueFactory(new PropertyValueFactory<>("spediName"));
		rowBenutzername.setCellValueFactory(new PropertyValueFactory<>("spediBenutzerName"));
		rowPasswort.setCellValueFactory(new PropertyValueFactory<>("passwort"));
		rowStrasse.setCellValueFactory(new PropertyValueFactory<>("strasse"));
		rowPLZ.setCellValueFactory(new PropertyValueFactory<>("plz"));
		rowOrt.setCellValueFactory(new PropertyValueFactory<>("ort"));
		
		speditionTabelle.setItems(list);
		
		}
		
		tfSpeditionIDSuchen.setText("");
		tfSpeditionNameSuchen.setText("");

	    }

	  
	@FXML 
	void btnSpeditionLoeschenPressed(ActionEvent event) throws Exception {
		
		String password = tfPasswortAdminLoeschen.getText();
		String id = tfIdLoeschen.getText();
		
		if(dbZugriff.checkConnection()) {
			if(dbZugriff.userLogin(LoginController.username, password)) {
				speditionModel.spediMemberLoeschen(dbZugriff.getStatement(), id);
			}
		}
		
		tfPasswortAdminLoeschen.setText("");
		tfIdLoeschen.setText("");

	    }
	
	@FXML
    void spediTabellePressed(MouseEvent event) {
		
		String spediID = speditionTabelle.getSelectionModel().getSelectedItem().getSpediID();
		String spediName = speditionTabelle.getSelectionModel().getSelectedItem().getSpediName();
		String spediBenutzername = speditionTabelle.getSelectionModel().getSelectedItem().getSpediBenutzerName();
		String passwort = speditionTabelle.getSelectionModel().getSelectedItem().getPasswort();
		String strasse = speditionTabelle.getSelectionModel().getSelectedItem().getStrasse();
		String plz = speditionTabelle.getSelectionModel().getSelectedItem().getPlz();
		String ort = speditionTabelle.getSelectionModel().getSelectedItem().getOrt();
		
		lblSpediIdShow.setText(spediID);
		tfSpediNameChange.setText(spediName);
		tfBenutzernameChange.setText(spediBenutzername);
		tfPasswortChange.setText(passwort);
		tfStrasseChange.setText(strasse);
		tfPLZChange.setText(plz);
		tfOrtChange.setText(ort);
    }

	

//---------initialize: Füllt das Label mit dem Benutzten der sich eingeloggt hat---------
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		showUserLabel.setText(LoginController.username);
		
	}

	// -------------------------------------------------------------------
}
