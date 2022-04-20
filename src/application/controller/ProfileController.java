package application.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ProfileController {

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

}
