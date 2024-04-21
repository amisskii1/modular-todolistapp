package com.misskii.todolistapp.gui.controllers;

import com.misskii.todolistapp.dao.api.LicenseApi;
import com.misskii.todolistapp.dao.exceptions.PersonNotExistsException;
import com.misskii.todolistapp.license.api.LicenseClientApi;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ServiceLoader;

public class LicensePageController extends GeneralController {
    @FXML
    private TextField licenseValue;
    @FXML
    private TextField email;

    LicenseClientApi licenseClientApi = ServiceLoader.load(LicenseClientApi.class)
            .findFirst()
            .orElseThrow(() -> new RuntimeException("No implementation found"));

    LicenseApi licenseApi = ServiceLoader.load(LicenseApi.class)
            .findFirst()
            .orElseThrow(() -> new RuntimeException("No implementation found"));

    public void submitLicense(ActionEvent event) throws IOException {
        if (!isEmailFormatValid(email.getText())) {
            displayError("Email is not valid");
            return;
        }
        if (!isLicenseValueValid()) {
            displayError("License value is not valid");
            return;
        }
        try {
            licenseApi.save(email.getText(),licenseValue.getText());
        }catch (SQLException e) {
            licenseApi.update(email.getText(), licenseValue.getText());
        } catch (PersonNotExistsException e){
            displayError("User with this email does not exist");
            return;
        }
        changeScene(event, "login-page.fxml");
    }

    public void getTrialLicense(ActionEvent event) {
        if (!isEmailFormatValid(email.getText())) {
            displayError("Email is not valid");
            return;
        }

        try {
            String licenseTrialResult = null;
            try {
                licenseTrialResult = licenseClientApi.createTrialLicense(email.getText());
            } catch (RuntimeException e) {
                displayError(e.getMessage());
                return;
            }

            if (Objects.equals(licenseTrialResult, "Trial license can not be activated")) {
                displayError("Trial license can not be activated");
                return;
            }

            try {
                licenseApi.save(email.getText(), licenseTrialResult);
                changeScene(event, "login-page.fxml");
            } catch (SQLException e) {
                displayError("Trial license can not be activated");
            } catch (PersonNotExistsException e) {
                displayError("User with this email does not exist");
            }
        } catch (IOException e) {
            displayError("Server is not accessible");
        }
    }

    public boolean isLicenseValueValid(){
        return !licenseValue.getText().isEmpty();
    }

    public void cancel(ActionEvent event) throws IOException {
        changeScene(event, "login-page.fxml");
    }

}