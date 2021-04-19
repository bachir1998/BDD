package com.example.bdd;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Planete {
    @PrimaryKey
    public int uid;

    @ColumnInfo(name = "name")
    private String nom;

    @ColumnInfo(name = "size")
    private String taille;

    @ColumnInfo(name = "image")
    private int image;



    public Planete(int uid, String nom, String taille, int image) {
        this.uid = uid;
        this.nom = nom;
        this.taille = taille;
        this.image = image;
    }

    public int getImage() {
        return image;
    }

    public int getUid() {
        return uid;
    }

    public String getNom() {
        return nom;
    }

    public String getTaille() {
        return taille;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setTaille(String taille) {
        this.taille = taille;
    }

    public void setImage(int image) {
        this.image = image;
    }
}

/**
 *
 * public Planete(int uid, String nom, String taille){
 *         this.uid = uid;
 *         this.nom = nom;
 *         this.taille = taille;
 *     }
 */
