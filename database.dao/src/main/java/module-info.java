module database.dao{
    exports com.misskii.todolistapp.dao.api;
    requires database.util;
    requires database.entity;

    requires javafx.base;
    provides com.misskii.todolistapp.dao.api.PersonApi with com.misskii.todolistapp.dao.PersonDao;
    provides com.misskii.todolistapp.dao.api.TaskApi with com.misskii.todolistapp.dao.TaskDao;
}