package com.misskii.javatodolistapp.Controllers;

import com.misskii.javatodolistapp.DAO.TaskDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.io.IOException;

public class EditPageController extends GeneralController {
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
    private TaskDAO taskDAO = new TaskDAO();

    public void confChangeTask(ActionEvent event) throws IOException {
        String status = "In Progress";
        if (markDone.isSelected()){
            status = "Done";
        }
        if(taskID.getText().isEmpty()){
            displayError("Id field should be filled");
        }
        taskDAO.updateTaskByID(currentUser(getUserId())+1 ,taskID.getText(), taskDesk.getText(), taskTitle.getText(), taskExp.getText(), status);
        switchToMainPage(event);
    }

    public void cancel(ActionEvent event) throws IOException {
        switchToMainPage(event);
    }
}
