package de.rumait.shop;

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
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ShopController implements Initializable {

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
	@FXML
	private JFXButton btnNewShop;
	@FXML
	private JFXButton btnStationenLoeschen;
	@FXML
	private JFXButton btnRefresh;
	@FXML
	private TableView<ShopModel> tableShop;
	@FXML
	private TableColumn<ShopModel, String> rowShopID;
	@FXML
	private TableColumn<ShopModel, String> rowShopName;
	@FXML
	private TableColumn<ShopModel, String> rowStrasse;
	@FXML
	private TableColumn<ShopModel, String> rowBenutzername;
	private ObservableList<ShopModel> list = FXCollections.observableArrayList();

	// ---------------------------------------------------------------------

	// ---------------Methoden für die verschiedenen
	// Fenster----------------------------

	// -----Methode ShopFensterOeffnen wird nach Klick ausgef�hrt------------

	public void btnUebersichtPressed(ActionEvent event) throws Exception {

		AnchorPane sceneUebersichtPressed = FXMLLoader
				.load(getClass().getResource("/de/rumait/uebersicht/mainUebersichtWindow.fxml"));
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

	// -----Methode Btn Aktualisieren gedrückt
	@FXML
	void btnRefreshPressed(ActionEvent event) {

		refreashTable();

	}

	@FXML
	void btnNewShopPressed(ActionEvent event) throws Exception {

		Stage mainWindow = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/de/rumait/shop/shopAnlegenWindow.fxml"));
		Scene scene = new Scene(root);

		mainWindow.setScene(scene);
		mainWindow.setResizable(false);
		mainWindow.setTitle("Dashboard Center");
		mainWindow.show();

	}

	@FXML
	void btnStationenLoschenPressed(ActionEvent event) throws Exception {

		Stage mainWindow = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/de/rumait/shop/shopLoeschenWindow.fxml"));
		Scene scene = new Scene(root);
		mainWindow.setScene(scene);
		mainWindow.setResizable(false);
		mainWindow.setTitle("Dashboard Center");
		mainWindow.show();

	}

	// -------------Methoden: Fenster �ffnen-----------------------------
	// -------------Shop, Spedition, Pakete------------------------------

	// -------Shop Fenster wird ge�ffnet---------------------------------
	public void shopFensterOeffnen() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/de/rumait/shop/mainShopWindow.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();
			Stage shopStage = new Stage();
			shopStage.setScene(new Scene(root1));
			shopStage.show();
		} catch (Exception e) {
			System.out.println("Shop: Fenster konnte nicht ge�ffnet werden: " + e);
		}

	}

	// -------Spedition Fenster wird ge�ffnet----------------------------
	public void speditionFensterOeffnen() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(
					getClass().getResource("/de/rumait/spedition/mainSpeditionWindow.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();
			Stage speditionStage = new Stage();
			speditionStage.setScene(new Scene(root1));
			speditionStage.show();
		} catch (Exception e) {
			System.out.println("Spedition: Fenster konnte nicht ge�ffnet werden: " + e);
		}

	}

	// -------Pakete Fenster wird ge�ffnet----------------------------
	public void paketeFensterOeffnen() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/de/rumait/pakete/mainPaketeWindow.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();
			Stage paketeStage = new Stage();
			paketeStage.setScene(new Scene(root1));
			paketeStage.show();
		} catch (Exception e) {
			System.out.println("Pakete: Fenster konnte nicht ge�ffnet werden: " + e);
		}

	}

//---------initialize: Füllt das Label mit dem Benutzten der sich eingeloggt hat---------
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		showUserLabel.setText(LoginController.username);

		getShopMembersFromDatabase();

	}

	// -------Methode für TableView Liste, Elemente aus der Datenbank holen------

	public void getShopMembersFromDatabase() {

		try {
			if (database.checkConnection()) {

				Connection connection = database.getConnection();
				ResultSet rSet = connection.createStatement().executeQuery("SELECT * from Shop");

				while (rSet.next()) {

					list.add(new ShopModel(rSet.getString("idShop"), rSet.getString("shopName"),
							rSet.getString("strasse"), rSet.getString("benutzername")));

				}

				rowShopID.setCellValueFactory(new PropertyValueFactory<>("idShop"));
				rowShopName.setCellValueFactory(new PropertyValueFactory<>("shopName"));
				rowStrasse.setCellValueFactory(new PropertyValueFactory<>("strasse"));
				rowBenutzername.setCellValueFactory(new PropertyValueFactory<>("benutzername"));

				tableShop.setItems(list);

			}

		} catch (Exception e) {

		}

	}

	public void refreashTable() {

		tableShop.getItems().clear();
		getShopMembersFromDatabase();
	}

	// -------------------------------------------------------------------
}
