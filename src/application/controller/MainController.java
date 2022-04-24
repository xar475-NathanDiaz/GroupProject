package application.controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import application.model.BrowserData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainController implements Initializable{

	@FXML
	private AnchorPane anchor;

	@FXML
	private Button openScheduleBttn;

	@FXML
	void openSchedule(ActionEvent event) {
		try {
			BrowserData.prevMenu = 0;
			URL url = new File("src/application/view/ItemBrowser.fxml").toURI().toURL();
			Parent root = FXMLLoader.load(url);
			Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
			Scene scene = new Scene(root);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			window.setScene(scene);
			window.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

}
