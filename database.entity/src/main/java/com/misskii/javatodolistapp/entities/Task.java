package com.misskii.javatodolistapp.entities;

public class Task {
    private String taskTitle;
    private String taskDescription;
    private String dueTo;
    private int personId;
    private int taskId;

    private String status;

    public Task() {
    }

    public Task(String taskTitle, String taskDescription, String dueTo, int personId, String status){
        this.status = status;
        this.taskTitle = taskTitle;
        this.taskDescription = taskDescription;
        this.dueTo = dueTo;
        this.personId = personId;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getDueTo() {
        return dueTo;
    }

    public void setDueTo(String dueTo) {
        this.dueTo = dueTo;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
