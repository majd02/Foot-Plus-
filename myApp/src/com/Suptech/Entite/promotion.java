/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Suptech.Entite;

import java.sql.Date;

/**
 *
 * @author moham
 */
public class promotion {
     private int id_promo;
    private String nom_promo;
    private Date debut_date;
    private Date fin_date;
    private String desc_promo ;
    private double pourcentage ; 
    
    
public promotion (int id_promo, String nom_promo, Date debut_promo, Date fin_promo , String desc_promo, double pourcentage) {
        this.id_promo = id_promo;
        this.nom_promo = nom_promo;
        this.debut_date = debut_promo;
        this.fin_date = fin_promo;
        this.desc_promo =desc_promo;
        this.pourcentage =pourcentage;


    }

    public promotion(String nom_promo, Date debut_promo, Date fin_promo, String desc_promo, double pourcentage) {
        this.nom_promo = nom_promo;
        this.debut_date = debut_date;
        this.fin_date = fin_date;
        this.desc_promo=desc_promo;
        this.pourcentage=pourcentage;
       }

    public int getId_promo() {
        return id_promo;
    }

    public void setId_promo(int id_promo) {
        this.id_promo = id_promo;
    }

    public String getNom_promo() {
        return nom_promo;
    }

    public void setNom_promo(String nom_promo) {
        this.nom_promo = nom_promo;
    }

    public Date getDebut_date() {
        return debut_date;
    }

    public void setDebut_date(Date debut_date) {
        this.debut_date = debut_date;
    }

    public Date getFin_date() {
        return fin_date;
    }

    public void setFin_date(Date fin_date) {
        this.fin_date = fin_date;
    }

    public String getDesc_promo() {
        return desc_promo;
    }

    public void setDesc_promo(String desc_promo) {
        this.desc_promo = desc_promo;
    }

    public double getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(double pourcentage) {
        this.pourcentage = pourcentage;
    }

    @Override
    public String toString() {
        return "promotion{" + "id_promo=" + id_promo + ", nom_promo=" + nom_promo + ", debut_date=" + debut_date + ", fin_date=" + fin_date + ", desc_promo=" + desc_promo + ", pourcentage=" + pourcentage + '}';
    }
    
    
    
    }
    
    
    

