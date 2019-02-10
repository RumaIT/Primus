package de.rumait.kunden;

import java.io.IOException;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.sun.media.sound.ModelAbstractChannelMixer;

import de.rumait.databse.Database;
import de.rumait.mainLogin.LoginController;
import de.rumait.mainLogin.LoginMain;
import de.rumait.popUpWindow.PopUpWindow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class KundenController implements Initializable {
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
	private TableView<KundenModel> tableKunden;
	@FXML
	private TableColumn<KundenModel, String> rowKundenID;
	@FXML
	private TableColumn<KundenModel, String> rowName;
	@FXML
	private TableColumn<KundenModel, String> rowNachname;
	@FXML
	private TableColumn<KundenModel, String> rowStrasse;
	@FXML
	private TableColumn<KundenModel, String> rowPLZ;
	@FXML
	private TableColumn<KundenModel, String> rowOrt;
	@FXML
	private TableColumn<KundenModel, String> rowStationsID;
	@FXML
	private TableColumn<KundenModel, String> rowStationsName;
	@FXML
	private RadioButton rbKundenID;
	@FXML
	private RadioButton rbNachname;
	@FXML
	private RadioButton rbStrasse;
	@FXML
	private RadioButton rbPLZ;
	@FXML
	private RadioButton rbStationsName;
	@FXML
	private RadioButton rbStationsID;
	@FXML
	private JFXTextField tfSuchen;
	@FXML
	private JFXButton btnKundenSuchen;
	@FXML
	private JFXTextField tf1;
	@FXML
	private JFXTextField tf2;
	@FXML
	private JFXTextField tf3;
	@FXML
	private JFXTextField tf4;
	@FXML
	private JFXTextField tf5;
	@FXML
	private JFXTextField tf6;
	@FXML
	private JFXButton btnDatenAendern;
	@FXML
	private JFXTextField tfKundenIDLoeschen;
	@FXML
	private JFXPasswordField tfPasswortLoeschen;
	@FXML
	private JFXButton btnLoeschen;
	@FXML
	private JFXButton btnAnlegen;
	@FXML
	private JFXButton btnAllesAnzeigen;
	@FXML
	private Label lbl1;
	@FXML
	private Label lbl2;
	@FXML
	private Label lbl3;
	@FXML
	private Label lbl4;
	@FXML
	private Label lbl5;
	@FXML
	private Label lbl6;
	private ToggleGroup group = new ToggleGroup();
	private Database dbZugriff = new Database();
	private KundenModel kundenModel = new KundenModel();
	private ObservableList<KundenModel> list = FXCollections.observableArrayList();
		
	

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
	
	@FXML
	void btnKundenPressed(ActionEvent event) throws IOException {
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
	    void btnAllesAnzeigenPressed(ActionEvent event) throws SQLException, IOException {
		 
		 tableKunden.getItems().clear();
		 
		 if (dbZugriff.checkConnection()) {
			 
			ResultSet abfrage = kundenModel.getAllData(dbZugriff.getStatement());
			if(abfrage.next()) {
				abfrage.beforeFirst();
				while(abfrage.next()) {
					 list.add(new KundenModel(abfrage.getString(1), abfrage.getString(2), abfrage.getString(3), abfrage.getString(4), abfrage.getString(5), abfrage.getString(6), abfrage.getString(7), abfrage.getString(8)));
				 }
				
				rowKundenID.setCellValueFactory(new PropertyValueFactory<>("kundenID"));
				rowName.setCellValueFactory(new PropertyValueFactory<>("name"));
				rowNachname.setCellValueFactory(new PropertyValueFactory<>("nachname"));
				rowStrasse.setCellValueFactory(new PropertyValueFactory<>("strasse"));
				rowPLZ.setCellValueFactory(new PropertyValueFactory<>("plz"));
				rowOrt.setCellValueFactory(new PropertyValueFactory<>("ort"));
				rowStationsID.setCellValueFactory(new PropertyValueFactory<>("stationsID"));
				rowStationsName.setCellValueFactory(new PropertyValueFactory<>("stationsName"));
				
				tableKunden.setItems(list);
			}else {
				PopUpWindow.getPopUpWindow("Fehler bei der Suche", "Es wurden keine Einträge gefunden");
			}
			
		}
	    }

	    @FXML
	    void btnAnlegenPressed(ActionEvent event) throws IOException {
	    	
	    	Stage stage = new Stage();
	    	AnchorPane root = FXMLLoader.load(getClass().getResource("/de/rumait/kundenAnlegen/kundenAnlegenWindow.fxml"));
	    	Scene scene = new Scene(root);
	    	stage.setScene(scene);
	    	stage.show();
	    	stage.setResizable(false);
	    	stage.setTitle("Neuen Kunden Anlegen");
	    }


	    @FXML
	    void btnDatenAendernPressed(ActionEvent event) throws SQLException {
	    	
	    	String kundenID = tableKunden.getSelectionModel().getSelectedItem().getKundenID();
	    	String name = tf1.getText();
	    	String nachname = tf2.getText();
	    	String strasse = tf3.getText();
	    	String plz = tf4.getText();
	    	String ort = tf5.getText();
	    	String stationsID = tf6.getText();
	    	
	    	if(dbZugriff.checkConnection()) {
	    		kundenModel.datenAendern(dbZugriff.getStatement(), kundenID, name, nachname, strasse, plz, ort, stationsID);
	    	}
	    	eingabeAusblenden();
	    }
	    
	    
	    @FXML
	    void tablePressed(MouseEvent event) throws Exception{
	    	
	    	if(tableKunden.getItems().isEmpty() || tableKunden.getSelectionModel().getSelectedCells().isEmpty()) {
	    		
	    		System.out.println("Nichts zum Übernhmen Meldung");
	   
	    	}else {
	    		
	    		lbl1.setVisible(true);
		    	lbl1.setText("Name ändern:");
		    	tf1.setVisible(true);
		    	
		    	lbl2.setVisible(true);
		    	lbl2.setText("Nachname ändern:");
		    	tf2.setVisible(true);
		    	
		    	lbl3.setVisible(true);
		    	lbl3.setText("Strasse ändern:");
		    	tf3.setVisible(true);
		    	
		    	lbl4.setVisible(true);
		    	lbl4.setText("PLZ ändern:");
		    	tf4.setVisible(true);
		    	
		    	lbl5.setVisible(true);
		    	lbl5.setText("Ort ändern:");
		    	tf5.setVisible(true);
		    	
		    	lbl6.setVisible(true);
		    	lbl6.setText("Station ändern:");
		    	tf6.setVisible(true);
		    	
	    		String name = tableKunden.getSelectionModel().getSelectedItem().getName();
		    	String nachname = tableKunden.getSelectionModel().getSelectedItem().getNachname();
		    	String strasse = tableKunden.getSelectionModel().getSelectedItem().getStrasse();
		    	String plz = tableKunden.getSelectionModel().getSelectedItem().getPlz();
		    	String ort = tableKunden.getSelectionModel().getSelectedItem().getOrt();
		    	String stationsID = tableKunden.getSelectionModel().getSelectedItem().getStationsID();
		    			
		    	tf1.setText(name);
		    	tf2.setText(nachname);
		    	tf3.setText(strasse);
		    	tf4.setText(plz);
		    	tf5.setText(ort);
		    	tf6.setText(stationsID);
			}
	    }

	 

	    @FXML
	    void btnKundenSuchenPressed(ActionEvent event) throws SQLException, IOException {
	    	
	    	tableKunden.getItems().clear();
	    	
	    	String kundenID ="";
	    	String nachname ="";
	    	String strasse ="";
	    	String plz ="";
	    	String stationsID ="";
	    	String stationsName ="";
	
	    	if(rbKundenID.isSelected()) {
	    		
	    		kundenID = tfSuchen.getText();
	    		
	    	}else if (rbNachname.isSelected()) {
	    		
				nachname = tfSuchen.getText();
				
			}else if (rbPLZ.isSelected()) {
				
				plz = tfSuchen.getText();
				
			}else if (rbStationsID.isSelected()) {
				
				stationsID = tfSuchen.getText();
				
			}else if (rbStationsName.isSelected()) {
				
				stationsName = tfSuchen.getText();
				
			}else if (rbStrasse.isSelected()) {
				
				strasse = tfSuchen.getText();
			
			}
	    	
	    	if(dbZugriff.checkConnection()) {
	    		
	    		ResultSet abfrage = kundenModel.sucheKunden(dbZugriff.getStatement(), kundenID, nachname, strasse, plz, stationsName, stationsID);
	    		if(abfrage.next()) {
	    			abfrage.beforeFirst();
	    			while(abfrage.next()) {
						 list.add(new KundenModel(abfrage.getString(1), abfrage.getString(2), abfrage.getString(3), abfrage.getString(4), abfrage.getString(5), abfrage.getString(6), abfrage.getString(7), abfrage.getString(8)));
					 }
					
					rowKundenID.setCellValueFactory(new PropertyValueFactory<>("kundenID"));
					rowName.setCellValueFactory(new PropertyValueFactory<>("name"));
					rowNachname.setCellValueFactory(new PropertyValueFactory<>("nachname"));
					rowStrasse.setCellValueFactory(new PropertyValueFactory<>("strasse"));
					rowPLZ.setCellValueFactory(new PropertyValueFactory<>("plz"));
					rowOrt.setCellValueFactory(new PropertyValueFactory<>("ort"));
					rowStationsID.setCellValueFactory(new PropertyValueFactory<>("stationsID"));
					rowStationsName.setCellValueFactory(new PropertyValueFactory<>("stationsName"));
					
					tableKunden.setItems(list);
	    		}else {
	    			PopUpWindow.getPopUpWindow("Fehler bei der Suche", "Es konnten keine Einträge gefunden werden");
	    		}
	    		
	    	}
	    	
	    	tfSuchen.setText("");
	    }

	    @FXML
	    void btnLoeschenPressed(ActionEvent event) throws Exception {
	    	
	    	String kundenID = tfKundenIDLoeschen.getText();
	    	String passwort = tfPasswortLoeschen.getText();
	    	
	    	
	    	if(dbZugriff.checkConnection()) {
	    		if(dbZugriff.userLogin(LoginController.username, passwort)) {
	    			if(kundenModel.kundenLoeschen(dbZugriff.getStatement(), kundenID)) {
		    			PopUpWindow.getPopUpWindow("Kunden löschen", "Der Löschvorgang ist beendet.");
		    		}else {
		    			PopUpWindow.getPopUpWindow("Fehler beim Löschen", "Der Kunde hat eine bestehende Historie. Bitte beim Admin melden.");
		    		}
	    		}else {
	    			PopUpWindow.getPopUpWindow("Passwort falsch", "Bitte versuchen Sie es erneut");
	    		}
	    	}
	    	

	    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		showUserLabel.setText(LoginController.username);
		tableKunden.setPlaceholder(new Label("Keine Einträge vorhanden"));
		rbKundenID.setSelected(true);
		rbKundenID.setToggleGroup(group);
    	rbNachname.setToggleGroup(group);
    	rbPLZ.setToggleGroup(group);
    	rbStationsID.setToggleGroup(group);
    	rbStationsName.setToggleGroup(group);
    	rbStrasse.setToggleGroup(group);
    	
    	eingabeAusblenden();

	}
	
	public void eingabeAusblenden() {
		lbl1.setVisible(false);
    	lbl2.setVisible(false);
    	lbl3.setVisible(false);
    	lbl4.setVisible(false);
    	lbl5.setVisible(false);
    	lbl6.setVisible(false);
    	
    	tf1.setVisible(false);
    	tf2.setVisible(false);
    	tf3.setVisible(false);
    	tf4.setVisible(false);
    	tf5.setVisible(false);
    	tf6.setVisible(false);
	}

}
