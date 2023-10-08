package com.misskii.javatodolistapp;

import com.misskii.javatodolistapp.DAO.PersonDAO;
import com.misskii.javatodolistapp.Models.Person;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RegisterPageController {
    private Parent root;
    private Scene scene;
    private Stage stage;

    @FXML
    private TextField userName;
    @FXML
    private TextField userEmailField;
    @FXML
    private PasswordField userPasswordField;
    @FXML
    private PasswordField userPasswordField2;
    private PersonDAO personDAO = new PersonDAO();

    public void displayError(String errorMessage){
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setHeaderText("Input not valid");
        errorAlert.setContentText(errorMessage);
        errorAlert.showAndWait();
    }

    public void createNewUser(ActionEvent event) throws IOException {
        List<String> emailsList = new ArrayList<>();

        if (userName.getText().isEmpty() || userEmailField.getText().isEmpty() || userPasswordField.getText().isEmpty() || userPasswordField2.getText().isEmpty()) {
            displayError("All fields must be filled in");
            return;
        } else if (!Objects.equals(userPasswordField.getText(), userPasswordField2.getText())) {
            displayError("Passwords does not match");
            return;
        }else{
            for (int i = 0; i < personDAO.loginUser().size(); i++) {
                emailsList.add(personDAO.loginUser().get(i).getEmail());
                if (Objects.equals(emailsList.get(i), userEmailField.getText())) {
                    displayError("User with this email already exists");
                    return;
                }
            }
        }
        personDAO.createNewPerson(new Person(userName.getText(), userEmailField.getText(), userPasswordField.getText()));
        root = FXMLLoader.load(getClass().getResource("login-page.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToLoginPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("login-page.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
