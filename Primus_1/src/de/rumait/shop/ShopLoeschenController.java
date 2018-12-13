package de.rumait.shop;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import de.rumait.databse.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

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
	    	if(db.checkConnection()) {
	    		
		    	shopModel.shopMemberLoeschen(db.getStatement(), shopID);

	    	}else {
	    		System.out.println("DB Verbindung fehlgeschlagen");
	    	}
	    	
	    	
	    }
	    

}
