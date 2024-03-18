package com.misskii.todolistapp.updater.api;

public interface UpdaterApi {
    boolean compareVersions();
    String getActualVersion();
    String getLatestVersion();
}
