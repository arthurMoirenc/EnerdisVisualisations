package com.example;

public class Donnee {
    private int id;
    private String date;
    private float valeur;
    private String unite;

    public Donnee(int id, String date, float valeur, String unite) {
        this.id = id;
        this.date = date;
        this.valeur = valeur;
        this.unite = unite;
    }

    public Donnee(String date, float valeur, String unite) {
        this.date = date;
        this.valeur = valeur;
        this.unite = unite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getValeur() {
        return valeur;
    }

    public void setValeur(float valeur) {
        this.valeur = valeur;
    }

    public String getUnite() {
        return unite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }
}
