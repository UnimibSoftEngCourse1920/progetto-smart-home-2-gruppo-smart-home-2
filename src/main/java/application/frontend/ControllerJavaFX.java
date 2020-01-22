package application.frontend;

import java.io.IOException;

import application.backend.*;
import application.controllers.ControllerCasa;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ControllerJavaFX {
	
	@FXML
	public BorderPane borderPaneProgrammi;
	public ControllerCasa c = new ControllerCasa(this);

	public void start(Stage primaryStage) throws Exception {
    	try {
    		primaryStage.setTitle("Smart-Home-2");
    		Parent root = FXMLLoader.load(getClass().getResource("views/menu.fxml"));
    		Scene home = new Scene(root);
    		home.getStylesheets().add("application.css");
    		primaryStage.setScene(home);
			primaryStage.show();
			primaryStage.setMaximized(true);
    	} catch (Exception e) {
			e.printStackTrace();
		}
    }

	@FXML
	public void viewProgrammi() throws IOException {
		Parent programmi = FXMLLoader.load(getClass().getResource("views/programmi.fxml"));
		borderPaneProgrammi.setCenter(programmi);
	}
	
	@FXML
	public void viewHomePage() throws IOException {
		Parent homepage = FXMLLoader.load(getClass().getResource("views/homepage.fxml"));
		borderPaneProgrammi.setCenter(homepage);
	}
	
	@FXML
	public void viewStanze() throws IOException {
		Parent homepage = FXMLLoader.load(getClass().getResource("views/stanze.fxml"));
		borderPaneProgrammi.setCenter(homepage);
	}
	
	@FXML
	public void click() throws IOException {
		//System.out.println("ciao");
		Alert a1 = new Alert(Alert.AlertType.ERROR);
		a1.setTitle("ciao");
		a1.showAndWait();
	}
	
	public ControllerCasa getControllerCasa() {
		return this.c;
	}
	
}
