package de.rumait.shop;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import de.rumait.databse.Database;
import de.rumait.popUpWindow.PopUpWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class ShopAnlegenController {
	
	@FXML
    private JFXButton btnShopAnlegen;

    @FXML
    private JFXTextField tfShopName;

    @FXML
    private JFXTextField tfOrt;

    @FXML
    private JFXTextField tfPLZ;

    @FXML
    private JFXTextField tfStrasse;

    @FXML
    private JFXTextField tfHausnummer;

    @FXML
    private JFXTextField tfBenutzername;

    @FXML
    private JFXTextField tfPasswort;

    @FXML
    private JFXTextField tfPasswortConfirm;
    
    ShopModel shopModel = new ShopModel();
    Database db = new Database();
    ShopController sc = new ShopController();
    
    
    

    @FXML
    void btnShopAnlegenPressed(ActionEvent event) {
    	
    	String shopName = tfShopName.getText();
    	String ort = tfOrt.getText();
    	String plz = tfPLZ.getText();
    	String strasse = tfStrasse.getText();
    	String hausnummer = tfHausnummer.getText();
    	String benutzername = tfBenutzername.getText();
    	String passwort = tfPasswort.getText();
    	String passwortConfirm = tfPasswortConfirm.getText();
    	
    	if(db.checkConnection() && (passwortConfirm.equals(passwort))) {
    		
        	
    		shopModel.createShopMember(db.getStatement(), ort, plz, benutzername, passwort, shopName, strasse, hausnummer);
    		PopUpWindow.getPopUpWindow("Neue Station erfolgreich angelegt");
    		
    		//Fenster zum anlegen schliessen 
    		
    		Stage shopAnlegen = (Stage)btnShopAnlegen.getScene().getWindow();
    		shopAnlegen.close();
    		
    		
        	
    		
        	
    	}else {
    		System.out.println("Datenbank Verbindung unterbrochen");
    		PopUpWindow.getPopUpWindow("Passwort und Passwort bestätigen stimmt nicht überein");
    	}
    	
    	

    }


}