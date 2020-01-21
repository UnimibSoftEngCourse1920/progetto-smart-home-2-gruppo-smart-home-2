package application.frontend;

import java.io.IOException;

import application.backend.*;
import application.controllers.ControllerCasa;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ControllerJavaFX {
	
	@FXML
	public Button button;

	public void start(Stage primaryStage) throws Exception {
    	try {
    		primaryStage.setTitle("Smart-Home-2");
    		Parent root = FXMLLoader.load(getClass().getResource("views/esempio.fxml"));
    		Scene home = new Scene(root, 600,600);
    		home.getStylesheets().add("application.css");
    		primaryStage.setScene(home);
			primaryStage.show();
			
    	} catch (Exception e) {
			e.printStackTrace();
		}
    }

	@FXML
	public void click() throws IOException {
		System.out.println("ciao2");
	}
}
