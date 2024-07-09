package com.example.topsports;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "sportivi")
public class Sportiv {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String parola;
    @ColumnInfo(name = "nume")
    private String nume;

    @ColumnInfo(name = "email")
    private String email;

    @ColumnInfo(name = "sport")
    private String sport;

    @ColumnInfo(name = "varsta")
    private int varsta;

    @ColumnInfo(name = "inaltime")
    private float inaltime;

    @ColumnInfo(name = "greutate")
    private float greutate;

    @ColumnInfo(name = "oras")
    private String oras;

    public Sportiv(String parola,String nume, String email, String sport, int varsta, float inaltime, float greutate, String oras) {
        this.parola = parola;
        this.nume = nume;
        this.email = email;
        this.sport = sport;
        this.varsta = varsta;
        this.inaltime = inaltime;
        this.greutate = greutate;
        this.oras = oras;
    }

    public Sportiv() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public int getVarsta() {
        return varsta;
    }

    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }

    public float getInaltime() {
        return inaltime;
    }

    public void setInaltime(float inaltime) {
        this.inaltime = inaltime;
    }

    public float getGreutate() {
        return greutate;
    }

    public void setGreutate(float greutate) {
        this.greutate = greutate;
    }

    public String getOras() {
        return oras;
    }

    public void setOras(String oras) {
        this.oras = oras;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }
}
