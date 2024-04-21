package com.misskii.todolistapp.updater;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.misskii.todolistapp.updater.api.UpdaterApi;

public class Updater implements UpdaterApi {
    private static final String ACTUAL_VERSION = "1.1.2";
    private final String gitToken = System.getenv("packages_token");
    private String latestVersion;
    public boolean compareVersions(){
        try {
            String apiUrl = "https://api.github.com/users/amisskii1/packages/maven/com.misskii.javatodolistapp/versions";
            URL url = new URL(apiUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/vnd.github+json");
            con.setRequestProperty("Authorization", "Bearer " + gitToken);
            con.setRequestProperty("X-GitHub-Api-Version", "2022-11-28");

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(response.toString());
                String latestVersion = jsonNode.get(0).get("name").asText();

                setLatestVersion(latestVersion);

                return ACTUAL_VERSION.equals(latestVersion);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getActualVersion() {
        return ACTUAL_VERSION;
    }

    public String getLatestVersion() {
        return latestVersion;
    }

    public void setLatestVersion(String latestVersion) {
        this.latestVersion = latestVersion;
    }
}