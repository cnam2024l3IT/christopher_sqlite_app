package com.example.sqliteexampleapp.models;

import java.util.Set;

public class Person extends DataInfo {
    private String lastName;

    private String firstName;

    public Person(String lastName, String firstName) {
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public Person(long _id, String lastName, String firstName, String dateCreation, String dateModification) {
        this._id = _id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.dateCreation = dateCreation;
        this.dateModification = dateModification;
    }

    public Person(long _id) {
        this._id = _id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

}
