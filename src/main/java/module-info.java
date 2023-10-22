module com.misskii.javatodolistapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.postgresql.jdbc;
    requires com.fasterxml.jackson.databind;


    opens com.misskii.javatodolistapp to javafx.fxml;
    opens com.misskii.javatodolistapp.Models to javafx.base;
    exports com.misskii.javatodolistapp;
    exports com.misskii.javatodolistapp.Controllers;
    opens com.misskii.javatodolistapp.Controllers to javafx.fxml;
}