package com.misskii.javatodolistapp;

import com.misskii.javatodolistapp.DAO.PersonDAO;
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
import java.util.Objects;

public class LoginPageController {
    @FXML
    TextField userEmail;

    @FXML
    PasswordField userPassword;

    private Parent root;
    private Stage stage;
    private Scene scene;

    PersonDAO personDAO = new PersonDAO();

    public void switchToApp(ActionEvent event) throws IOException {
        for (int i = 0; i < personDAO.loginUser().size(); i++){
            if(!Objects.equals(personDAO.loginUser().get(i).getEmail(), userEmail.getText()) || !Objects.equals(personDAO.loginUser().get(i).getPassword(), userPassword.getText())){
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText("Input not valid");
                errorAlert.setContentText("Incorrect email or password");
                errorAlert.showAndWait();
                return;
            }else{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("main-page.fxml"));
                root = loader.load();
                MainPageController mainPageController = loader.getController();
                mainPageController.displayUser(personDAO.loginUser().get(i).getId());
                mainPageController.fillTable();
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                break;

            }
        }
    }

    public void switchToRegistration(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("register-page.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
