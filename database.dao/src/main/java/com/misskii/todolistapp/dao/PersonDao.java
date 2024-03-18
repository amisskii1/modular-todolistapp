package com.misskii.todolistapp.dao;

import com.misskii.todolistapp.dao.api.PersonApi;
import com.misskii.todolistapp.entities.Person;
import com.misskii.todolistapp.updater.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDao implements PersonApi {
    private final Connection connection = DBUtil.getConnection();

    public void createNewPerson(Person person){
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO PERSON (personName, personEmail, personPassword) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, person.getName());
            preparedStatement.setString(2, person.getEmail());
            preparedStatement.setString(3, person.getPassword());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()){
                int id = resultSet.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Person> getAllPeople(){
        List<Person> people = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM Person";
            ResultSet resultSet = statement.executeQuery(SQL);
            while(resultSet.next()){
                Person person = new Person();
                person.setId(resultSet.getInt("personId"));
                person.setName(resultSet.getString("personName"));
                person.setEmail(resultSet.getString("personEmail"));
                person.setPassword(resultSet.getString("personPassword"));
                people.add(person);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return people;
    }
}