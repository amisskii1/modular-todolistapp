package com.misskii.todolistapp.gui.controllers;

import com.misskii.todolistapp.dao.api.LicenseApi;
import com.misskii.todolistapp.dao.api.PersonApi;
import com.misskii.todolistapp.license.api.LicenseClientApi;
import com.misskii.todolistapp.updater.api.UpdaterApi;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
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
    LicenseApi licenseApi = ServiceLoader.load(LicenseApi.class)
            .findFirst()
            .orElseThrow(() -> new RuntimeException("No implementation found"));

    LicenseClientApi licenseClientApi = ServiceLoader.load(LicenseClientApi.class)
            .findFirst()
            .orElseThrow(() -> new RuntimeException("No implementation found"));


    UpdaterApi updaterApi = ServiceLoader.load(UpdaterApi.class)
            .findFirst()
            .orElseThrow(() -> new RuntimeException("No implementation found"));


    public void switchToApp(ActionEvent event) throws IOException {
        if (!isEmailFormatValid(userEmail.getText())) {
            displayError("Email is not valid");
            return;
        }
        for (int i = 0; i < personApi.getAllPeople().size(); i++) {
            if (Objects.equals(personApi.getAllPeople().get(i).getEmail(), userEmail.getText())
                    && Objects.equals(personApi.getAllPeople().get(i).getPassword(), userPassword.getText())) {
                try {
                    List<String> licenseData = licenseClientApi.validateLicenseKey(userEmail.getText(), licenseApi.getLicenseValueByUserID(i+1));
                    licenseApi.updateLicenseStatus(licenseData.get(0), LocalDateTime.parse(licenseData.get(1)),i+1);
                }catch (Exception e){
                    if (!LocalDateTime.now().isBefore(licenseApi.getExpireDate(i+1))){
                        licenseApi.updateLicenseStatus("invalid", i+1);
                    }
                }
                String licenseStatus = licenseApi.getLicenseStatus(i+1);
                if (Objects.equals(licenseStatus, "valid")){
                    displayLicenseConfirmation("Your license is active and valid");
                }else{
                    displayLicenseConfirmation("Your license is not active or invalid");
                }
                switchToMainPage(event, i, licenseStatus);
                return;
            }
        }
        displayError("Incorrect email or password");
    }

    public void switchToRegistration(ActionEvent event) throws IOException {
        changeScene(event, "register-page.fxml");
    }

    public void initialize(){
        if ( !updaterApi.compareVersions() ){
            version.setStyle("-fx-text-fill: red;");
            version.setText("It appearss you might not be using the most up-to-date version." +
                    " \nYou can download the latest version from the following link:" +
                    "\nhttps://github.com/AntonMisskii/todolistApp/packages/1962002");
        }else version.setText("You're all up to date with the latest version!");
    }

    public void switchToLicense(ActionEvent event) throws IOException {
        changeScene(event, "license-page.fxml");
    }
}
