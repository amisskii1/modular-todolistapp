package com.misskii.todolistapp.updater.controllers;

import com.misskii.javatodolistapp.dao.TaskDao;
import com.misskii.javatodolistapp.entities.Task;
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
    private TaskDao taskDao = new TaskDao();

    private Task task;


    public void confChangeTask(ActionEvent event) throws IOException {
        if(taskID.getText().isEmpty()){
            displayError("Id field should be filled");
        }
        task = taskDao.getTaskByID(taskID.getText());
        String status = "In Progress";
        if (markDone.isSelected()){
            status = "Done";
        }
        if (taskDesk.getText().isEmpty()){
            taskDao.updateTaskByID(currentUser(getUserId())+1 ,taskID.getText(), task.getTaskDescription(),
                    taskTitle.getText(), taskExp.getText(), status);
        }
        if (taskTitle.getText().isEmpty()){
            taskDao.updateTaskByID(currentUser(getUserId())+1 ,taskID.getText(), task.getTaskDescription(),
                    task.getTaskTitle(), taskExp.getText(), status);
        }
        if (taskExp.getText().isEmpty()){
            taskDao.updateTaskByID(currentUser(getUserId())+1 ,taskID.getText(), task.getTaskDescription(),
                    taskTitle.getText(), task.getDueTo(), status);
        }
        if (taskDesk.getText().isEmpty() && taskTitle.getText().isEmpty()){
            taskDao.updateTaskByID(currentUser(getUserId())+1 ,taskID.getText(), task.getTaskDescription(),
                    task.getTaskTitle(), taskExp.getText(), status);
        }
        if (taskDesk.getText().isEmpty() && taskExp.getText().isEmpty()){
            taskDao.updateTaskByID(currentUser(getUserId())+1 ,taskID.getText(), task.getTaskDescription(),
                    taskTitle.getText(), task.getDueTo(), status);
        }
        if (taskTitle.getText().isEmpty() && taskExp.getText().isEmpty()){
            taskDao.updateTaskByID(currentUser(getUserId())+1 ,taskID.getText(), taskDesk.getText(),
                    task.getTaskTitle(), task.getDueTo(), status);
        }
        if (taskExp.getText().isEmpty() && taskTitle.getText().isEmpty()
                && taskDesk.getText().isEmpty()) {
            taskDao.updateTaskByID(currentUser(getUserId())+1 ,taskID.getText(), task.getTaskDescription(),
                    task.getTaskTitle(), task.getDueTo(), status);
        }
        if (!taskDesk.getText().isEmpty() && !taskTitle.getText().isEmpty() && !taskExp.getText().isEmpty()){
            taskDao.updateTaskByID(currentUser(getUserId())+1 ,taskID.getText(), taskDesk.getText(),
                    taskTitle.getText(), taskExp.getText(), status);
        }
        switchToMainPage(event);
    }

    public void cancel(ActionEvent event) throws IOException {
        switchToMainPage(event);
    }
}
