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
public class sub { 
    private int num_sub;
    private int user_id;
    private String stade_id;
    private Date date_debut;
    private Date date_fin;
    private float prix;
    private String photo;

    public sub() {
    }

    public sub(int num_sub, int user_id, String stade_id, Date date_debut, Date date_fin, float prix, String photo) {
        this.num_sub = num_sub;
        this.user_id = user_id;
        this.stade_id= stade_id;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.prix = prix;
        this.photo = photo;
    }

    public sub(int user_id, String stade_id, Date date_debut, Date date_fin, float prix, String photo) {
        this.user_id = user_id;
        this.stade_id = stade_id;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.prix = prix;
        this.photo = photo;
    }

    public sub(Date date_debut, Date date_fin, float prix, String photo) {
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.prix = prix;
        this.photo = photo;
    }

    public int getNum_sub() {
        return num_sub;
    }

    public void setNum_sub(int num_sub) {
        this.num_sub = num_sub;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getStade_id() {
        return stade_id;
    }

    public void setStade_id(String stade_id) {
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
        return "sub{" + "num_sub=" + num_sub + ", user_id=" + user_id + ", stade_id=" + stade_id + ", date_debut=" + date_debut + ", date_fin=" + date_fin + ", prix=" + prix + ", photo=" + photo + '}';
    }
    
    
}
