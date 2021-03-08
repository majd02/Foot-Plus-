/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.util;
import com.esprit.models.Reclamation;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;
/**
 *
 * @author USER
 */
public class DataSource {
    private Connection cnx ;
    
    private static DataSource instance;
    
    private final String url= "jdbc:mysql://127.0.0.1/test";
    
    private final String username="root";
    
    private final String password="";
     public DataSource() {
        try {
            cnx=DriverManager.getConnection(url, username, password);
            System.out.println("Connecting !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public static DataSource getInstance() {
        if (instance == null)
            instance = new DataSource();
        return instance;
    }
    
    public Connection getCnx() {
        return cnx;
    }
    
    Connection conn = null;
    public static Connection ConnectDb(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1/test","root","");
           // JOptionPane.showMessageDialog(null, "Connection Established");
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
     public static ObservableList<Reclamation> getDataReclamation(){
        Connection conn = ConnectDb();
        ObservableList<Reclamation> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from reclamation");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                list.add(new Reclamation( rs.getInt("id"),rs.getString("type"), rs.getString("ido"), rs.getString("sujet"), rs.getString("description"), rs.getString("date"), rs.getString("etat") ,rs.getString("idU")));               
            }
        } catch (Exception e) {
        }
        return list;
    }
}
