package com.example.sqliteexampleapp.models;

import java.util.Set;

public class Personne extends DataInfo {
    private String nom;

    private String prenom;

    private Set<Annonce> annonces;

    public Personne() {}

    public Personne(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Set<Annonce> getAnnonces() {
        return annonces;
    }

    public void setAnnonces(Set<Annonce> annonces) {
        this.annonces = annonces;
    }

    public void addAnnonce(Annonce annonce) {
        annonces.add(annonce);
        annonce.setPersonne(this);
    }
}
