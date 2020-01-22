package application.frontend;

import java.io.IOException;
import java.util.ArrayList;

import application.backend.*;
import application.controllers.ControllerCasa;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ControllerJavaFX {
	public ControllerCasa c = new ControllerCasa(this);
	
	//menu---------------------------------------------------------------------------
	@FXML
	public BorderPane borderPane;
	public VBox vbox;
	
	
	//stanze-------------------------------------------------------------------------
	@FXML
	public BorderPane borderPaneTabellaStanze;
	@FXML
	public TableView<Stanza> tabellaStanze;
	@FXML
	public TableColumn nomeStanza = new TableColumn("Nome Stanza");
	
	
	
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
	
	public ControllerCasa getControllerCasa() {
		return this.c;
	}

	//metodi relativi ai programmi------------------------------------------------------------------------------
	@FXML
	public void viewProgrammi() throws IOException {
		Parent programmi = FXMLLoader.load(getClass().getResource("views/programmi.fxml"));
		borderPane.setCenter(programmi);
		//vbox.getChildren().addAll(programmi);
	}
	
	//metodi relativi alla homepage-------------------------------------------------------------------------------
	@FXML
	public void viewHomePage() throws IOException {
		Parent homepage = FXMLLoader.load(getClass().getResource("views/homepage.fxml"));
		borderPane.setCenter(homepage);
		//vbox.getChildren().addAll(homepage);
		
	}
	
	//metodi relativi alle stanze---------------------------------------------------------------------------------
	@FXML
	public void viewStanze() throws IOException {
		Parent stanze = FXMLLoader.load(getClass().getResource("views/stanze.fxml"));
		//vbox.getChildren().addAll(stanze);
		borderPane.setCenter(stanze);
		//System.out.print(nomeStanza.getText());
		//tabella.setItems(getListaStanze());
		//nomeStanza.setCellValueFactory(new PropertyValueFactory<Stanza, String>("ciao"));
	}
	
	public ObservableList<Stanza> getListaStanze() {
		ArrayList<Stanza> stanze = getControllerCasa().getStanze();
		Stanza stanza1 = new Stanza("Esempio Camera 1");
		Stanza stanza2 = new Stanza("Esempio Camera 2");
		
		stanze.add(stanza1);
		stanze.add(stanza2);
		//return FXCollections.observableArrayList(getControllerCasa().getStanze());
		return FXCollections.observableArrayList(stanze);
	}
	
	@FXML
	public void visualizzaStanzeTabella() {
		nomeStanza.setMinWidth(570);
		
		//nella stringa di property devo mettere il nome preciso dell'attributo nella classe
		nomeStanza.setCellValueFactory(new PropertyValueFactory<Stanza, String>("nome"));
		tabellaStanze.getColumns().addAll(nomeStanza);
		tabellaStanze.setVisible(true);
		tabellaStanze.setItems(getListaStanze());
		borderPaneTabellaStanze.setCenter(tabellaStanze);
		
		tabellaStanze.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event arg0) {
				Stanza stanzaSelezionata = tabellaStanze.getSelectionModel().getSelectedItem();
				if (stanzaSelezionata != null) {
					System.out.println(stanzaSelezionata.getNome());
				}
			}
		});
	}
	
	@FXML
	public void click() throws IOException {
		//System.out.println("ciao");
		Alert a1 = new Alert(Alert.AlertType.ERROR);
		a1.setTitle("ciao");
		a1.showAndWait();
	}	

}
