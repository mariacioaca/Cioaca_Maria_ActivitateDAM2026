package com.example.tasks04;

import java.io.Serializable;

public class Masina implements Serializable {
    private String marca;
    private double pret;
    private boolean electrica;
    private String categorie;
    private boolean esteNoua;

    public Masina(String marca, double pret, boolean electrica,
                  String categorie, boolean esteNoua) {
        this.marca = marca;
        this.pret = pret;
        this.electrica = electrica;
        this.categorie = categorie;
        this.esteNoua=esteNoua;
    }
    @Override
    public String toString() {
        return "Marca: " + marca +
                "\nPret: " + pret +
                "\nElectrica: " + (electrica ? "Da" : "Nu") +
                "\nCategorie: " + categorie +
                "\nMasina noua: " + (esteNoua ? "Da" : "Nu");
    }
}
