module todolist.gui{
    requires todolist.models;
    requires javafx.fxml;
    requires javafx.controls;
    requires todolist.updater;
    requires todolist.dao;
    requires todolist.license;

    opens com.misskii.todolistapp.gui.controllers to javafx.fxml;
    exports com.misskii.todolistapp.gui.start to javafx.graphics;

    uses com.misskii.todolistapp.updater.api.UpdaterApi;
    uses com.misskii.todolistapp.dao.api.TaskApi;
    uses com.misskii.todolistapp.dao.api.PersonApi;
    uses com.misskii.todolistapp.dao.api.LicenseApi;
    uses com.misskii.todolistapp.license.api.LicenseClientApi;
}