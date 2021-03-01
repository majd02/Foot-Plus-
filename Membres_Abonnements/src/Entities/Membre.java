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
public class Membre {
    private int User_id;
    private String User_name;
    private String User_lastname;
    private String User_Email;
    private int User_phone;
    private String password;
    private String User_photo;
    private String User_gender;
    private Date User_Date;

    public Membre() {
    }

    public Membre(int User_id, String User_name, String User_lastname, String User_Email, int User_phone, String password, String User_photo, String User_gender, Date User_Date) {
        this.User_id = User_id;
        this.User_name = User_name;
        this.User_lastname = User_lastname;
        this.User_Email = User_Email;
        this.User_phone = User_phone;
        this.password = password;
        this.User_photo = User_photo;
        this.User_gender = User_gender;
        this.User_Date = User_Date;
    }

    public Membre(String User_name, String User_lastname, String User_Email, int User_phone, String password, String User_photo, String User_gender, Date User_Date) {
        this.User_name = User_name;
        this.User_lastname = User_lastname;
        this.User_Email = User_Email;
        this.User_phone = User_phone;
        this.password = password;
        this.User_photo = User_photo;
        this.User_gender = User_gender;
        this.User_Date = User_Date;
    }

    public int getUser_id() {
        return User_id;
    }

    public void setUser_id(int User_id) {
        this.User_id = User_id;
    }

    public String getUser_name() {
        return User_name;
    }

    public void setUser_name(String User_name) {
        this.User_name = User_name;
    }

    public String getUser_lastname() {
        return User_lastname;
    }

    public void setUser_lastname(String User_lastname) {
        this.User_lastname = User_lastname;
    }

    public String getUser_Email() {
        return User_Email;
    }

    public void setUser_Email(String User_Email) {
        this.User_Email = User_Email;
    }

    public int getUser_phone() {
        return User_phone;
    }

    public void setUser_phone(int User_phone) {
        this.User_phone = User_phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser_photo() {
        return User_photo;
    }

    public void setUser_photo(String User_photo) {
        this.User_photo = User_photo;
    }

    public String getUser_gender() {
        return User_gender;
    }

    public void setUser_gender(String User_gender) {
        this.User_gender = User_gender;
    }

    public Date getUser_Date() {
        return User_Date;
    }

    public void setUser_Date(Date User_Date) {
        this.User_Date = User_Date;
    }

    @Override
    public String toString() {
        return "Membre{" + "User_id=" + User_id + ", User_name=" + User_name + ", User_lastname=" + User_lastname + ", User_Email=" + User_Email + ", User_phone=" + User_phone + ", password=" + password + ", User_photo=" + User_photo + ", User_gender=" + User_gender + ", User_Date=" + User_Date + '}';
    }
    
    
}
