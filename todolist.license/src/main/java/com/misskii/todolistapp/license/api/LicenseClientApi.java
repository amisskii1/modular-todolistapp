package com.misskii.todolistapp.license.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;

public interface LicenseClientApi {
    String createTrialLicense(String userEmail) throws JsonProcessingException;
    List<String> validateLicenseKey(String userEmail, String licenseKey) throws JsonProcessingException, ResourceAccessException;
}
