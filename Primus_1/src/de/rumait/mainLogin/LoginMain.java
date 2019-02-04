package de.rumait.mainLogin;
import java.io.IOException;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginMain extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		
	
			
			Loginstarten();
			
		


}
	
	public void Loginstarten() throws IOException {
		
		Parent root = FXMLLoader.load(getClass().getResource("/de/rumait/mainLogin/loginWindow.fxml"));
		Scene scene = new Scene(root);
		
		
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
	}
	
}
