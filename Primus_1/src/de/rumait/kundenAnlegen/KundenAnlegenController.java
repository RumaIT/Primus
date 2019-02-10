package de.rumait.kundenAnlegen;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.tools.javac.resources.compiler;
import com.sun.tools.javac.util.List;

import de.rumait.databse.Database;
import de.rumait.popUpWindow.PopUpWindow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SpinnerValueFactory.ListSpinnerValueFactory;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class KundenAnlegenController implements Initializable{
	
	@FXML
    private AnchorPane root;
    @FXML
    private JFXTextField tfName;
    @FXML
    private JFXTextField tfNachname;
    @FXML
    private JFXTextField tfStrasse;
    @FXML
    private JFXTextField tfPLZ;
    @FXML
    private JFXTextField tfOrt;
    @FXML
    private ComboBox<KundenAnlegenModel> comboBox;
    @FXML
    private JFXButton btnAnlegen;
    private Database dbZugriff = new Database();
    private KundenAnlegenModel model = new KundenAnlegenModel();
    private ObservableList<KundenAnlegenModel> list = FXCollections.observableArrayList();
    @FXML
    void btnAnlegenPressed(ActionEvent event) throws SQLException, IOException {
    	
    	String name = tfNachname.getText();
    	String nachname = tfNachname.getText();
    	String strasse = tfStrasse.getText();
    	String plz = tfPLZ.getText();
    	String ort = tfOrt.getText();
    	String stationsName = comboBox.getSelectionModel().getSelectedItem().toString();
    	
    	if(dbZugriff.checkConnection()) {
        	model.createNewKunden(dbZugriff.getStatement(), nachname, nachname, strasse, plz, ort, stationsName);
    	}
    	
    	PopUpWindow.getPopUpWindow("Kunden anlegen", "Das anlegen des Kunden war erfolgreich");
    	
    	Stage currentStage = (Stage) btnAnlegen.getScene().getWindow();
    	currentStage.close();
		
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	
		try {
			if(dbZugriff.checkConnection()) {
	    		ResultSet abfrage = model.getStations(dbZugriff.getStatement());
	    		
	    		while (abfrage.next()) {
					list.add(new KundenAnlegenModel(abfrage.getString(1).toString()));
				}
	    		comboBox.getItems().addAll(list);
	    	}
		} catch (Exception e) {
			
		}
	}
 
}
