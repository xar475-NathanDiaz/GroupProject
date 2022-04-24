package application.controller;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.model.Plan;
import application.model.Schedule;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class PlannerController implements Initializable{

	@FXML
	private Button cancelBttn;

	@FXML
	private VBox typeBttns;

	@FXML
	private VBox dayBttns;

	@FXML
	private TextField nameField;

	@FXML
	private Button saveBttn;

	@FXML
	private Spinner<Integer> hourSelector;

	@FXML
	private Spinner<Integer> minuteSelector;

	@FXML
	private ChoiceBox<String> amPmSelector;

	@FXML
	void cancelPlan(ActionEvent event) {
		try {
			URL url = new File("src/application/view/Schedule.fxml").toURI().toURL();
			Parent root = FXMLLoader.load(url);
			Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
			window.setScene(new Scene(root));
			window.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void savePlan(ActionEvent event) {
		Plan newPlan = new Plan();
		int time = (hourSelector.getValue()%12)*60 + 720*((amPmSelector.getValue().equals("PM"))?1:0) + minuteSelector.getValue();
		String dayString = "";
		newPlan.setName(nameField.getText());

		//get the selected buttons to make the dayString
		//the buttons are grouped in a Vbox, so first get the children of that box
		ObservableList<Node> dayBttnList = dayBttns.getChildren();
		for (Node node : dayBttnList) {
			//for each child node, cast it to checkbox
			CheckBox dayBttn = (CheckBox) node;
			//see if that one is selected
			if(dayBttn.isSelected()){
				dayString = dayString + Plan.dayNameToInitial(dayBttn.getText());
			}
		}
		newPlan.setDaysFromString(dayString);
		newPlan.setTime(time);

		//get the label from the selected button from typeBttns to set the type
		//these buttons are also grouped in a Vbox, so need to get the children of that box
		//however, these buttons are also in a toggleGroup, so by accessing the toggleGroup of only the first button, the selected button can be accessed
		String typeString = ((RadioButton) ((RadioButton) typeBttns.getChildren().get(0)).getToggleGroup().getSelectedToggle()).getText();
		newPlan.setPlanFromString(typeString);

		Schedule addingSchedule = new Schedule();
		addingSchedule.addPlan(newPlan);

		//once the plan has been saved, go back to the schedule controller
		try {
			URL url = new File("src/application/view/Schedule.fxml").toURI().toURL();
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
		//add type buttons to a toggle group and setting the class button to selected
		ToggleGroup group = new ToggleGroup();
		ObservableList<Node> toggleBttnList = typeBttns.getChildren();
		for (Node node : toggleBttnList) {
			ToggleButton bttn = (ToggleButton) node;
			bttn.setToggleGroup(group);
		}
		//set the first button as selected
		((RadioButton) toggleBttnList.get(0)).setSelected(true);

		//set up how the spinners will work
		SpinnerValueFactory<Integer> hourFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 12, 12);
		hourFactory.setWrapAround(true);
		SpinnerValueFactory<Integer> minuteFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0);
		minuteFactory.setWrapAround(true);
		hourSelector.setValueFactory(hourFactory);
		minuteSelector.setValueFactory(minuteFactory);

		//set up the am/pm selector
		ArrayList<String> selectorItems = new ArrayList<>();
		selectorItems.add("AM");
		selectorItems.add("PM");
		amPmSelector.setItems(FXCollections.observableArrayList(selectorItems));
		amPmSelector.setValue("AM");
	}

}