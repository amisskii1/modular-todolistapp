package com.misskii.todolistapp.gui.controllers;

import com.misskii.todolistapp.dao.api.TaskApi;
import com.misskii.todolistapp.entities.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.util.ServiceLoader;

public class AddPageController extends GeneralController {
    @FXML
    private TextField dueTo;

    @FXML
    private TextField taskTitle;

    @FXML
    private TextField taskDescription;

    TaskApi taskApi = ServiceLoader.load(TaskApi.class)
            .findFirst()
            .orElseThrow(() -> new RuntimeException("No implementation found"));

    public void createNewTask(ActionEvent event) throws IOException {
        taskApi.createNewTask(new Task(taskTitle.getText(), taskDescription.getText(), dueTo.getText(), currentUser(getUserId()) + 1, "In Progress"));
        switchToMainPage(event);
    }

    public void cancel(ActionEvent event) throws IOException {
        switchToMainPage(event);
    }
}
