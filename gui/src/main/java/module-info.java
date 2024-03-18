module com.misskii.todolistapp.controllers{
    requires database.entity;
    requires javafx.fxml;
    requires javafx.controls;
    requires com.misskii.todolistapp.updater;
    requires database.dao;

    opens com.misskii.todolistapp.gui.controllers to javafx.fxml;
    exports com.misskii.todolistapp.gui.start to javafx.graphics;

    uses com.misskii.todolistapp.updater.api.UpdaterApi;
    uses com.misskii.todolistapp.dao.api.TaskApi;
    uses com.misskii.todolistapp.dao.api.PersonApi;
}