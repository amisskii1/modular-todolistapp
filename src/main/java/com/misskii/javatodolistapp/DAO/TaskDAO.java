package com.misskii.javatodolistapp.DAO;

import com.misskii.javatodolistapp.Models.Task;
import com.misskii.javatodolistapp.Util.DBUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDAO {
    private final Connection connection = DBUtil.getConnection();

    public void createNewTask(Task task){
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO Task (personId, tasktitle, taskDescription, dueTo, status) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, task.getPersonId());
            preparedStatement.setString(2, task.getTaskTitle());
            preparedStatement.setString(3, task.getTaskDescription());
            preparedStatement.setString(4, task.getDueTo());
            preparedStatement.setString(5, task.getStatus());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()){
                int id = resultSet.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateTaskByID(int personId,String id, String description, String title, String dueto, String status){
        try {
            int taskId = Integer.parseInt(id);
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE TASK SET taskdescription=?, tasktitle=?, dueto=?, status=? WHERE TASK.taskid=?" +
                            "AND TASK.personid=?");
            preparedStatement.setString(1, description);
            preparedStatement.setString(2, title);
            preparedStatement.setString(3, dueto);
            preparedStatement.setString(4, status);
            preparedStatement.setInt(5, taskId);
            preparedStatement.setInt(6, personId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<Task> selectAllTasksByPersonId(int id){
        ObservableList<Task> observableList = FXCollections.observableArrayList();
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT taskid, tasktitle, taskdescription, dueto, status from TASK JOIN PERSON ON TASK.personid=PERSON.personid WHERE TASK.personid=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Task task = new Task();
                task.setTaskId(resultSet.getInt("taskid"));
                task.setTaskTitle(resultSet.getString("tasktitle"));
                task.setTaskDescription(resultSet.getString("taskdescription"));
                task.setDueTo(resultSet.getString("dueto"));
                task.setStatus(resultSet.getString("status"));
                observableList.add(task);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return observableList;
    }
}
