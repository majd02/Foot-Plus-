/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Suptech.Utils;

import com.Suptech.Entite.produit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;

/**
 */
public class DataBase {
    String url = "jdbc:mysql://127.0.0.1:3306/esprit";
     String login = "root";
     String pwd = "";
    public  static DataBase db;
    public Connection con;
    private DataBase() {
         try {
             con=DriverManager.getConnection(url, login, pwd);
             System.out.println("connexion etablie");
         } catch (SQLException ex) {
             System.out.println(ex);
         }
    }
    
    public Connection  getConnection()
    {
    return con;
    }     
    public static DataBase getInstance()
    {if(db==null)
        db=new DataBase();
    return db;
    }   
       public static Connection ConnectDb(){
        try {
            
            Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/esprit","root","");
           // JOptionPane.showMessageDialog(null, "Connection Established");
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
        
    
     public static ObservableList<produit> getDataProduit(){
        Connection conn = ConnectDb();
        ObservableList<produit> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps;
            ps = conn.prepareStatement("select * from produit");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                list.add(new produit(rs.getInt("id") ,rs.getString("nom"),  Double.parseDouble(rs.getString("prix")), rs.getString("desc"), rs.getString("image")));               
            }
        } catch (Exception e) {
        }
        return list;
    }
       
    
}
