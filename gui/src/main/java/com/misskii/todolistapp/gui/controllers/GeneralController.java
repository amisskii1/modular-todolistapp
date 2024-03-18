package com.misskii.todolistapp.gui.controllers;

import com.misskii.todolistapp.dao.api.PersonApi;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;
import java.util.ServiceLoader;

public class GeneralController {
    private int userId;
    private Parent root;
    private Scene scene;
    private Stage stage;

    PersonApi personApi = ServiceLoader.load(PersonApi.class)
            .findFirst()
            .orElseThrow(() -> new RuntimeException("No implementation found"));

    public int currentUser(int userId) {
        int currentUser = -1;
        for (int i = 0; i < personApi.getAllPeople().size(); i++){
            if(personApi.getAllPeople().get(i).getId() == userId){
                currentUser = i;
            }
        }
        return currentUser;
    }
    public void switchToMainPage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/main-page.fxml"));
        root = loader.load();
        MainPageController mainPageController = loader.getController();
        mainPageController.displayUser(personApi.getAllPeople().get(currentUser(getUserId())).getId());
        mainPageController.fillTable();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToMainPage(ActionEvent event, int id) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/main-page.fxml"));
        root = loader.load();
        MainPageController mainPageController = loader.getController();
        mainPageController.displayUser(personApi.getAllPeople().get(id).getId());
        mainPageController.fillTable();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchFromMainPage(ActionEvent event, String fxml, int id) throws IOException {
        System.out.println(getClass().getResource("/views/"+fxml));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/"+fxml));
        root = loader.load();
        GeneralController generalController = loader.getController();
        generalController.setUserId(id);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void changeScene(ActionEvent event,String fxml) throws IOException{
        System.out.println(getClass().getResource("/"+fxml));
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/" + fxml)));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void displayError(String errorMessage){
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setHeaderText("Input not valid");
        errorAlert.setContentText(errorMessage);
        errorAlert.showAndWait();
    }

    public void openStage(String fxml) throws IOException {
        System.out.println(getClass().getResource("/" + fxml));
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/" + fxml)));
        Stage aboutStage = new Stage();
        aboutStage.setTitle("About");
        aboutStage.setScene(new Scene(root, 230, 237));
        aboutStage.show();
    }

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
}
