module database.dao{
    requires transitive database.util;
    requires database.entity;
    requires javafx.base;

    exports com.misskii.todolistapp.dao.api;
    provides com.misskii.todolistapp.dao.api.PersonApi with com.misskii.todolistapp.dao.PersonDao;
    provides com.misskii.todolistapp.dao.api.TaskApi with com.misskii.todolistapp.dao.TaskDao;
}