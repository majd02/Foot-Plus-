/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Suptech.Entite;

/**
 */
public class produit {
    private int id;
    private String nom;
    private double prix;
    private String desc ;
    private String image; 

    public produit(int id, String nom, double prix, String desc , String image) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.desc = desc;
        this.image =image;
    }

    public produit(String nom, double prix, String desc, String image) {
        this.nom = nom;
        this.prix = prix;
        this.desc = desc;
        this.image=image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
     public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "produit{" + "id=" + id + ", nom=" + nom + ", prix=" + prix + ", desc=" + desc + ", image=" + image +'}';
    }
    
    
    
    
    
}
