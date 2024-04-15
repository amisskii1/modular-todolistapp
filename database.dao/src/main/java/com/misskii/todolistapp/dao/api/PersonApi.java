package com.misskii.todolistapp.dao.api;

import com.misskii.todolistapp.dao.exceptions.PersonNotExistsException;
import com.misskii.todolistapp.entities.Person;

import java.sql.SQLException;
import java.util.List;

public interface PersonApi {
    void createNewPerson(Person person);
    List<Person> getAllPeople();
    int getPersonIdByEmail(String email) throws SQLException, PersonNotExistsException;
}
