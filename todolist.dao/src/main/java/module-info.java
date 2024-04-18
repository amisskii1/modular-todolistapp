module todolist.dao {
    requires transitive todolist.utils;
    requires todolist.models;
    requires javafx.base;

    exports com.misskii.todolistapp.dao.api;
    exports com.misskii.todolistapp.dao.exceptions;
    provides com.misskii.todolistapp.dao.api.PersonApi with com.misskii.todolistapp.dao.PersonDao;
    provides com.misskii.todolistapp.dao.api.TaskApi with com.misskii.todolistapp.dao.TaskDao;
    provides com.misskii.todolistapp.dao.api.LicenseApi with com.misskii.todolistapp.dao.LicenseDao;
}