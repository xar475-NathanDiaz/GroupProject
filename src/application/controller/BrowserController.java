package application.controller;

import java.io.File;
import java.net.URL;

import application.model.BrowserData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class BrowserController{

	@FXML
	private Button backBttn;

	@FXML
	private ListView<String> itemList;

	@FXML
	private Button openBttn;

	@FXML
	void openItem(ActionEvent event) {

	}

	@FXML
	void prevMenu(ActionEvent event) {
		int prevMenu = BrowserData.prevMenu;
		try {
			URL url;
			Parent root;
			Stage window;
			switch(prevMenu){
				case 1:
					url = new File("src/application/view/Main.fxml").toURI().toURL();
					root = FXMLLoader.load(url);
					window = (Stage) ((Node)event.getSource()).getScene().getWindow();
					window.setScene(new Scene(root));
					window.show();
					break;
				case 2:
					url = new File("src/application/view/Main.fxml").toURI().toURL();
					root = FXMLLoader.load(url);
					window = (Stage) ((Node)event.getSource()).getScene().getWindow();
					window.setScene(new Scene(root));
					window.show();
					break;
				case 0:
				default:
					url = new File("src/application/view/Main.fxml").toURI().toURL();
					root = FXMLLoader.load(url);
					window = (Stage) ((Node)event.getSource()).getScene().getWindow();
					window.setScene(new Scene(root));
					window.show();
					break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
