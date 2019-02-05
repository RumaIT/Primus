package de.rumait.shop;

import java.io.IOException;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;

import de.rumait.databse.Database;
import de.rumait.mainLogin.LoginController;
import de.rumait.mainLogin.LoginMain;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ShopController implements Initializable {

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
	private JFXTextField tfStationIDSuchen;
	@FXML
    private JFXButton btnKunden;
	@FXML
	private JFXTextField tfStationNameSuchen;
	@FXML
	private JFXButton btnStationSuchen;
	@FXML
	private TableView<ShopModel> stationTable;
	@FXML
	private TableColumn<ShopModel, String> rowStationsID;
	@FXML
	private TableColumn<ShopModel, String> rowStationsName;
	@FXML
	private TableColumn<ShopModel, String> rowBenutzername;
	@FXML
	private TableColumn<ShopModel, String> rowPasswort;
	@FXML
	private TableColumn<ShopModel, String> rowStrasse;
	@FXML
	private TableColumn<ShopModel, String> rowPLZ;
	@FXML
	private TableColumn<ShopModel, String> rowOrt;
	@FXML
	private JFXButton btnAlleStationenSuchen;
	@FXML
	private JFXTextField tfShopNameChange;
	@FXML
	private Label lblStationIdShow;
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
	private JFXTextField tfShopName;
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

	private ObservableList<ShopModel> list = FXCollections.observableArrayList();
	private Database dbZugriff = new Database();
	private ShopModel shopModel = new ShopModel();

	// ---------------------------------------------------------------------

	// ---------------Methoden für die verschiedenen
	// Fenster----------------------------

	// -----Methode ShopFensterOeffnen wird nach Klick ausgef�hrt------------

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

	}

	// ----Methode PaketeFensterOeffnen wird nach Klick ausgef�hrt--------
	public void paketePressed(ActionEvent event) throws Exception {

		AnchorPane scenePaketePressed = FXMLLoader
				.load(getClass().getResource("/de/rumait/pakete/mainPaketeWindow.fxml"));
		rootPane.getChildren().setAll(scenePaketePressed);

	}
	//Kunden fenster öffnen
	
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
	

