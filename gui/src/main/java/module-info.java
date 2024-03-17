module com.misskii.todolistapp.controllers{
    requires database.entity;
    requires database.dao;
    requires com.misskii.todolistapp.updater;

    requires javafx.fxml;
    requires javafx.controls;

    exports com.misskii.todolistapp.gui.start;
    exports com.misskii.todolistapp.gui.controllers;
    opens com.misskii.todolistapp.gui.controllers to javafx.fxml;
}