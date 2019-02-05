package de.rumait.kunden;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import de.rumait.mainLogin.LoginController;
import de.rumait.mainLogin.LoginMain;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class KundenController implements Initializable{
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
	    private JFXButton btnKunden;

	   
	    public void btnUebersichtPressed(ActionEvent event) throws Exception {
	    	AnchorPane sceneUebersichtPressed = FXMLLoader
					.load(getClass().getResource("/de/rumait/mainWindow/mainWindows.fxml"));
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
		
		@FXML
	    void btnKundenPressed(ActionEvent event) throws IOException {
			AnchorPane sceneKundenPressed = FXMLLoader.load(getClass().getResource("/de/rumait/kunden/mainKundenWindow.fxml"));
			rootPane.getChildren().setAll(sceneKundenPressed);
		}

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			showUserLabel.setText(LoginController.username);
			
		}

}
