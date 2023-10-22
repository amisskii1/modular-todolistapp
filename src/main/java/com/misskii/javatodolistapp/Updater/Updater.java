package com.misskii.javatodolistapp.Updater;

import java.io.*;
import java.util.Objects;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Updater {
    private boolean isWindows;
    private String scriptPath = "src/main/resources/scripts/listPackageVersionsScript.cmd";
    private String outputFilePath = "src/main/resources/versions.json";
    private String actualVersion = "src/main/resources/application-version";
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
        getListOfPackageVersions();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            File versionsFile = new File("versions.json");
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

    public String extractActualVersionFromFile() {
        try {
            FileReader fileReader = new FileReader(actualVersion);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String actualVersion;
            actualVersion = bufferedReader.readLine();
            return actualVersion;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean compareActualVersionWithPackageLatestVersion(){
        String latestVersion = extractLatestVersionFromJsonFile();
        String actualVersion = extractActualVersionFromFile();
        if (Objects.equals(latestVersion, actualVersion)){
            return true;
        }
        return false;
    }
}
