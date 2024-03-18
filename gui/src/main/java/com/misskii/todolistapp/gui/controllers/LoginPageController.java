package com.misskii.todolistapp.gui.controllers;

import com.misskii.todolistapp.dao.api.PersonApi;
import com.misskii.todolistapp.updater.api.UpdaterApi;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.util.Objects;
import java.util.ServiceLoader;

public class LoginPageController extends GeneralController {
    @FXML
    private TextField userEmail;
    @FXML
    private PasswordField userPassword;
    @FXML
    private TextArea version;

    PersonApi personApi = ServiceLoader.load(PersonApi.class)
            .findFirst()
            .orElseThrow(() -> new RuntimeException("No implementation found"));

    UpdaterApi updaterApi = ServiceLoader.load(UpdaterApi.class)
            .findFirst()
            .orElseThrow(() -> new RuntimeException("No implementation found"));

    public void switchToApp(ActionEvent event) throws IOException {
        for (int i = 0; i < personApi.getAllPeople().size(); i++) {
            if (Objects.equals(personApi.getAllPeople().get(i).getEmail(), userEmail.getText())
                    && Objects.equals(personApi.getAllPeople().get(i).getPassword(), userPassword.getText())) {
                switchToMainPage(event, i);
                return;
            }
        }
        displayError("Incorrect email or password");
    }

    public void switchToRegistration(ActionEvent event) throws IOException {
        changeScene(event, "views/register-page.fxml");
    }

    public void initialize(){
        if ( !updaterApi.compareVersions() ){
            version.setStyle("-fx-text-fill: red;");
            version.setText("It appears you might not be using the most up-to-date version." +
                    " \nYou can download the latest version from the following link:" +
                    "\nhttps://github.com/AntonMisskii/todolistApp/packages/1962002");
        }else version.setText("You're all up to date with the latest version!");
    }
}
