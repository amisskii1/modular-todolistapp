package com.misskii.javatodolistapp;

import com.misskii.javatodolistapp.DAO.PersonDAO;
import com.misskii.javatodolistapp.DAO.TaskDAO;
import com.misskii.javatodolistapp.Models.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AddPageController {
    private Parent root;
    private Scene scene;
    private Stage stage;
    private TaskDAO taskDAO = new TaskDAO();
    private PersonDAO personDAO = new PersonDAO();



    private int userId;
    @FXML
    private TextField dueTo;

    @FXML
    private TextField taskTitle;

    @FXML
    private TextField taskDescription;

    public void getUserId(int id){
        userId=id;
    }


    public void createNewTask(ActionEvent event) throws IOException {
        int currentUser = 0;
        for (int i = 0; i < personDAO.loginUser().size(); i++){
            if(personDAO.loginUser().get(i).getId() == userId){
                currentUser = i;
            }
        }
        taskDAO.createNewTask(new Task(taskTitle.getText(), taskDescription.getText(), dueTo.getText(), userId, "In Progress"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main-page.fxml"));
        root = loader.load();
        MainPageController mainPageController = loader.getController();
        mainPageController.displayUser(personDAO.loginUser().get(currentUser).getId());
        mainPageController.fillTable();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
