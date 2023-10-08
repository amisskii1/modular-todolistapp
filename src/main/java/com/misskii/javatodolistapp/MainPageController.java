package com.misskii.javatodolistapp;

import com.misskii.javatodolistapp.DAO.TaskDAO;
import com.misskii.javatodolistapp.Models.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class MainPageController {
    private Parent root;
    private Scene scene;
    private Stage stage;
    private int userId;
    @FXML
    private TableView<Task> table;
    @FXML
    private TableColumn<Task, Integer> tableId;
    @FXML
    private TableColumn<Task, String> tableTitle;
    @FXML
    private TableColumn<Task, String> tableDescription;
    @FXML
    private TableColumn<Task, String> tableDueTo;
    @FXML
    private TableColumn<Task, String> tableStatus;
    TaskDAO taskDAO = new TaskDAO();

    public void fillTable(){
        for (int i = 0; i < taskDAO.selectAllTasksByPersonId(this.userId).size(); i++){
            tableId.setCellValueFactory(new PropertyValueFactory<Task, Integer>("taskId"));
            tableTitle.setCellValueFactory(new PropertyValueFactory<Task, String>("taskTitle"));
            tableDescription.setCellValueFactory(new PropertyValueFactory<Task, String>("taskDescription"));
            tableDueTo.setCellValueFactory(new PropertyValueFactory<Task, String>("dueTo"));
            tableStatus.setCellValueFactory(new PropertyValueFactory<Task, String>("status"));
            table.setItems(taskDAO.selectAllTasksByPersonId(this.userId));
        }
    }

    public void displayUser(int id){
        this.userId = id;
    }

    public void switchToCreate(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("add-page.fxml"));
        root = loader.load();
        AddPageController addPageController = loader.getController();
        addPageController.getUserId(this.userId);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToEdit(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("edit-page.fxml"));
        root = loader.load();
        EditPageController editPageController = loader.getController();
        editPageController.getUserId(this.userId);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