//----------------------Methoden für Buttons ---------------------------------------------

	@FXML
	void btnAlleStationenSuchenPressed(ActionEvent event) throws SQLException {

		stationTable.getItems().clear();

		if (dbZugriff.checkConnection()) {
			ResultSet abfrage = shopModel.getAllStations(dbZugriff.getStatement());

			while (abfrage.next()) {

				list.add(new ShopModel(abfrage.getString(1), abfrage.getString(2), abfrage.getString(3),
						abfrage.getString(4), abfrage.getString(5), abfrage.getString(6), abfrage.getString(7)));
			}

			rowStationsID.setCellValueFactory(new PropertyValueFactory<>("stationID"));
			rowStationsName.setCellValueFactory(new PropertyValueFactory<>("shopName"));
			rowBenutzername.setCellValueFactory(new PropertyValueFactory<>("benutzerName"));
			rowPasswort.setCellValueFactory(new PropertyValueFactory<>("passwort"));
			rowStrasse.setCellValueFactory(new PropertyValueFactory<>("strasse"));
			rowPLZ.setCellValueFactory(new PropertyValueFactory<>("plz"));
			rowOrt.setCellValueFactory(new PropertyValueFactory<>("ort"));

			stationTable.setItems(list);

		}

	}

	@FXML
	void btnChangePressed(ActionEvent event) throws SQLException {

		String idShop = lblStationIdShow.getText();
		String stationName = tfShopNameChange.getText();
		String benutzerName = tfBenutzernameChange.getText();
		String passwort = tfPasswortChange.getText();
		String strasse = tfStrasseChange.getText();
		String plz = tfPLZChange.getText();
		String ort = tfOrtChange.getText();

		if (dbZugriff.checkConnection()) {

			if (shopModel.changeStationInfo(dbZugriff.getStatement(), idShop, stationName, benutzerName, passwort,
					strasse, plz, ort)) {

				lblStationIdShow.setText("");
				tfShopNameChange.setText("");
				tfBenutzernameChange.setText("");
				tfPasswortChange.setText("");
				tfStrasseChange.setText("");
				tfPLZChange.setText("");
				tfOrtChange.setText("");

				System.out.println("Hier kommt eine Fenstermeldung!(Erflgreich)");
			} else {
				System.out.println("Fenstermeldung! (Fehlgeschlagen)");
			}
		}

	}

	@FXML
	void btnNeueStationPressed(ActionEvent event) throws SQLException {

		String shopName = tfShopName.getText();
		String benutzerName = tfBenutzername.getText();
		String passwort = tfPasswort.getText();
		String strasse = tfStrasse.getText();
		String plz = tfPLZ.getText();
		String ort = tfOrt.getText();

		if (dbZugriff.checkConnection()) {

			if (shopModel.createShopMember(dbZugriff.getStatement(), ort, plz, benutzerName, passwort, shopName,
					strasse)) {
				System.out.println("Fenstermeldung: erfolgreich");
			} else {
				System.out.println("Fenstermeldung: fehlgeschlagen");
			}
		}

		tfShopName.setText("");
		tfBenutzername.setText("");
		tfPasswort.setText("");
		tfStrasse.setText("");
		tfPLZ.setText("");
		tfOrt.setText("");

	}

	@FXML
	void btnStationLoeschenPressed(ActionEvent event) throws Exception {

		String passwort = tfPasswortAdminLoeschen.getText();
		String stationID = tfIdLoeschen.getText();

		if (shopModel.deleteStationEntery(dbZugriff, passwort, stationID)) {
			System.out.println("Hier kommt eine Fenster Meldung (Erfolgreich)");
		} else {
			System.out.println("Hier kommt eine Fenstermeldung (Fehlgeschlagen)");
		}

		tfIdLoeschen.setText("");
		tfPasswortAdminLoeschen.setText("");

	}

	@FXML
	void btnStationSuchenPressed(ActionEvent event) throws SQLException {

		stationTable.getItems().clear();
		String stationID = tfStationIDSuchen.getText();
		String stationName = tfStationNameSuchen.getText();

		if (dbZugriff.checkConnection()) {
			ResultSet abfrage = shopModel.getStationsFromSearch(dbZugriff.getStatement(), stationID, stationName);

			while (abfrage.next()) {
				list.add(new ShopModel(abfrage.getString(1), abfrage.getString(2), abfrage.getString(3),
						abfrage.getString(4), abfrage.getString(5), abfrage.getString(6), abfrage.getString(7)));
			}

			rowStationsID.setCellValueFactory(new PropertyValueFactory<>("stationID"));
			rowStationsName.setCellValueFactory(new PropertyValueFactory<>("shopName"));
			rowBenutzername.setCellValueFactory(new PropertyValueFactory<>("benutzerName"));
			rowPasswort.setCellValueFactory(new PropertyValueFactory<>("passwort"));
			rowStrasse.setCellValueFactory(new PropertyValueFactory<>("strasse"));
			rowPLZ.setCellValueFactory(new PropertyValueFactory<>("plz"));
			rowOrt.setCellValueFactory(new PropertyValueFactory<>("ort"));

			stationTable.setItems(list);

			tfStationIDSuchen.setText("");
			tfStationNameSuchen.setText("");
		}
	}

	@FXML
	void stationTablePressed(MouseEvent event) throws Exception{

		String stationID = stationTable.getSelectionModel().getSelectedItem().getStationID();
		String stationName = stationTable.getSelectionModel().getSelectedItem().getShopName();
		String benutzername = stationTable.getSelectionModel().getSelectedItem().getBenutzerName();
		String passwort = stationTable.getSelectionModel().getSelectedItem().getPasswort();
		String strasse = stationTable.getSelectionModel().getSelectedItem().getStrasse();
		String plz = stationTable.getSelectionModel().getSelectedItem().getPlz();
		String ort = stationTable.getSelectionModel().getSelectedItem().getOrt();

		lblStationIdShow.setText(stationID);

		tfShopNameChange.setText(stationName);
		tfBenutzernameChange.setText(benutzername);
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

	// -------Methode für TableView Liste, Elemente aus der Datenbank holen------

	// -------------------------------------------------------------------
}
