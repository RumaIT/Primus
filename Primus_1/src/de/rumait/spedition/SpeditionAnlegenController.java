package de.rumait.spedition;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.mysql.cj.xdevapi.DbDoc;
import com.sun.media.sound.ModelAbstractChannelMixer;
import com.sun.source.tree.ParenthesizedTree;

import de.rumait.databse.Database;
import de.rumait.popUpWindow.PopUpWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class SpeditionAnlegenController {
	
	 @FXML
	    private JFXTextField tfSpeditionsName;

	    @FXML
	    private JFXTextField tfStadt;

	    @FXML
	    private JFXTextField tfStrasse;

	    @FXML
	    private JFXTextField tfPLZ;

	    @FXML
	    private JFXTextField tfHausnummer;

	    @FXML
	    private JFXTextField tfUserName;

	    @FXML
	    private JFXPasswordField tfPasswort;

	    @FXML
	    private JFXPasswordField tfPasswortConfirm;

	    @FXML
	    private JFXButton btnSpeditionAnlegen;
	    
	    SpeditionModel model = new SpeditionModel();
	    Database db = new Database();
	    
	    

	    @FXML
	    void btnSpeditionAnlegenPressed(ActionEvent event) {
	    	
	    	String ort = tfStadt.getText();
	    	String strasse = tfStrasse.getText();
	    	String hausnummer = tfHausnummer.getText();
	    	String plz = tfPLZ.getText();
	    	String passwort = tfPasswort.getText();
	    	String passwortConfirm = tfPasswortConfirm.getText();
	    	String SpeditionName = tfSpeditionsName.getText();
	    	String benutzerName = tfUserName.getText();
	    	
	    	if(db.checkConnection() && passwort.equals(passwortConfirm)) {
	    		
	    	model.createSpeditionsMember(db.getStatement(), ort, plz, benutzerName, passwort, SpeditionName, strasse, hausnummer);
	    	
	    	PopUpWindow.getPopUpWindow("Die Spedition wurde erfolgreich angelegt.");
	    	
	    	Stage parent = (Stage) btnSpeditionAnlegen.getScene().getWindow();
	    	parent.close();
	    	
	    	}else {
	    		PopUpWindow.getPopUpWindow("Die Passwörter stimmen nicht überein");
	    	}

	    }

	    
	    

}
