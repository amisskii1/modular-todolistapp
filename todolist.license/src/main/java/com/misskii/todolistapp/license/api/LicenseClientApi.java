package com.misskii.todolistapp.license.api;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.net.ConnectException;
import java.util.List;

public interface LicenseClientApi {
    String createTrialLicense(String userEmail) throws IOException;
    List<String> validateLicenseKey(String userEmail, String licenseKey) throws ConnectException, JsonProcessingException;
}
