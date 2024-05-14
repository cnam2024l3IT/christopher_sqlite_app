package com.example.sqliteexampleapp.models;

import java.util.Set;

public class Person extends DataInfo {
    private final String lastName;

    private final String firstName;

    public Person(String lastName, String firstName) {
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

}
