package com.misskii.todolistapp.dao.api;

import com.misskii.todolistapp.entities.Task;
import javafx.collections.ObservableList;

import java.sql.Date;

public interface TaskApi {
    void createNewTask(Task task);
    void updateTaskByID(int personId, String id, String description, String title, Date date, String status, String priority);
    ObservableList<Task> selectAllTasksByPersonId(int id);
    Task getTaskByID(String id);
    String selectPriorityByTaskID(int id);

}
