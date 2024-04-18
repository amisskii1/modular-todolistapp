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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.ServiceLoader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public void switchToMainPage(ActionEvent event, String status) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/main-page.fxml"));
        root = loader.load();
        MainPageController mainPageController = loader.getController();
        mainPageController.displayUser(personApi.getAllPeople().get(currentUser(getUserId())).getId());
        mainPageController.setLicenseStatus(status);
        mainPageController.fillTable();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToMainPage(ActionEvent event, int id, String status) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/main-page.fxml"));
        root = loader.load();
        MainPageController mainPageController = loader.getController();
        mainPageController.displayUser(personApi.getAllPeople().get(id).getId());
        mainPageController.setLicenseStatus(status);
        mainPageController.fillTable();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void switchToEditPage(ActionEvent event, int id, String status) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/edit-page.fxml"));
        root = loader.load();
        EditPageController editPageController = loader.getController();
        editPageController.setUserId(id);
        editPageController.setLicenseStatus(status);
        editPageController.checkLicense(status);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToCreatePage(ActionEvent event, int id, String status) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/add-page.fxml"));
        root = loader.load();
        AddPageController addPageController = loader.getController();
        addPageController.setUserId(id);
        addPageController.setLicenseStatus(status);
        addPageController.checkLicense(status);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void changeScene(ActionEvent event,String fxml) throws IOException{
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/views/"+fxml)));
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

    public void displayLicenseConfirmation(String licenseMessage){
        Alert licenseAlert = new Alert(Alert.AlertType.INFORMATION);
        licenseAlert.setHeaderText("License information");
        licenseAlert.setContentText(licenseMessage);
        licenseAlert.showAndWait();
    }

    public void openStage(String fxml) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/" + fxml)));
        Stage aboutStage = new Stage();
        aboutStage.setTitle("About");
        aboutStage.setScene(new Scene(root, 230, 237));
        aboutStage.show();
    }

    public boolean isValidDate(String input){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        try {
            Date date = sdf.parse(input);
            return sdf.format(date).equals(input);
        } catch (ParseException e) {
            return false;
        }
    }

    public boolean isEmailFormatValid(String email) {
        if (Objects.equals(email, "")) return false;
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
}