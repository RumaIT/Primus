package de.rumait.shop;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import de.rumait.databse.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ShopController {

	@FXML
	private JFXTextField tfBenutzername;

	@FXML
	private JFXTextField tfPasswort;

	@FXML
	private JFXTextField tfShopName;

	@FXML
	private JFXTextField tfStrasse;

	@FXML
	private JFXTextField tfHausnummer;

	@FXML
	private JFXTextField tfOrt;

	@FXML
	private JFXTextField tfPLZ;

	@FXML
	private JFXTextField tfConfrimPasswort;

	@FXML
    private JFXButton btnLoeschen;
	
	@FXML
	private JFXTextField tfShopId;

	@FXML
	private Button btnShopKundenAnlegen;

	ShopModel shopModel = new ShopModel();

	

	@FXML
    void btnShopKundenAnlegenPressed(ActionEvent event) {
		Database db = new Database();
		
    	String benutzername = tfBenutzername.getText();
    	String passwort = tfPasswort.getText();
    	String shopName = tfShopName.getText();
    	String strasse = tfStrasse.getText();
    	String hausnummer = tfHausnummer.getText();
    	
    	String ort = tfOrt.getText();
    	String plz = tfPLZ.getText();
    	
    	if(db.checkConnection()) {
        	shopModel.createShopMember(db.getStatement(), ort, plz, benutzername, passwort, shopName, strasse, hausnummer);

    	}else {
    		System.out.println("DB Verbindung fehlgeschlagen");
    		
    	}
    	
	}
    	
	
	
	
	
    	@FXML
     void btnShopLoeschen(ActionEvent event) {
    		
    		String id = tfShopId.getText();
    		
    		Database db2 = new Database();
    		
    		if(db2.checkConnection()) {
    			
    			System.out.println("ID: "+id);
    			shopModel.shopMemberLoeschen(db2.getStatement(), id);
    			
    		}else {
    			System.out.println("Fehler2");
    		}
    		
    		

        }
    	
    	
    	
    	


    	
    }


