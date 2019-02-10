package de.rumait.popUpWindow;

import java.io.IOException;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class PopUpWindow {
	

	public static void getPopUpWindow(String ueberschrift, String text) throws IOException {
		
		Label lblUeberschrift = new Label();
		Label lblTextFehlerStatus = new Label();
		lblUeberschrift.setText(ueberschrift);
		lblTextFehlerStatus.setText(text);
		JFXButton okButton = new JFXButton("OK");
		okButton.setPrefSize(105,35);
		okButton.setStyle("fx-background-color: white");
		okButton.setStyle("-fx-font: 13 arial");
		
		lblUeberschrift.setStyle("-fx-font: 15 arial black");
	
		Stage stage = new Stage();
		AnchorPane root = new AnchorPane();
		root.setStyle("fx-background-color: lightblue");
		Scene scene = new Scene(root, 390, 183);
		stage.setTitle("Meldung");
		stage.setScene(scene);
		stage.show();
		HBox hBox = new HBox();
		HBox hBox2 = new HBox();
		
		hBox.setPrefSize(390, 50);
		hBox.setLayoutX(0);
		hBox.setLayoutY(0);
		hBox.setAlignment(Pos.CENTER);
		hBox.getChildren().add(lblUeberschrift);
		
		hBox2.setPrefSize(390, 50);
		hBox2.setLayoutX(0);
		hBox2.setLayoutY(50);
		hBox2.setAlignment(Pos.CENTER);
		hBox2.getChildren().add(lblTextFehlerStatus);
		
		HBox hBox3 = new HBox();
		hBox3.setPrefSize(390, 50);
		hBox3.setLayoutX(0);
		hBox3.setLayoutY(115);
		hBox3.setAlignment(Pos.CENTER);
		hBox3.getChildren().add(okButton);
		
		
		root.getChildren().add(hBox);
		root.getChildren().add(hBox2);
		root.getChildren().add(hBox3);
		
		okButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
			
				Stage window = (Stage)okButton.getScene().getWindow();
				window.close();
				
			}
			
		});
	}
}
