package com.misskii.javatodolistapp;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    private Parent root;
    private static Scene scene;
    private static Stage stg;

    @Override
    public void start(Stage stage) throws IOException {
        stg = stage;
        Parent root = FXMLLoader.load(getClass().getResource("login-page.fxml"));
        stage.setTitle("TO-DO List Application");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void changeScene(String fxml) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        stg.setScene(new Scene(root));
        stg.show();
    }


    public static void main(String[] args) {
        launch();
    }
}