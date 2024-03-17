package com.misskii.todolistapp.gui.controllers;

import com.misskii.todolistapp.updater.Updater;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AboutController extends GeneralController{
    @FXML
    private TextField latestVersion;
    @FXML
    private TextField actualVersion;
    @FXML
    private TextField author;

    Updater updater = new Updater();

    public void initialize(){
        author.setEditable(false);
        actualVersion.setEditable(false);
        latestVersion.setEditable(false);
        if (!updater.compareVersions()){
            latestVersion.setStyle("-fx-text-fill: red;");
            actualVersion.setStyle("-fx-text-fill: green;");
        }else {
            latestVersion.setStyle("-fx-text-fill: green;");
            actualVersion.setStyle("-fx-text-fill: green;");
        }
        latestVersion.setText(updater.getLatestVersion());
        actualVersion.setText(updater.getActualVersion());
    }
}
