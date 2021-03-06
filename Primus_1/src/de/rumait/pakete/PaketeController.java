package de.rumait.pakete;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.sun.org.apache.xalan.internal.xsltc.dom.KeyIndex;
import com.sun.prism.es2.ES2Graphics;

import de.rumait.databse.Database;
import de.rumait.mainLogin.LoginController;
import de.rumait.mainLogin.LoginMain;
import de.rumait.popUpWindow.PopUpWindow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class PaketeController implements Initializable {

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
	private JFXButton btnKunden;
	@FXML
	private TableView<PaketModel> tablePakete;
	@FXML
	private TableColumn<PaketModel, String> paketePaketeID;
	@FXML
	private TableColumn<PaketModel, String> paketeKundenID;
	@FXML
	private TableColumn<PaketModel, String> paketeGewicht;
	@FXML
	private TableColumn<PaketModel, String> paketeBreite;
	@FXML
	private TableColumn<PaketModel, String> paketeHoehe;
	@FXML
	private TableColumn<PaketModel, String> paketeVerfolgungsID;
	@FXML
	private TableColumn<PaketModel, String> paketeStatus;
	@FXML
	private TableColumn<PaketModel, String> paketeDatum;
	@FXML
	private TableColumn<PaketModel, String> paketeUhrzeit;
	@FXML
	private TableView<PaketeModellStationTabelle> tableStationPakete;
	@FXML
	private TableColumn<PaketeModellStationTabelle, String> stationPaketeZuweisungsID;
	@FXML
	private TableColumn<PaketeModellStationTabelle, String> stationPaketeShopID;
	@FXML
	private TableColumn<PaketeModellStationTabelle, String> stationPaketePaketID;
	@FXML
	private TableView<SpeditionPaketeTabelleModel> tableSpeditionPakete;
	@FXML
	private TableColumn<SpeditionPaketeTabelleModel, String> speditionPaketeZuweisungsID;
	@FXML
	private TableColumn<SpeditionPaketeTabelleModel, String> speditionPaketeSpeditionsID;
	@FXML
	private TableColumn<SpeditionPaketeTabelleModel, String> speditionPaketePaketID;
	@FXML
	private JFXTextField tfPaketePaketeIDSuchen;
	@FXML
	private JFXTextField tfPaketeVerfolgungsIDSuchen;
	@FXML
	private JFXTextField tfPaketeNachnameSuchen;
	@FXML
	private JFXButton btnPaketeSuchen;
	@FXML
	private JFXTextField tfPaketeKundenID;
	@FXML
	private JFXButton btnPaketeInfo;
	@FXML
	private JFXTextField tfPaketIDLoeschen;
	@FXML
	private JFXPasswordField tfAdminPasswortLoeschen;
	@FXML
	private JFXButton btnPaketLoeschen;
	@FXML
	private JFXTextField tf1;
	@FXML
	private JFXButton btnStationPaketeAlleAnzeigen;
	@FXML
	private JFXButton btnSpeditionPaketeAlleAnzeigen;
	@FXML
	private JFXButton btnPaketeAlleAnzeigen;
	@FXML
	private Label label1;
	@FXML
	private Label label2;
	@FXML
	private JFXTextField tf2;
	@FXML
	private Label label4;
	@FXML
	private JFXTextField tf4;
	@FXML
	private Label label3;
	@FXML
	private JFXTextField tf3;
	@FXML
	private JFXTextField tf5;
	@FXML
	private JFXTextField tf6;
	@FXML
	private Label label5;
	@FXML
	private Label label6;
	@FXML
	private Label label7;
	@FXML
	private JFXTextField tf7;
	@FXML
	private Label label8;
	@FXML
	private JFXTextField tf8;
	@FXML
	private JFXButton btnDatenAendern;
	@FXML
	private JFXTextField tfStationPaketePaketID;
	@FXML
	private JFXButton btnStationPaketeSuchen;
	@FXML
	private JFXButton btnSpeditionPaketeSuchen;
	@FXML
	private JFXTextField tfSpeditionPaketePaketID;
	private Database dbZugriff = new Database();
	private ObservableList<PaketModel> list = FXCollections.observableArrayList();
	private PaketModel paketModel = new PaketModel();
	private ObservableList<PaketeModellStationTabelle> list2 = FXCollections.observableArrayList();
	private PaketeModellStationTabelle paketStationPaketModell = new PaketeModellStationTabelle();
	private String selection ="0";
	private ObservableList<SpeditionPaketeTabelleModel>list3 = FXCollections.observableArrayList();
	private SpeditionPaketeTabelleModel speditionPaketeTabelleModel = new SpeditionPaketeTabelleModel();
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

	public void btnKundenPressed(ActionEvent event) throws Exception {

		AnchorPane sceneKundenPressed = FXMLLoader
				.load(getClass().getResource("/de/rumait/kunden/mainKundenWindow.fxml"));
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
	void btnDatenAendernPressed(ActionEvent event) throws SQLException, IOException {

		if (selection.equals("1")) {

			String paketID = tablePakete.getSelectionModel().getSelectedItem().getPaketID();

			String kundenID = tf1.getText();
			String gewicht = tf2.getText();
			String breite = tf3.getText();
			String hoehe = tf4.getText();
			String status = tf5.getText();
			String datum = tf6.getText();
			String uhrzeit = tf7.getText();

			String vergleich = tf8.getText();
			String versicherung;

			if (vergleich.equals("true") || vergleich.equals("1")) {
				versicherung = "1";
			} else {
				versicherung = "0";
			}

			if (dbZugriff.checkConnection()) {
				paketModel.paketeAendern(dbZugriff.getStatement(), kundenID, gewicht, breite, hoehe, status, datum,
						uhrzeit, versicherung, paketID);
			}

			felderAusblenden();

			System.out.println("Daten geändert");

		} else if (selection.equals("2")) {

			String stationsID = tf1.getText();
			String paketID = tf2.getText();
			String zuweisungsID = tableStationPakete.getSelectionModel().getSelectedItem().getZuweisungsID();
			if (dbZugriff.checkConnection()) {
				paketStationPaketModell.aendereStationPaketDaten(dbZugriff.getStatement(), stationsID, paketID,
						zuweisungsID);
			}
			felderAusblenden();
		}else if(selection.equals("3")) {
			
			String speditionID = tf1.getText();
			String paketID = tf2.getText();
			String zuweisungsID = tableSpeditionPakete.getSelectionModel().getSelectedItem().getZuweisungsID();
			
			if(dbZugriff.checkConnection()) {
				speditionPaketeTabelleModel.aendereDaten(dbZugriff.getStatement(), speditionID, paketID, zuweisungsID);
			}
			felderAusblenden();
		}else {
			PopUpWindow.getPopUpWindow("Eingabe Fehler", "Bitte wählen Sie einen Datensatz");
		}
	}

	@FXML
	void btnPaketLoeschenPressed(ActionEvent event) throws Exception {
		
		String password = tfAdminPasswortLoeschen.getText();
		String paketID = tfPaketIDLoeschen.getText();
		
		if(dbZugriff.checkConnection()) {
			if(dbZugriff.userLogin(LoginController.username, password)) {
				paketModel.paketLoeschen(dbZugriff.getStatement(), paketID);
			}
		}

	}

	@FXML
	void btnPaketeAlleAnzeigenPressed(ActionEvent event) throws SQLException, IOException {

		tablePakete.getItems().clear();

		if (dbZugriff.checkConnection()) {
			ResultSet abfrage = paketModel.getAllPackgesFromDatabase(dbZugriff.getStatement());
			if(abfrage.next()) {
				abfrage.beforeFirst();
				while (abfrage.next()) {
					list.add(new PaketModel(abfrage.getString(1), abfrage.getString(2), abfrage.getString(3),
							abfrage.getString(4), abfrage.getString(5), abfrage.getString(6), abfrage.getString(7),
							abfrage.getString(8), abfrage.getString(9)));
				}

				paketePaketeID.setCellValueFactory(new PropertyValueFactory<>("paketID"));
				paketeKundenID.setCellValueFactory(new PropertyValueFactory<>("kundenID"));
				paketeGewicht.setCellValueFactory(new PropertyValueFactory<>("gewicht"));
				paketeBreite.setCellValueFactory(new PropertyValueFactory<>("breite"));
				paketeHoehe.setCellValueFactory(new PropertyValueFactory<>("hoehe"));
				paketeVerfolgungsID.setCellValueFactory(new PropertyValueFactory<>("verfolgungsID"));
				paketeStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
				paketeDatum.setCellValueFactory(new PropertyValueFactory<>("datum"));
				paketeUhrzeit.setCellValueFactory(new PropertyValueFactory<>("uhrzeit"));

				tablePakete.setItems(list);
			}else {
				PopUpWindow.getPopUpWindow("Fehler bei der Suche", "Es wurden keine Einträge gefunden.");
			}
			
		}

	}

	@FXML
	void btnPaketeInfoPressed(ActionEvent event) {

	}

	@FXML
	void btnPaketeSuchenPressed(ActionEvent event) throws SQLException, IOException {

		tablePakete.getItems().clear();

		String paketID = tfPaketePaketeIDSuchen.getText();
		String verfolgungsID = tfPaketeVerfolgungsIDSuchen.getText();
		String nachname = tfPaketeNachnameSuchen.getText();

		if (dbZugriff.checkConnection()) {

			ResultSet abfrage = paketModel.searchForPackagesOnDatabse(dbZugriff.getStatement(), paketID, verfolgungsID,
					nachname);
			
			if(abfrage.next()) {
				abfrage.beforeFirst();
				
				while (abfrage.next()) {

					list.add(new PaketModel(abfrage.getString(1), abfrage.getString(2), abfrage.getString(3),
							abfrage.getString(4), abfrage.getString(5), abfrage.getString(6), abfrage.getString(7),
							abfrage.getString(8), abfrage.getString(9)));
				}
				paketePaketeID.setCellValueFactory(new PropertyValueFactory<>("paketID"));
				paketeKundenID.setCellValueFactory(new PropertyValueFactory<>("kundenID"));
				paketeGewicht.setCellValueFactory(new PropertyValueFactory<>("gewicht"));
				paketeBreite.setCellValueFactory(new PropertyValueFactory<>("breite"));
				paketeHoehe.setCellValueFactory(new PropertyValueFactory<>("hoehe"));
				paketeVerfolgungsID.setCellValueFactory(new PropertyValueFactory<>("verfolgungsID"));
				paketeStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
				paketeDatum.setCellValueFactory(new PropertyValueFactory<>("datum"));
				paketeUhrzeit.setCellValueFactory(new PropertyValueFactory<>("uhrzeit"));

				
				
			}else {
				PopUpWindow.getPopUpWindow("Fehler bei der Suche", "Es wurden keine Einträge gefunden.");
			}
			
			tablePakete.setItems(list);
			tfPaketeNachnameSuchen.setText("");
			tfPaketePaketeIDSuchen.setText("");
			tfPaketeVerfolgungsIDSuchen.setText("");
		}
	}

	@FXML
	void btnSpeditionPaketeAlleAnzeigenPressed(ActionEvent event) throws SQLException, IOException {
		
		tableSpeditionPakete.getItems().clear();
		if(dbZugriff.checkConnection()) {
			ResultSet abfrage = speditionPaketeTabelleModel.getAllInfoFromDatabase(dbZugriff.getStatement());
			if(abfrage.next()) {
				abfrage.beforeFirst();
				while(abfrage.next()) {
					list3.add(new SpeditionPaketeTabelleModel(abfrage.getString(2), abfrage.getString(3), abfrage.getString(1)));
				}
				speditionPaketeZuweisungsID.setCellValueFactory(new PropertyValueFactory<>("zuweisungsID"));
				speditionPaketePaketID.setCellValueFactory(new PropertyValueFactory<>("paketID"));
				speditionPaketeSpeditionsID.setCellValueFactory(new PropertyValueFactory<>("speditionID"));
				System.out.println("Test");
				tableSpeditionPakete.setItems(list3);
			}else {
				PopUpWindow.getPopUpWindow("Fehler bei der Suche", "Es wurden keine Einträge gefunden.");
			}
			
		}

	}

	@FXML
	void btnSpeditionPaketeSuchenPressed(ActionEvent event) throws SQLException, IOException {
		tableSpeditionPakete.getItems().clear();
		String paketID = tfSpeditionPaketePaketID.getText();
		
		if(dbZugriff.checkConnection()) {
			ResultSet abfrage = speditionPaketeTabelleModel.searchTableSpeditionPakete(dbZugriff.getStatement(), paketID);
			if(abfrage.next()) {
				abfrage.beforeFirst();
				while(abfrage.next()) {
					list3.add(new SpeditionPaketeTabelleModel(abfrage.getString(2), abfrage.getString(3), abfrage.getString(1)));
				}
				speditionPaketeZuweisungsID.setCellValueFactory(new PropertyValueFactory<>("zuweisungsID"));
				speditionPaketePaketID.setCellValueFactory(new PropertyValueFactory<>("paketID"));
				speditionPaketeSpeditionsID.setCellValueFactory(new PropertyValueFactory<>("speditionID"));
				
				tableSpeditionPakete.setItems(list3);
			}else {
				PopUpWindow.getPopUpWindow("Fehler bei der Suche", "Es wurden keine Einträge gefunden.");
			}
		}
		
		tfSpeditionPaketePaketID.setText("");
	}


	@FXML
	void btnStationPaketeAlleAnzeigenPressed(ActionEvent event) throws SQLException, IOException {

		tableStationPakete.getItems().clear();

		if (dbZugriff.checkConnection()) {
			ResultSet abfrage = paketStationPaketModell.getStationPaketAll(dbZugriff.getStatement());
			if(abfrage.next()) {
				abfrage.beforeFirst();
				while (abfrage.next()) {
					list2.add(new PaketeModellStationTabelle(abfrage.getString(3), abfrage.getString(2),
							abfrage.getString(1)));
				}
				stationPaketeZuweisungsID.setCellValueFactory(new PropertyValueFactory<>("zuweisungsID"));
				stationPaketeShopID.setCellValueFactory(new PropertyValueFactory<>("stationsID"));
				stationPaketePaketID.setCellValueFactory(new PropertyValueFactory<>("paketID"));

			}else {
				PopUpWindow.getPopUpWindow("Fehler bei der Suche", "Es wurden keine Einträge gefunden.");
			}
			
			tableStationPakete.setItems(list2);
		}

	}

	@FXML
	void btnStationPaketeSuchenPressed(ActionEvent event) throws SQLException, IOException {
		tableStationPakete.getItems().clear();
		String paketID = tfStationPaketePaketID.getText();

		if (dbZugriff.checkConnection()) {
			ResultSet abfrage = paketStationPaketModell.stationPaketeSuchen(dbZugriff.getStatement(), paketID);
			if(abfrage.next()) {
				abfrage.beforeFirst();
				while (abfrage.next()) {
					list2.add(new PaketeModellStationTabelle(abfrage.getString(3), abfrage.getString(2),abfrage.getString(1)));
				}
				stationPaketeZuweisungsID.setCellValueFactory(new PropertyValueFactory<>("zuweisungsID"));
				stationPaketeShopID.setCellValueFactory(new PropertyValueFactory<>("stationsID"));
				stationPaketePaketID.setCellValueFactory(new PropertyValueFactory<>("paketID"));

				tableStationPakete.setItems(list2);
			}else {
				PopUpWindow.getPopUpWindow("Fehler bei der Suche","Es wurden keine Einträge gefunden.");
			}
			
		}
		tfStationPaketePaketID.setText("");
	}

	@FXML
	void tablePaketePressed(MouseEvent event) throws SQLException {

		if(tablePakete.getItems().isEmpty() || tablePakete.getSelectionModel().getSelectedCells().isEmpty()) {
			
		}else {
			felderAusblenden();

			label1.setVisible(true);
			label1.setText("KundenID verändern: ");

			label2.setVisible(true);
			label2.setText("Gewicht anpassen:");

			label3.setVisible(true);
			label3.setText("Breite anpassen");

			label4.setVisible(true);
			label4.setText("Höhe Anpassen:");

			label5.setVisible(true);
			label5.setText("Status ändern");

			label6.setVisible(true);
			label6.setText("Datum ändern:");

			label7.setVisible(true);
			label7.setText("Uhrzeit ändern");

			label8.setVisible(true);
			label8.setText("Versicherung ändern: ");

			tf1.setVisible(true);
			tf2.setVisible(true);
			tf3.setVisible(true);
			tf4.setVisible(true);
			tf5.setVisible(true);
			tf6.setVisible(true);
			tf7.setVisible(true);
			tf8.setVisible(true);

			String kundenID = tablePakete.getSelectionModel().getSelectedItem().getKundenID();
			String gewicht = tablePakete.getSelectionModel().getSelectedItem().getGewicht();
			String breite = tablePakete.getSelectionModel().getSelectedItem().getBreite();
			String hoehe = tablePakete.getSelectionModel().getSelectedItem().getHoehe();
			String staus = tablePakete.getSelectionModel().getSelectedItem().getStatus();
			String datum = tablePakete.getSelectionModel().getSelectedItem().getDatum();
			String uhrzeit = tablePakete.getSelectionModel().getSelectedItem().getUhrzeit();
			String versicherung = "0";

			String paketID = tablePakete.getSelectionModel().getSelectedItem().getPaketID();

			if (dbZugriff.checkConnection()) {
				versicherung = String.valueOf(paketModel.getVersicherung(dbZugriff.getStatement(), paketID));
			}

			tf1.setText(kundenID);
			tf2.setText(gewicht);
			tf3.setText(breite);
			tf4.setText(hoehe);
			tf5.setText(staus);
			tf6.setText(datum);
			tf7.setText(uhrzeit);
			tf8.setText(versicherung);

			setSelection("1");

		}
	}

	@FXML
	void tableSpeditionPaketePressed(MouseEvent event) {
		
		if(tableSpeditionPakete.getItems().isEmpty() || tableSpeditionPakete.getSelectionModel().getSelectedCells().isEmpty()) {
			System.out.println("Kein Eintrag Vorhanden Fenster");
		}else {
			
			label1.setVisible(true);
			label1.setText("SpeditionsID ändern:");
			
			label2.setVisible(true);
			label2.setText("PaketID ändern:");
			
			String paketID = tableSpeditionPakete.getSelectionModel().getSelectedItem().getPaketID();
			String stationsID = tableSpeditionPakete.getSelectionModel().getSelectedItem().getSpeditionID();
			
			tf1.setVisible(true);
			tf1.setText(stationsID);
			tf2.setVisible(true);
			tf2.setText(paketID);

			setSelection("3");
			
		}
	}

	@FXML
	void tableStationPaketePressed(MouseEvent event) {
		
		if(tableStationPakete.getItems().isEmpty() || tableStationPakete.getSelectionModel().getSelectedCells().isEmpty()) {
			System.out.println("Keine Einträge Fenster");
		}else {
			
			felderAusblenden();

			label1.setVisible(true);
			label1.setText("Anderen Station zuweisen: ");

			label2.setVisible(true);
			label2.setText("Anderes Paket zuweisen:");

			tf1.setVisible(true);
			tf2.setVisible(true);

			String stationsID = tableStationPakete.getSelectionModel().getSelectedItem().getStationsID();
			String paketID = tableStationPakete.getSelectionModel().getSelectedItem().getPaketID();

			tf1.setText(stationsID);
			tf2.setText(paketID);

			setSelection("2");
		}

		

	}

//---------initialize: Füllt das Label mit dem Benutzten der sich eingeloggt hat---------
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		showUserLabel.setText(LoginController.username);
		tablePakete.setPlaceholder(new Label("Keine Einträge vorhanden"));
		tableSpeditionPakete.setPlaceholder(new Label("Keine Einträge vorhanden"));
		tableStationPakete.setPlaceholder(new Label("Keine Einträge vorhanden"));

		label1.setVisible(false);
		label2.setVisible(false);
		label3.setVisible(false);
		label4.setVisible(false);
		label5.setVisible(false);
		label6.setVisible(false);
		label7.setVisible(false);
		label8.setVisible(false);

		tf1.setVisible(false);
		tf2.setVisible(false);
		tf3.setVisible(false);
		tf4.setVisible(false);
		tf5.setVisible(false);
		tf6.setVisible(false);
		tf7.setVisible(false);
		tf8.setVisible(false);

	}

	public void felderAusblenden() {
		label1.setVisible(false);
		label2.setVisible(false);
		label3.setVisible(false);
		label4.setVisible(false);
		label5.setVisible(false);
		label6.setVisible(false);
		label7.setVisible(false);
		label8.setVisible(false);

		tf1.setVisible(false);
		tf2.setVisible(false);
		tf3.setVisible(false);
		tf4.setVisible(false);
		tf5.setVisible(false);
		tf6.setVisible(false);
		tf7.setVisible(false);
		tf8.setVisible(false);
	}

	public String getSelection() {
		return selection;
	}

	public void setSelection(String selection) {
		this.selection = selection;
	}

	// -------------------------------------------------------------------
}
