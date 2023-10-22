package com.misskii.javatodolistapp.Controllers;

import com.misskii.javatodolistapp.Updater.Updater;
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
        if (!updater.compareActualVersionWithPackageLatestVersion()){
            latestVersion.setStyle("-fx-text-fill: red;");
            actualVersion.setStyle("-fx-text-fill: green;");
        }else {
            latestVersion.setStyle("-fx-text-fill: green;");
            actualVersion.setStyle("-fx-text-fill: green;");
        }
        latestVersion.setText(updater.extractLatestVersionFromJsonFile());
        actualVersion.setText(updater.extractActualVersionFromFile());
    }
}
