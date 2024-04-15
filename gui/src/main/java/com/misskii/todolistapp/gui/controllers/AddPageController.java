package com.misskii.todolistapp.gui.controllers;

import com.misskii.todolistapp.dao.api.TaskApi;
import com.misskii.todolistapp.entities.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import java.io.IOException;
import java.sql.Date;
import java.util.Objects;
import java.util.ServiceLoader;

public class AddPageController extends GeneralController {
    private String licenseStatus;
    @FXML
    public ToggleGroup priority;
    @FXML
    private TextField taskDate;
    @FXML
    private TextField taskTitle;
    @FXML
    private TextField taskDescription;
    @FXML
    private RadioButton priority1;
    @FXML
    private RadioButton priority2;
    @FXML
    private RadioButton priority3;
    @FXML
    private RadioButton priorityDefault;

    TaskApi taskApi = ServiceLoader.load(TaskApi.class)
            .findFirst()
            .orElseThrow(() -> new RuntimeException("No implementation found"));

    public void createNewTask(ActionEvent event) throws IOException {
        if (!isValidDate(taskDate.getText())){
            displayError("Date field must be in format yyyy-MM-dd");
            return;
        }
        taskApi.createNewTask(new Task(taskTitle.getText(), taskDescription.getText(), Date.valueOf(taskDate.getText()),
                currentUser(getUserId()) + 1, "In Progress", setPriority()));
        switchToMainPage(event, this.licenseStatus);
    }

    public String setPriority(){
        if (priority1.isSelected()){
            return "priority1";
        } else if (priority2.isSelected()) {
            return "priority2";
        } else if (priority3.isSelected()) {
            return "priority3";
        }
        return "default";
    }

    public void cancel(ActionEvent event) throws IOException {
        switchToMainPage(event, this.licenseStatus);
    }

    public String isLicenseStatus() {
        return licenseStatus;
    }

    public void setLicenseStatus(String licenseStatus) {
        this.licenseStatus = licenseStatus;
    }

    public void checkLicense(String status) {
        priorityDefault.setSelected(true);
        if (!Objects.equals(status, "valid")){
            priority1.setDisable(true);
            priority2.setDisable(true);
            priority3.setDisable(true);
        }
    }
}