package com.misskii.todolistapp.gui.controllers;

import com.fasterxml.jackson.core.JsonProcessingException; //transitive dependency
import com.misskii.todolistapp.dao.api.TaskApi;
import com.misskii.todolistapp.entities.Task;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;
import java.sql.Date;
import java.util.Objects;
import java.util.ServiceLoader;

public class MainPageController extends GeneralController {
    private int userId;
    private String licenseStatus;
    @FXML
    private TableView<Task> table;
    @FXML
    private TableColumn<Task, Integer> tableId;
    @FXML
    private TableColumn<Task, String> tableTitle;
    @FXML
    private TableColumn<Task, String> tableDescription;
    @FXML
    private TableColumn<Task, Date> tableDueTo;
    @FXML
    private TableColumn<Task, String> tableStatus;

    TaskApi taskApi = ServiceLoader.load(TaskApi.class)
            .findFirst()
            .orElseThrow(() -> new RuntimeException("No implementation found"));

    public void fillTable() throws JsonProcessingException {
        ObservableList<Task> tasks = taskApi.selectAllTasksByPersonId(this.userId);
        tableId.setCellValueFactory(new PropertyValueFactory<Task, Integer>("taskId"));
        tableTitle.setCellValueFactory(new PropertyValueFactory<Task, String>("taskTitle"));
        tableDescription.setCellValueFactory(new PropertyValueFactory<Task, String>("taskDescription"));
        tableDueTo.setCellValueFactory(new PropertyValueFactory<Task, Date>("date"));
        tableStatus.setCellValueFactory(new PropertyValueFactory<Task, String>("status"));
        setCellFactoryBasedOnLicenseStatus(this.licenseStatus);
        table.setItems(tasks);
    }

    public void setCellFactoryBasedOnLicenseStatus(String status){
        if (Objects.equals(status, "valid")){
            setCellFactoryForColumn(tableId);
            setCellFactoryForColumn(tableTitle);
            setCellFactoryForColumn(tableDescription);
            setCellFactoryForColumn(tableDueTo);
            setCellFactoryForColumn(tableStatus);
        }
    }

    private <T> void setCellFactoryForColumn(TableColumn<Task, T> column) {
        column.setCellFactory(tc -> new TableCell<Task, T>() {
            @Override
            protected void updateItem(T item, boolean empty) {
                super.updateItem(item, empty);

                if (item == null || empty) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(item.toString());
                    String priority = getTableView().getItems().get(getIndex()).getPriorityStatus();
                    setStyle(styleByPriority(priority));
                }
            }
        });
    }

    public void displayUser(int id){
        this.userId = id;
    }

    public void switchToCreate(ActionEvent event) throws IOException {
        switchToCreatePage(event, this.userId, licenseStatus);
    }

    public void switchToEdit(ActionEvent event) throws IOException {
        switchToEditPage(event, this.userId, licenseStatus);
    }

    public void aboutStage(ActionEvent event) throws IOException {
        openStage("about.fxml");
    }

    public String styleByPriority(String priority){
        if (Objects.equals(priority, "priority1")){
            return "-fx-text-fill: green;";
        } else if (Objects.equals(priority, "priority2")) {
            return "-fx-text-fill: blue;";
        } else if (Objects.equals(priority, "priority3")) {
            return "-fx-text-fill: orange;";
        }
        return "";
    }
    public void setLicenseStatus(String licenseStatus) {
        this.licenseStatus = licenseStatus;
    }

    public void logout(ActionEvent event) throws IOException {
        changeScene(event, "login-page.fxml");
    }
}