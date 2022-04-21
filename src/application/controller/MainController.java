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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainController {

	@FXML
	private AnchorPane anchor;
	 
	@FXML
	private Button createScheduleBttn;

	@FXML
	private Button openScheduleBttn;

	@FXML
	private Button profileBttn;

	@FXML
	void createSchedule(ActionEvent event) {

	}

	@FXML
	void openProfile(ActionEvent event) throws IOException {
		URL url = new File("src/application/view/Profile.fxml").toURI().toURL();
    		anchor = FXMLLoader.load(url);
    		Scene scene = new Scene(anchor);
    		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
    		window.setScene(scene);
    		window.show();
	}

	@FXML
	void openSchedule(ActionEvent event) {

	}

}
