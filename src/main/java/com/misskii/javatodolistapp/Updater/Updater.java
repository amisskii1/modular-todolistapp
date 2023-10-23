package com.misskii.javatodolistapp.Updater;

import java.io.*;
import java.util.Objects;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Updater {
    private boolean isWindows;
    private String scriptPath = "src/main/resources/scripts/listPackageVersionsScript.cmd";
    private String outputFilePath = "versions.json";
    private String actualVersion = "application-version";

    private String appVersion;
    private ProcessBuilder processBuilder;
    private Process process;

    public boolean defineOperationSystem(){
        isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");
        return isWindows;
    }

    public void getListOfPackageVersions() {
        if (defineOperationSystem()){
            File file = new File(scriptPath);
            try {
                processBuilder = new ProcessBuilder("cmd.exe", "/c", file.getAbsolutePath());
                process = processBuilder.start();
                process.waitFor();
            }catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public String extractLatestVersionFromJsonFile() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            File versionsFile = new File(outputFilePath);
            getListOfPackageVersions();
            if (versionsFile.exists()){
                JsonNode allVersions = objectMapper.readTree(versionsFile);
                JsonNode lastVersion = allVersions.get(0);
                String version = lastVersion.get("name").asText();
                return version;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void extractActualVersionFromFile() {
        try {
            FileReader fileReader = new FileReader(actualVersion);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String actualVersion;
            actualVersion = bufferedReader.readLine();
            appVersion = actualVersion;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean compareActualVersionWithPackageLatestVersion(){
        String latestVersion = extractLatestVersionFromJsonFile();
        if (Objects.equals(latestVersion, appVersion)){
            return true;
        }
        return false;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }
}
