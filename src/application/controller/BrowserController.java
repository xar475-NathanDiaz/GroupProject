package application.controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import application.model.BrowserData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class BrowserController implements Initializable{

	@FXML
	private Button backBttn;

	@FXML
	private ListView<String> itemList;

	@FXML
	private Button openBttn;

	@FXML
	void openItem(ActionEvent event) {
		String selectedItem = itemList.getSelectionModel().getSelectedItem();
		int selectionIndex = itemList.getSelectionModel().getSelectedIndex();
		int listLength = itemList.getItems().size();
		//test if the user has selected a new item
		if(selectionIndex==listLength-1){
			//TODO open a blank menu, create blank file/object
			System.out.println("New items are unimplemented");
			//this return is temporary, should be taken out when new items are implemented
			return;
		}
		try {
			//if the menuType is 0, load Schedule.fxml, else load Profile.fxml
			URL url = (BrowserData.menuType==0)?new File("src/application/view/Schedule.fxml").toURI().toURL():new File("src/application/view/Profile.fxml").toURI().toURL();
			Parent root = FXMLLoader.load(url);
			Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
			window.setScene(new Scene(root));
			window.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	//TODO: when going back to the previous menu, need to load the data the program displaying too
	@FXML
	void prevMenu(ActionEvent event) {
		int prevMenu = BrowserData.prevMenu;
		try {
			URL url;
			Parent root;
			Stage window;
			switch(prevMenu){
				case 1:
					url = new File("src/application/view/Schedule.fxml").toURI().toURL();
					root = FXMLLoader.load(url);
					window = (Stage) ((Node)event.getSource()).getScene().getWindow();
					window.setScene(new Scene(root));
					window.show();
					break;
				case 2:
					url = new File("src/application/view/Profile.fxml").toURI().toURL();
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<String> list = FXCollections.observableArrayList();
		list.addAll(BrowserData.initItemList());
		itemList.setItems(list);
	}

}