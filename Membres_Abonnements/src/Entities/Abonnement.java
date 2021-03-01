/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author Youssef
 */
public class Abonnement {
  
    private int num_abon;
    private int User_id;
    private int stade_id;
    private Date date_debut;
    private Date date_fin;
    private float prix;
    private String photo;

    public Abonnement(int num_abon, int User_id, int stade_id, Date date_debut, Date date_fin, float prix, String photo) {
        this.num_abon = num_abon;
        this.User_id = User_id;
        this.stade_id = stade_id;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.prix = prix;
        this.photo = photo;
    }

    public Abonnement() {
    }

    public Abonnement(int User_id, int stade_id, Date date_debut, Date date_fin, float prix, String photo) {
        this.User_id = User_id;
        this.stade_id = stade_id;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.prix = prix;
        this.photo = photo;
    }

    public int getNum_abon() {
        return num_abon;
    }

    public void setNum_abon(int num_abon) {
        this.num_abon = num_abon;
    }

    public int getUser_id() {
        return User_id;
    }

    public void setUser_id(int User_id) {
        this.User_id = User_id;
    }

    public int getStade_id() {
        return stade_id;
    }

    public void setStade_id(int stade_id) {
        this.stade_id = stade_id;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Abonnement{" + "num_abon=" + num_abon + ", User_id=" + User_id + ", stade_id=" + stade_id + ", date_debut=" + date_debut + ", date_fin=" + date_fin + ", prix=" + prix + ", photo=" + photo + '}';
    }
    
    
    
    
}
