package application.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ProfileController {

	@FXML
	private AnchorPane anchor;
	 
	@FXML
	private TextField nameField;

	@FXML
	private TextField gpaField;

	@FXML
	private Button editBttn;

	@FXML
	private ListView<?> assignmentList;

	@FXML
	private ListView<?> scheduleList;
	
	@FXML
   	private Button MainMenuButton;

    	@FXML
    	void handelMainMenu(ActionEvent event) throws IOException {
    		URL url = new File("src/application/view/Main.fxml").toURI().toURL();
		anchor = FXMLLoader.load(url);
		Scene scene = new Scene(anchor);
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		window.setScene(scene);
    }

}
