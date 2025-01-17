package com.misskii.todolistapp.entities;

public class Person {
    private Integer id;

    private String name;

    private String password;

    private String email;
    public Person(String name,String email, String password) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public Person() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}