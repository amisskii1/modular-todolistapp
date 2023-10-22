package com.misskii.javatodolistapp.Controllers;

import com.misskii.javatodolistapp.DAO.TaskDAO;
import com.misskii.javatodolistapp.Models.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import java.io.IOException;

public class AddPageController extends GeneralController {
    private TaskDAO taskDAO = new TaskDAO();

    @FXML
    private TextField dueTo;

    @FXML
    private TextField taskTitle;

    @FXML
    private TextField taskDescription;

    public void createNewTask(ActionEvent event) throws IOException {
        taskDAO.createNewTask(new Task(taskTitle.getText(), taskDescription.getText(), dueTo.getText(), currentUser(getUserId()) + 1, "In Progress"));
        switchToMainPage(event);
    }

    public void cancel(ActionEvent event) throws IOException {
        switchToMainPage(event);
    }
}
