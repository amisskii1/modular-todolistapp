package com.misskii.todolistapp.gui.controllers;


import com.misskii.todolistapp.dao.api.TaskApi;
import com.misskii.todolistapp.updater.api.UpdaterApi;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.util.ServiceLoader;

public class AboutController extends GeneralController{
    @FXML
    private TextField latestVersion;
    @FXML
    private TextField actualVersion;
    @FXML
    private TextField author;

    UpdaterApi updaterApi = ServiceLoader.load(UpdaterApi.class)
            .findFirst()
            .orElseThrow(() -> new RuntimeException("No implementation found"));

    public void initialize(){
        author.setEditable(false);
        actualVersion.setEditable(false);
        latestVersion.setEditable(false);
        if (!updaterApi.compareVersions()){
            latestVersion.setStyle("-fx-text-fill: red;");
            actualVersion.setStyle("-fx-text-fill: green;");
        }else {
            latestVersion.setStyle("-fx-text-fill: green;");
            actualVersion.setStyle("-fx-text-fill: green;");
        }
        latestVersion.setText(updaterApi.getLatestVersion());
        actualVersion.setText(updaterApi.getActualVersion());
    }
}
