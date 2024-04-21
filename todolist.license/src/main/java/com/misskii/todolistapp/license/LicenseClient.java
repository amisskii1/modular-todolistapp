package com.misskii.todolistapp.license;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.misskii.todolistapp.license.api.LicenseClientApi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LicenseClient implements LicenseClientApi {

    @Override
    public String createTrialLicense(String userEmail) throws JsonProcessingException {
        Map<String, String> jsonData = new HashMap<>();
        jsonData.put("userEmail", userEmail);

        try {
            URL url = new URL("https://localhost:8443/api/trial");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            ObjectMapper mapper = new ObjectMapper();
            String jsonInputString = mapper.writeValueAsString(jsonData);
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();
            StringBuilder response = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
            }

            if (responseCode == HttpURLConnection.HTTP_OK) {
                JsonNode jsonNode = mapper.readTree(response.toString());
                return jsonNode.get("licenseValue").asText();
            } else if (responseCode == HttpURLConnection.HTTP_FORBIDDEN) {
                JsonNode jsonNode = mapper.readTree(response.toString());
                return jsonNode.get("errorMessage").asText();
            } else {
                // Handle other HTTP response codes here
                throw new RuntimeException("Failed to create trial license. Response code: " + responseCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to create trial license", e);
        }
    }

    @Override
    public List<String> validateLicenseKey(String userEmail, String licenseKey) throws JsonProcessingException {
        Map<String, String> jsonData = new HashMap<>();
        jsonData.put("userEmail", userEmail);
        jsonData.put("licenseValue", licenseKey);

        try {
            URL url = new URL("https://localhost:8443/api/validate");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            ObjectMapper mapper = new ObjectMapper();
            String jsonInputString = mapper.writeValueAsString(jsonData);
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();
            StringBuilder response = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
            }

            if (responseCode == HttpURLConnection.HTTP_OK) {
                JsonNode jsonNode = mapper.readTree(response.toString());
                List<String> jsonResult = new ArrayList<>();
                jsonResult.add(jsonNode.get("licenseStatus").asText());
                jsonResult.add(jsonNode.get("expiredDate").asText());
                return jsonResult;
            } else {
                String responseBody = response.toString();
                JsonNode jsonNode = mapper.readTree(responseBody);
                throw new RuntimeException(jsonNode.get("errorMessage").asText());
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to validate license key", e);
        }
    }
}
