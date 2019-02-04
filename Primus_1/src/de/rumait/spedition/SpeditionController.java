package de.rumait.spedition;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

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
    private JFXTextField SpeditionIDSuchen;
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
    private JFXButton btnStationLoeschen;
    @FXML
    private JFXButton btnNeueStation;
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
    private TableView<?> speditionTabelle;
    @FXML
    private TableColumn<?, ?> rowSpeditionID;
    @FXML
    private TableColumn<?, ?> rowSpeditionsName;
    @FXML
    private TableColumn<?, ?> rowBenutzername;
    @FXML
    private TableColumn<?, ?> rowPasswort;
    @FXML
    private TableColumn<?, ?> rowStrasse;
    @FXML
    private TableColumn<?, ?> rowPLZ;
    @FXML
    private TableColumn<?, ?> rowOrt;

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

	// ---Methode Ausloggen und LoginFenster starten----------------------
	@FXML
	public void btnAusloggenPressed(ActionEvent event) throws IOException {

		Stage openStage = (Stage) shopButton.getScene().getWindow();
		openStage.close();

		LoginMain loginWindow = new LoginMain();
		loginWindow.Loginstarten();

	}
	
	
	@FXML
    void btnAlleSpeditionenSuchenPressed(ActionEvent event) {

    }
	
	@FXML   
	void btnChangePressed(ActionEvent event) {

	    }
  
	@FXML
	void btnNeueStationPressed(ActionEvent event) {

	    }

	   
	@FXML  
	void btnSpeditionSuchenPressed(ActionEvent event) {

	    }

	  
	@FXML 
	void btnStationLoeschenPressed(ActionEvent event) {

	    }
	
	@FXML
    void spediTabellePressed(MouseEvent event) {

    }

	

//---------initialize: Füllt das Label mit dem Benutzten der sich eingeloggt hat---------
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		showUserLabel.setText(LoginController.username);
		
	}

	// -------------------------------------------------------------------
}
