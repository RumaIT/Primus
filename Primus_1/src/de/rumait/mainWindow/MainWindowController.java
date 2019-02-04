package de.rumait.mainWindow;

import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import de.rumait.databse.Database;
import de.rumait.mainLogin.LoginController;
import de.rumait.mainLogin.LoginMain;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainWindowController implements Initializable {

	Database database = new Database();

	// ------Buttons werden erstellt-----------------------------------------
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
	// ---------------------------------------------------------------------

	// ---------------Methoden für die verschiedenen
	// Fenster----------------------------

	// -----Methode ShopFensterOeffnen wird nach Klick ausgef�hrt------------

	public void btnUebersichtPressed(ActionEvent event) throws Exception {


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
		System.out.println("Test press");
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
	
	
//---------initialize: Füllt das Label mit dem Benutzten der sich eingeloggt hat---------
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		showUserLabel.setText(LoginController.username);

	}
	


	

	// -------------------------------------------------------------------
}
