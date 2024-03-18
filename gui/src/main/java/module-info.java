module com.misskii.todolistapp.controllers{
    uses com.misskii.todolistapp.dao.api.TaskApi;
    uses com.misskii.todolistapp.dao.api.PersonApi;
    requires database.entity;
    requires database.dao;

    requires javafx.fxml;
    requires javafx.controls;
    requires com.misskii.todolistapp.updater;

    exports com.misskii.todolistapp.gui.start;
    exports com.misskii.todolistapp.gui.controllers;
    opens com.misskii.todolistapp.gui.controllers to javafx.fxml;
}