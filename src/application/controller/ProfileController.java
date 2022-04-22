package application.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.model.Profile;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ProfileController implements Initializable{

	@FXML
	private AnchorPane anchor;
	 
	@FXML
	private TextField nameField;

	@FXML
	private TextField gpaField;

	@FXML
	private Button editBttn;

	@FXML
	private ListView<String> assignmentList;

	@FXML
	private ListView<String> scheduleList;
	
	@FXML
   	private Button mainBttn;

    @FXML
    private void mainMenu(ActionEvent event) throws IOException {
		try {
			URL url = new File("src/application/view/Main.fxml").toURI().toURL();
			Parent root = FXMLLoader.load(url);
			Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
			window.setScene(new Scene(root));
			window.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<String> list = FXCollections.observableArrayList();
		list.addAll(Profile.getSchedulesFromFile());
		scheduleList.setItems(list);
	}

}
