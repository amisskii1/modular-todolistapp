package com.misskii.javatodolistapp;

import com.misskii.javatodolistapp.DAO.PersonDAO;
import com.misskii.javatodolistapp.DAO.TaskDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class EditPageController {
    private int userId;
    private Parent root;
    private Scene scene;
    private Stage stage;

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
    private PersonDAO personDAO = new PersonDAO();

    public void getUserId(int id){
        userId=id;
    }

    public void confChangeTask(ActionEvent event) throws IOException {
        int currentUser = 0;
        for (int i = 0; i < personDAO.loginUser().size(); i++){
            if(personDAO.loginUser().get(i).getId() == userId){
                currentUser = i;
            }
        }
        String status = "In Progress";
        if (markDone.isSelected()){
            status = "Done";
        }
        if(taskID.getText().isEmpty()){
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Input not valid");
            errorAlert.setContentText("Id field should be filled");
            errorAlert.showAndWait();
            return;
        }
        taskDAO.updateTaskByID(taskID.getText(), taskDesk.getText(), taskTitle.getText(), taskExp.getText(), status);
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
