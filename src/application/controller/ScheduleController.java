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
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ScheduleController {

	@FXML
    private Label schedName;

    @FXML
    private Button planBttn;

    @FXML
    private Button browserBttn;

    @FXML
    private Button mainBttn;

    @FXML
    void addPlan(ActionEvent event) {

    }

    @FXML
    void mainMenu(ActionEvent event) {
		try {
			BrowserData.prevMenu = 0;
			BrowserData.menuType = 0;
			URL url = new File("src/application/view/Main.fxml").toURI().toURL();
			Parent root = FXMLLoader.load(url);
			Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
			window.setScene(new Scene(root));
			window.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    }

    @FXML
    void openBrowser(ActionEvent event) {
		try {
			BrowserData.prevMenu = 0;
			BrowserData.menuType = 0;
			URL url = new File("src/application/view/ItemBrowser.fxml").toURI().toURL();
			Parent root = FXMLLoader.load(url);
			Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
			window.setScene(new Scene(root));
			window.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    }
}
