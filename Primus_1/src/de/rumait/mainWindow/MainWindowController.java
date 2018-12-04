package de.rumait.mainWindow;

import com.jfoenix.controls.JFXButton;

import de.rumait.databse.Database;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

public class MainWindowController {

	Database database = new Database();
	
	
	//------Buttons werden erstellt-----------------------------------------
	@FXML
	private JFXButton shopButton;
	private JFXButton speditionButton;
	private JFXButton paketeButton;
	
	private JFXButton logoutButton;
	//---------------------------------------------------------------------
	
	
	//-----Methode ShopFensterOeffnen wird nach Klick ausgeführt------------
	public void shopPressed(ActionEvent event) throws Exception {
		shopFensterOeffnen();
	}

	
	
	//----Methode SpeditoinFensterOeffnen wird nach Klick ausgeführt--------
	public void speditionPressed(ActionEvent event) throws Exception {
		speditionFensterOeffnen();
	}
	
	//----Methode PaketeFensterOeffnen wird nach Klick ausgeführt--------
	public void paketePressed(ActionEvent event) throws Exception {
		paketeFensterOeffnen();
	}
	
	
	
	
	
	//-------------Methoden: Fenster öffnen-----------------------------
	//-------------Shop, Spedition, Pakete------------------------------
	
	
	//-------Shop Fenster wird geöffnet---------------------------------
	public void shopFensterOeffnen() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/de/rumait/shop/mainShopWindow.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();
			Stage shopStage = new Stage();
			shopStage.setScene(new Scene(root1));
			shopStage.show();
		} catch (Exception e) {
			System.out.println("Shop: Fenster konnte nicht geöffnet werden: " +e);
		}

	}
	
	//-------Spedition Fenster wird geöffnet----------------------------
	public void speditionFensterOeffnen() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/de/rumait/spedition/mainSpeditionWindow.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();
			Stage speditionStage = new Stage();
			speditionStage.setScene(new Scene(root1));
			speditionStage.show();
		} catch (Exception e) {
			System.out.println("Spedition: Fenster konnte nicht geöffnet werden: " +e);
		}

	}
	
	//-------Pakete Fenster wird geöffnet----------------------------
	public void paketeFensterOeffnen() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/de/rumait/pakete/mainPaketeWindow.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();
			Stage paketeStage = new Stage();
			paketeStage.setScene(new Scene(root1));
			paketeStage.show();
		} catch (Exception e) {
			System.out.println("Pakete: Fenster konnte nicht geöffnet werden: " +e);
		}

	}
	
	
	
	
	//-------------------------------------------------------------------
}
