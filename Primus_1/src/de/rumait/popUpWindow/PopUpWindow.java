package de.rumait.popUpWindow;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PopUpWindow {
	
	
	public static void getPopUpWindow(String message) {
		Stage stage = new Stage();
		VBox root = new VBox(3);
		Scene scene = new Scene(root,400,200);
		
		Button ok = new Button("Bestätigen");
		Label label = new Label(message);
		
		ok.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				
				stage.close();
				
			}
		});
		root.getChildren().addAll(label,ok);
		
		
		stage.setTitle("Meldung");
		stage.setScene(scene);
		stage.show();
	}
	

}
