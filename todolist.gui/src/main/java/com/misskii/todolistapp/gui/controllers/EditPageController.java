package com.misskii.todolistapp.gui.controllers;

import com.misskii.todolistapp.dao.api.TaskApi;
import com.misskii.todolistapp.entities.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import java.io.IOException;
import java.sql.Date;
import java.util.Objects;
import java.util.ServiceLoader;

public class EditPageController extends GeneralController {
    private String licenseStatus;
    @FXML
    public ToggleGroup priority;
    @FXML
    private TextField taskID;
    @FXML
    private TextField taskTitle;
    @FXML
    private TextField taskDesk;
    @FXML
    private TextField taskExp;
    @FXML
    private CheckBox markDone;
    @FXML
    private RadioButton priority1;
    @FXML
    private RadioButton priority2;
    @FXML
    private RadioButton priority3;
    @FXML
    private RadioButton priorityDefault;

    private Task task;

    TaskApi taskApi = ServiceLoader.load(TaskApi.class)
            .findFirst()
            .orElseThrow(() -> new RuntimeException("No implementation found"));

    public void confChangeTask(ActionEvent event) throws IOException {
        if(taskID.getText().isEmpty()){
            displayError("Id field should be filled");
            return;
        }
        if (!taskExp.getText().isEmpty() && !isValidDate(taskExp.getText())){
            displayError("Date field must be in format yyyy-MM-dd");
            return;
        }
        task = taskApi.getTaskByID(taskID.getText());
        if (task == null) {
            displayError("Task with ID " + taskID.getText() + " not found");
            return;
        }
        String status = markDone.isSelected() ? "Done" : "In Progress";
        String description = taskDesk.getText().isEmpty() ? task.getTaskDescription() : taskDesk.getText();
        String title = taskTitle.getText().isEmpty() ? task.getTaskTitle() : taskTitle.getText();
        Date expirationDate = taskExp.getText().isEmpty() ? task.getDate() : Date.valueOf(taskExp.getText());
        taskApi.updateTaskByID(currentUser(getUserId()) + 1, taskID.getText(), description, title, expirationDate, status, setPriority());
        switchToMainPage(event, licenseStatus);
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
        switchToMainPage(event, licenseStatus);
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