package com.example.sqliteexampleapp.models;

public class Annonce extends DataInfo {
    private Person personne;

    private String title;

    private int price;

    private String description;

    private String datePublication;

    private String dateFinPublication;

    public Annonce(Person person, String title, int price, String description, String datePublication,
                   String dateFinPublication) {
        this.personne = person;
        this.title = title;
        this.price = price;
        this.description = description;
        this.datePublication = datePublication;
        this.dateFinPublication = dateFinPublication;
    }

    public Person getPersonne() {
        return personne;
    }

    public void setPersonne(Person personne) {
        this.personne = personne;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(String datePublication) {
        this.datePublication = datePublication;
    }

    public String getDateFinPublication() {
        return dateFinPublication;
    }

    public void setDateFinPublication(String dateFinPublication) {
        this.dateFinPublication = dateFinPublication;
    }
}
