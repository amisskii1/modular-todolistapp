package com.misskii.todolistapp.updater;

import java.io.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.misskii.todolistapp.updater.api.UpdaterApi;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class Updater implements UpdaterApi {
    private static final String ACTUAL_VERSION = "1.0.4";
    private final String gitToken = System.getenv("packages_token");
    private String latestVersion;
   public boolean compareVersions(){
       try {
           String apiUrl = "https://api.github.com/users/AntonMisskii/packages/maven/com.misskii.javatodolistapp/versions";
           HttpClient httpClient = HttpClients.createDefault();
           HttpGet httpGet = new HttpGet(apiUrl);
           httpGet.addHeader("Accept", "application/vnd.github+json");
           httpGet.addHeader("Authorization", "Bearer "+ gitToken);
           httpGet.addHeader("X-GitHub-Api-Version", "2022-11-28");
           HttpResponse response = httpClient.execute(httpGet);

           String responseBody = EntityUtils.toString(response.getEntity());
           ObjectMapper objectMapper = new ObjectMapper();
           JsonNode jsonNode = objectMapper.readTree(responseBody);
           String latestVersion = jsonNode.get(0).get("name").asText();

           setLatestVersion(latestVersion);

           if (ACTUAL_VERSION.equals(latestVersion)) {
               return true;
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
