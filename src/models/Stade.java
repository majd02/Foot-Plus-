/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Blob;

/**
 *
 * @author asus
 */
public class Stade {
    private int id_stade ;
    private String nom;
    private String adresse ;
    private int prixph;
    private String contact ;
    private String image;

    public Stade(int id_stade, String nom, String adresse, int prixph, String contact, String image) {
        this.id_stade = id_stade;
        this.nom = nom;
        this.adresse = adresse;
        this.prixph = prixph;
        this.contact = contact;
        this.image = image;
    }

    public Stade(String nom, String adresse, int prixph, String contact, String image) {
        this.nom = nom;
        this.adresse = adresse;
        this.prixph = prixph;
        this.contact = contact;
        this.image = image;
    }

    public Stade() {
        
    }

    public int getId_stade() {
        return id_stade;
    }

    public void setId_stade(int id_stade) {
        this.id_stade = id_stade;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getPrixph() {
        return prixph;
    }

    public void setPrixph(int prixph) {
        this.prixph = prixph;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Stade{" + "id_stade=" + id_stade + ", nom=" + nom + ", adresse=" + adresse + ", prixph=" + prixph + ", contact=" + contact + ", image=" + image + '}';
    }


   
    
    
    
}
