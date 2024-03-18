package com.misskii.todolistapp.dao.api;

import com.misskii.todolistapp.entities.Task;
import javafx.collections.ObservableList;

public interface TaskApi {
    void createNewTask(Task task);
    void updateTaskByID(int personId,String id, String description, String title, String dueto, String status);
    ObservableList<Task> selectAllTasksByPersonId(int id);
    Task getTaskByID(String id);

}
