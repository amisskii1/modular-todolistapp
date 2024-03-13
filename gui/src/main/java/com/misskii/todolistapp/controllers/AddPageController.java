package com.misskii.todolistapp.controllers;

import com.misskii.javatodolistapp.dao.TaskDao;
import com.misskii.javatodolistapp.entities.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import java.io.IOException;

public class AddPageController extends GeneralController {
    private TaskDao taskDao = new TaskDao();

    @FXML
    private TextField dueTo;

    @FXML
    private TextField taskTitle;

    @FXML
    private TextField taskDescription;

    public void createNewTask(ActionEvent event) throws IOException {
        taskDao.createNewTask(new Task(taskTitle.getText(), taskDescription.getText(), dueTo.getText(), currentUser(getUserId()) + 1, "In Progress"));
        switchToMainPage(event);
    }

    public void cancel(ActionEvent event) throws IOException {
        switchToMainPage(event);
    }
}
