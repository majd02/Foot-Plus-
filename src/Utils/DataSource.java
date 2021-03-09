/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;
import models.Reservation;
import models.Stade;

/**
 *
 * @author asus
 */

public class DataSource {
    private static DataSource instance ;
    private Connection cnx ;
    
    private final String URL ="jdbc:mysql://localhost:3307/pidev";
    private final String LOGIN ="root";
    private final String PASSWORD ="";
    
    private DataSource(){
    try {
        cnx =DriverManager.getConnection(URL, LOGIN, PASSWORD);
        System.out.println("Connecting !");
        
    
    }catch(SQLException ex){
        System.err.println(ex.getMessage());
    }
}
    
    public static DataSource getInstance(){
     if (instance == null){
        instance = new DataSource();
    }
    return instance ;
    
    }
    public Connection getCnx(){
    return cnx ;
    }
    Connection conn = null;
    public static Connection ConnectDb(){
        try {
            
            Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3307/pidev","root","");
           // JOptionPane.showMessageDialog(null, "Connection Established");
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
     public static ObservableList<Stade> getDataStade(){
        Connection conn = ConnectDb();
        ObservableList<Stade> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps;
            ps = conn.prepareStatement("select * from stade");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                list.add(new Stade(rs.getInt("id_stade") ,rs.getString("nom"), rs.getString("adresse"), Integer.parseInt(rs.getString("prixph")), rs.getString("contact"), rs.getString("image")));               
            }
        } catch (Exception e) {
        }
        return list;
    }
      public static ObservableList<Stade> getDataSearchStade(){
        Connection conn = ConnectDb();
        ObservableList<Stade> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from stade where nom=?");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                list.add(new Stade(rs.getString("nom"), rs.getString("adresse"), Integer.parseInt(rs.getString("prixph")), rs.getString("contact"), rs.getString("image")));               
            }
        } catch (Exception e) {
        }
        return list;
    }
     
          public static ObservableList<Reservation> getDataReservation(){
        Connection conn = ConnectDb();
        ObservableList<Reservation> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from reservation");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                list.add(new Reservation(rs.getInt(1),rs.getInt(2),rs.getInt(3)));              
            }
        } catch (Exception e) {
        }
        return list;
    }
    }
   


