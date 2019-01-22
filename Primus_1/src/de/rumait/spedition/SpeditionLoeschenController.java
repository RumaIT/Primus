package de.rumait.spedition;

import java.util.Optional;

import com.jfoenix.controls.*;
import com.mysql.cj.xdevapi.DbDoc;

import de.rumait.databse.Database;
import de.rumait.popUpWindow.PopUpWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class SpeditionLoeschenController{
	

    @FXML
    private JFXTextField tfID;

    @FXML
    private JFXButton btnLoeschen;
    
    SpeditionModel spmodel = new SpeditionModel();
    Database db = new Database();

    @FXML
    void btnLoeschenPressed(ActionEvent event) {
    	
    	String id = tfID.getText();
    	
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setContentText("Sind Sie sicher das Sie diese Spedition löschen wollen?");
    	alert.setTitle("Spedition Löschen");
    	Optional<ButtonType> action = alert.showAndWait();
    	
    	if(action.get() == ButtonType.OK && db.checkConnection()) {
    		
    		spmodel.speditionMemberLoeschen(db.getStatement(), id);
    		PopUpWindow.getPopUpWindow("Spedition erfolgreich gelöscht");
    		new SpeditionController().refreashTableView();
    		Stage stage = (Stage) btnLoeschen.getScene().getWindow();
    		stage.close();
    	}
    	
    	
  
    	

    }
    
    
    
	

}
