package de.rumait.shop;

import java.util.Optional;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import de.rumait.databse.Database;
import de.rumait.popUpWindow.PopUpWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;

public class ShopLoeschenController {
		@FXML
	    private JFXTextField tfShopID;

	    @FXML
	    private JFXButton btnShopLoeschen;
	    
	    ShopModel shopModel = new ShopModel();
	    Database db = new Database();
	    

	    @FXML
	    void btnShopLoeschenPressed(ActionEvent event) {

	    	String shopID = tfShopID.getText();
	    	
	    	// Pop Up Fenster, das Ja oder nein ausgibt um das Löschen zu bestätigen 
	    	Alert alert = new Alert(AlertType.CONFIRMATION);
	    	alert.setTitle("Bestätigung erforderlich");
	    	alert.setContentText("Sind sie sicher, dass Sie die Station entgültig löschen wollen?");
	    	Optional <ButtonType> action = alert.showAndWait();
	    	
	    	if(action.get() == ButtonType.OK) {
	    		
	    		if(db.checkConnection()) {
		    		
			    	shopModel.shopMemberLoeschen(db.getStatement(), shopID);

			
		
			    	PopUpWindow.getPopUpWindow("Der User wurde erfolgreich gelöscht");
			    	
			    	Stage shopLoeschen = (Stage)btnShopLoeschen.getScene().getWindow();
			    	shopLoeschen.close();
		    	}else {
		    		System.out.println("Der User konnte nicht gefunden werden");
		    		PopUpWindow.getPopUpWindow("Die Spedition mit dieser ID konnte nicht gefunden werden");
		    	}
	    		
	    	}
	    	
	    	
	    	

	    }
	    

}
