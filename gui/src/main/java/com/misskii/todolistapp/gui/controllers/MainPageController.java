package com.misskii.todolistapp.gui.controllers;

import com.misskii.todolistapp.dao.api.TaskApi;
import com.misskii.todolistapp.entities.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;
import java.util.ServiceLoader;

public class MainPageController extends GeneralController {
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

    TaskApi taskApi = ServiceLoader.load(TaskApi.class)
            .findFirst()
            .orElseThrow(() -> new RuntimeException("No implementation found"));

    public void fillTable(){
        for (int i = 0; i < taskApi.selectAllTasksByPersonId(this.userId).size(); i++){
            tableId.setCellValueFactory(new PropertyValueFactory<Task, Integer>("taskId"));
            tableTitle.setCellValueFactory(new PropertyValueFactory<Task, String>("taskTitle"));
            tableDescription.setCellValueFactory(new PropertyValueFactory<Task, String>("taskDescription"));
            tableDueTo.setCellValueFactory(new PropertyValueFactory<Task, String>("dueTo"));
            tableStatus.setCellValueFactory(new PropertyValueFactory<Task, String>("status"));
            table.setItems(taskApi.selectAllTasksByPersonId(this.userId));
        }
    }

    public void displayUser(int id){
        this.userId = id;
    }

    public void switchToCreate(ActionEvent event) throws IOException {
        switchFromMainPage(event, "add-page.fxml", this.userId);
    }

    public void switchToEdit(ActionEvent event) throws IOException {
        switchFromMainPage(event, "edit-page.fxml", this.userId);
    }

    public void aboutStage(ActionEvent event) throws IOException {
        openStage("views/about.fxml");
    }
}
