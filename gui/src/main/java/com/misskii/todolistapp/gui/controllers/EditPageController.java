package com.misskii.todolistapp.gui.controllers;

import com.misskii.todolistapp.dao.api.TaskApi;
import com.misskii.todolistapp.entities.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.util.ServiceLoader;

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

    private Task task;

    TaskApi taskApi = ServiceLoader.load(TaskApi.class)
            .findFirst()
            .orElseThrow(() -> new RuntimeException("No implementation found"));

    public void confChangeTask(ActionEvent event) throws IOException {
        if(taskID.getText().isEmpty()){
            displayError("Id field should be filled");
        }
        task = taskApi.getTaskByID(taskID.getText());
        String status = "In Progress";
        if (markDone.isSelected()){
            status = "Done";
        }
        if (taskDesk.getText().isEmpty()){
            taskApi.updateTaskByID(currentUser(getUserId())+1 ,taskID.getText(), task.getTaskDescription(),
                    taskTitle.getText(), taskExp.getText(), status);
        }
        if (taskTitle.getText().isEmpty()){
            taskApi.updateTaskByID(currentUser(getUserId())+1 ,taskID.getText(), task.getTaskDescription(),
                    task.getTaskTitle(), taskExp.getText(), status);
        }
        if (taskExp.getText().isEmpty()){
            taskApi.updateTaskByID(currentUser(getUserId())+1 ,taskID.getText(), task.getTaskDescription(),
                    taskTitle.getText(), task.getDueTo(), status);
        }
        if (taskDesk.getText().isEmpty() && taskTitle.getText().isEmpty()){
            taskApi.updateTaskByID(currentUser(getUserId())+1 ,taskID.getText(), task.getTaskDescription(),
                    task.getTaskTitle(), taskExp.getText(), status);
        }
        if (taskDesk.getText().isEmpty() && taskExp.getText().isEmpty()){
            taskApi.updateTaskByID(currentUser(getUserId())+1 ,taskID.getText(), task.getTaskDescription(),
                    taskTitle.getText(), task.getDueTo(), status);
        }
        if (taskTitle.getText().isEmpty() && taskExp.getText().isEmpty()){
            taskApi.updateTaskByID(currentUser(getUserId())+1 ,taskID.getText(), taskDesk.getText(),
                    task.getTaskTitle(), task.getDueTo(), status);
        }
        if (taskExp.getText().isEmpty() && taskTitle.getText().isEmpty()
                && taskDesk.getText().isEmpty()) {
            taskApi.updateTaskByID(currentUser(getUserId())+1 ,taskID.getText(), task.getTaskDescription(),
                    task.getTaskTitle(), task.getDueTo(), status);
        }
        if (!taskDesk.getText().isEmpty() && !taskTitle.getText().isEmpty() && !taskExp.getText().isEmpty()){
            taskApi.updateTaskByID(currentUser(getUserId())+1 ,taskID.getText(), taskDesk.getText(),
                    taskTitle.getText(), taskExp.getText(), status);
        }
        switchToMainPage(event);
    }

    public void cancel(ActionEvent event) throws IOException {
        switchToMainPage(event);
    }
}
