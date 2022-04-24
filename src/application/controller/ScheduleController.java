package application.controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import application.model.BrowserData;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ScheduleController implements Initializable{
	private Schedule currSchedule;

	@FXML
	private TextField schedName;

	@FXML
	private Button planBttn;

	@FXML
	private Button browserBttn;

	@FXML
	private HBox daysBar;

	@FXML
	private ToggleButton sunBttn;

	@FXML
	private ToggleButton monBttn;

	@FXML
	private ToggleButton tuesBttn;

	@FXML
	private ToggleButton wedBttn;

	@FXML
	private ToggleButton thursBttn;

	@FXML
	private ToggleButton friBttn;

	@FXML
	private ToggleButton satBttn;

	@FXML
	private ListView<String> planView;

	@FXML
	private ListView<String> timeView;

	@FXML
	private Button mainBttn;

	@FXML
	private Button saveBttn;

	@FXML
	void addPlan(ActionEvent event) {
		try {
			URL url = new File("src/application/view/Planner.fxml").toURI().toURL();
			Parent root = FXMLLoader.load(url);
			Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
			window.setScene(new Scene(root));
			window.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void changeDayView(ActionEvent event) {
		String bttnLabel = ((ToggleButton)event.getSource()).getText();
		ObservableList<String> planNameList = FXCollections.observableArrayList();
		//need an extra list since the views areset by pointer and not by value as I initially thought
		ObservableList<String> planTimeList = FXCollections.observableArrayList();
		char dayInitial;
		if(bttnLabel.equals("Sunday")){
			dayInitial = 'U';
		}else if(bttnLabel.equals("Monday")){
			dayInitial = 'M';
		}else if(bttnLabel.equals("Tuesday")){
			dayInitial = 'T';
		}else if(bttnLabel.equals("Wednesday")){
			dayInitial = 'W';
		}else if(bttnLabel.equals("Thursday")){
			dayInitial = 'R';
		}else if(bttnLabel.equals("Friday")){
			dayInitial = 'F';
		}else{//default case, assumed to be Saturday
			dayInitial = 'S';
		}
		planNameList.addAll(currSchedule.listPlanNamesByDay(dayInitial));
		planView.setItems(planNameList);

		planTimeList.addAll(currSchedule.listPlanTimesByDay(dayInitial));
		timeView.setItems(planTimeList);
	}

	@FXML
	void mainMenu(ActionEvent event) {
		try {
			BrowserData.prevMenu = 0;
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
			BrowserData.prevMenu = 1;
			URL url = new File("src/application/view/ItemBrowser.fxml").toURI().toURL();
			Parent root = FXMLLoader.load(url);
			Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
			window.setScene(new Scene(root));
			window.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void saveSchedule(ActionEvent event) {
		if(schedName.getText().isEmpty()){
			Schedule.loadFile = "";
			return;
		}
		String pathFile = "data/"+schedName.getText()+".txt";
		Schedule.loadFile = pathFile;
		currSchedule.updateSchedule();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//group all the day buttons into a groups so that they are all mutually exclusive
		ToggleGroup group = new ToggleGroup();
		sunBttn.setToggleGroup(group);
		monBttn.setToggleGroup(group);
		tuesBttn.setToggleGroup(group);
		wedBttn.setToggleGroup(group);
		thursBttn.setToggleGroup(group);
		friBttn.setToggleGroup(group);
		satBttn.setToggleGroup(group);
		sunBttn.setSelected(true);

		//create the schedule object for the controller
		currSchedule = new Schedule();

		//if Schedule.loadFile blank/empty, then that means this is a new schedule, and shouldn't load any plans
		if(Schedule.loadFile.isEmpty()){
			return;
		}

		//from the schedule, load the plans
		ObservableList<String> planNameList = FXCollections.observableArrayList();
		planNameList.addAll(currSchedule.listPlanNamesByDay('U'));
		planView.setItems(planNameList);

		//load plan times
		ObservableList<String> planTimeList = FXCollections.observableArrayList();
		planTimeList.addAll(currSchedule.listPlanTimesByDay('U'));
		timeView.setItems(planTimeList);

		//from the scedule, load the name
		schedName.setText(currSchedule.getName());
	}
}
