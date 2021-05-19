/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myap.entities;



/**
 *
 * @author USER
 */
 public class Feedback {
    
    
    private int id ;
    private String description;
    private int rate ;
    private String date;
    private String User;  

    public Feedback(int id, String description, int rate, String date, String User) {
        this.id = id;
        this.description = description;
        this.rate = rate;
        this.date = date;
        this.User = User;
    }

    public Feedback(String description, int rate, String date, String User) {
        this.description = description;
        this.rate = rate;
        this.date = date;
        this.User = User;
    }
     public Feedback(String description, int rate, String User) {
        this.description = description;
        this.rate = rate;
        this.User = User;
    }
      public Feedback(String description,  String User) {
        this.description = description;
        this.User = User;
    }

    public Feedback(int id, String description, int rate, String date) {
        this.id = id;
        this.description = description;
        this.rate = rate;
        this.date = date;
    }

    public Feedback() {
           }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Feedback{" + "id=" + id + ", description=" + description + ", rate=" + rate + ", date=" + date + ", User=" + User + '}';
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String User) {
        this.User = User;
    }
    
}

