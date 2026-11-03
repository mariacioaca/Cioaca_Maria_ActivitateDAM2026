package com.example.tasks04;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Masina implements Parcelable{
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

    public Masina(Parcel in) {
        marca = in.readString();
        pret = in.readDouble();
        electrica = in.readByte() != 0;
        categorie = in.readString();
        esteNoua = in.readByte() != 0;
    }

    public String getMarca() {
        return marca;
    }

    public double getPret() {
        return pret;
    }

    public boolean isElectrica() {
        return electrica;
    }

    public String getCategorie() {
        return categorie;
    }

    public boolean isEsteNoua() {
        return esteNoua;
    }
    @Override public void writeToParcel(Parcel d, int flags) {
        d.writeString(marca);
        d.writeDouble(pret);
        d.writeByte((byte) (electrica ? 1 : 0));
        d.writeString(categorie);
        d.writeByte((byte) (esteNoua ? 1 : 0));
    }
    @Override public int describeContents() { return 0; }
    public static final Creator<Masina> CREATOR = new Creator<>() {
        public Masina createFromParcel(Parcel in) { return new Masina(in); }
        public Masina[] newArray(int size) { return new Masina[size]; }
    };

    @Override
    public String toString() {
        return "Marca: " + marca +
                "\nPret: " + pret +
                "\nElectrica: " + (electrica ? "Da" : "Nu") +
                "\nCategorie: " + categorie +
                "\nMasina noua: " + (esteNoua ? "Da" : "Nu");
    }

}
