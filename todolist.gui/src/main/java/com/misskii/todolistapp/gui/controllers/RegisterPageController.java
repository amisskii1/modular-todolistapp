package com.misskii.todolistapp.gui.controllers;

import com.misskii.todolistapp.dao.api.PersonApi;
import com.misskii.todolistapp.entities.Person;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.util.*;

public class RegisterPageController extends GeneralController {
    @FXML
    private TextField userName;
    @FXML
    private TextField userEmailField;
    @FXML
    private PasswordField userPasswordField;
    @FXML
    private PasswordField userPasswordField2;
    PersonApi personApi = ServiceLoader.load(PersonApi.class)
            .findFirst()
            .orElseThrow(() -> new RuntimeException("No implementation found"));
    public void createNewUser(ActionEvent event) throws IOException {
        List<String> emailsList = new ArrayList<>();
        if (userName.getText().isEmpty() || userEmailField.getText().isEmpty() || userPasswordField.getText().isEmpty() || userPasswordField2.getText().isEmpty()) {
            displayError("All fields must be filled in");
            return;
        } else if (!Objects.equals(userPasswordField.getText(), userPasswordField2.getText())) {
            displayError("Passwords do not match");
            return;
        }else{
            for (int i = 0; i < personApi.getAllPeople().size(); i++) {
                emailsList.add(personApi.getAllPeople().get(i).getEmail());
                if (Objects.equals(emailsList.get(i), userEmailField.getText())) {
                    displayError("User with this email already exists");
                    return;
                }
            }
        }
        personApi.createNewPerson(new Person(userName.getText(), userEmailField.getText(), userPasswordField.getText()));
        changeScene(event, "login-page.fxml");
    }

    public void switchToLoginPage(ActionEvent event) throws IOException {
        changeScene(event, "login-page.fxml");
    }
}
