package de.rumait.mainLogin;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import de.rumait.databse.Database;
import de.rumait.popUpWindow.PopUpWindow;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class LoginController implements Initializable {

	
	@FXML
	private JFXButton loginButton;
	@FXML
	private JFXTextField userField;
	@FXML
	private JFXPasswordField passField;
	@FXML
	private Circle statusCircle;

	public static String username;
	private Database database = new Database();


	// Action Methode für Login. Benutzt die userLogin Methode von Database
	@FXML
	void loginPressed(ActionEvent event) throws Exception {

		username = userField.getText();
		String password = passField.getText();

		if (database.userLogin(username, password)) {

			startMainWindow();
			System.out.println("Login erfolgreich");

		} else {
			System.out.println("Login fehlgeschlagen");
			PopUpWindow.getPopUpWindow("Login fehlgeschlagen");
		}
		

	}

	// Action Event um den LoginButton zu Aktivieren, sobald mehr als 4 zeichen in
	// den Feldern ist
	@FXML
	void passwordKeyReleased(KeyEvent event) {

		String userAcitve = userField.getText();
		String passActive = passField.getText();

		boolean buttonActiveorNot = (userAcitve.isEmpty() || userAcitve.length() < 4)
				|| (passActive.isEmpty() || passActive.length() < 4);

		loginButton.setDisable(buttonActiveorNot);
	}

	public void createDashborard() {

	}

	// Führt etwas aus bevor das Programm startet (überprüft die Datenbankverbindung
	// und ändert die Farbe des Kreises)
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		loginButton.setDisable(true);

		if (database.checkConnection()) {

			statusCircle.setFill(Color.GREEN);

		} else {
			statusCircle.setFill(Color.RED);
		}
	}

	/*
	 * Starten des Hauptfensters (Dashboard) beim erfolgreichen Login
	 */

	public void startMainWindow() throws Exception {

		// Altes Fenster schließen

		Stage loginStage = (Stage) loginButton.getScene().getWindow();
		loginStage.close();

		// Neues Fenster öffnen

		Stage mainWindow = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/de/rumait/mainWindow/mainWindows.fxml"));
		Scene scene = new Scene(root);
		mainWindow.setScene(scene);
		mainWindow.setResizable(false);
		mainWindow.setTitle("Primus Adminpanel");
		mainWindow.show();

	}

}
