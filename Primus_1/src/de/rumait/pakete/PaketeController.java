package de.rumait.pakete;


import java.io.IOException;


import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
	    private TableView<PaketModel> tableStationPakete;
	    @FXML
	    private TableColumn<PaketModel, String> stationPaketeZuweisungsID;
	    @FXML
	    private TableColumn<PaketModel, String> stationPaketeShopID;
	    @FXML
	    private TableColumn<PaketModel, String> stationPaketePaketID;
	    @FXML
	    private TableView<PaketModel> tableSpeditionPakete;
	    @FXML
	    private TableColumn<PaketModel, String> speditionPaketeZuweisungsID;
	    @FXML
	    private TableColumn<PaketModel, String> speditionPaketeSpeditionsID;
	    @FXML
	    private TableColumn<PaketModel, String> speditionPaketePaketID;
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
	    void btnDatenAendernPressed(ActionEvent event) throws SQLException {
	    	
	    	
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
	    	
	    	if(vergleich.equals("true")|| vergleich.equals("1")) {
	    		versicherung ="1";
	    	}else {
	    		versicherung = "0";
	    	}
	    	
	    	if(dbZugriff.checkConnection()) {
	    		paketModel.paketeAendern(dbZugriff.getStatement(), kundenID, gewicht, breite, hoehe, status, datum, uhrzeit, versicherung, paketID);
	    	}
	    	
	 
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
			
			System.out.println("Daten geändert");
			

	    }


	    @FXML
	    void btnPaketLoeschenPressed(ActionEvent event) {

	    }

	    @FXML
	    void btnPaketeAlleAnzeigenPressed(ActionEvent event) throws SQLException {
	    	
	    	tablePakete.getItems().clear();
	    	
	    	if(dbZugriff.checkConnection()) {
	    		ResultSet abfrage = paketModel.getAllPackgesFromDatabase(dbZugriff.getStatement());	
	    		while(abfrage.next()) {
	    			list.add(new PaketModel(abfrage.getString(1), abfrage.getString(2), abfrage.getString(3), abfrage.getString(4), abfrage.getString(5), abfrage.getString(6), abfrage.getString(7), abfrage.getString(8), abfrage.getString(9)));
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
	    	}

	    }

	    @FXML
	    void btnPaketeInfoPressed(ActionEvent event) {

	    }

	    @FXML
	    void btnPaketeSuchenPressed(ActionEvent event) throws SQLException {
	    	
	    	tablePakete.getItems().clear();
	    	
	    	String paketID = tfPaketePaketeIDSuchen.getText();
	    	String verfolgungsID = tfPaketeVerfolgungsIDSuchen.getText();
	    	String nachname = tfPaketeNachnameSuchen.getText();
	    	
	    	if (dbZugriff.checkConnection()) {
	    		
	    		ResultSet abfrage = paketModel.searchForPackagesOnDatabse(dbZugriff.getStatement(), paketID, verfolgungsID, nachname);
	    		
	    		while(abfrage.next()) {
	    			
	    			list.add(new PaketModel(abfrage.getString(1), abfrage.getString(2), abfrage.getString(3), abfrage.getString(4), abfrage.getString(5), abfrage.getString(6), abfrage.getString(7), abfrage.getString(8), abfrage.getString(9)));
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
	    		tfPaketeNachnameSuchen.setText("");
	    		tfPaketePaketeIDSuchen.setText("");
	    		tfPaketeVerfolgungsIDSuchen.setText("");
			}
	    }

	    @FXML
	    void btnSpeditionPaketeAlleAnzeigenPressed(ActionEvent event) {

	    }

	    @FXML
	    void btnSpeditionPaketeSuchenPressed(ActionEvent event) {

	    }

	    @FXML
	    void btnStationPaketeAlleAnzeigenPressed(ActionEvent event) {

	    }

	    @FXML
	    void btnStationPaketeSuchenPressed(ActionEvent event) {

	    }

	    @FXML
	    void tablePaketePressed(MouseEvent event) throws SQLException {
	    	
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
	    	String versicherung ="0";
	    	
	    	
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
	    
	    	

	    }

	    @FXML
	    void tableSpeditionPaketePressed(MouseEvent event) {

	    }

	    @FXML
	    void tableStationPaketePressed(MouseEvent event) {

	    }

//---------initialize: Füllt das Label mit dem Benutzten der sich eingeloggt hat---------
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		showUserLabel.setText(LoginController.username);
		
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

	// -------------------------------------------------------------------
}
