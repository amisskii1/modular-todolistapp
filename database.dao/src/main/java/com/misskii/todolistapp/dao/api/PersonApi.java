package com.misskii.todolistapp.dao.api;

import com.misskii.todolistapp.entities.Person;

import java.util.List;

public interface PersonApi {
    void createNewPerson(Person person);
    List<Person> getAllPeople();
}
