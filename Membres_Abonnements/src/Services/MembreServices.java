/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Membre;
import Utils.DBConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Youssef
 */
public class MembreServices {
    Connection c = DBConnexion.getInstance().getCnx();
    
    
    public void AddMembre(Membre m)throws SQLException
    {
    Statement st;
        try 
        {
           st = c.createStatement();
           String rec = " INSERT INTO `membres`(`User_id`, `User_name`, `User_lastname`, `User_Email`, `User_phone`, `password`, `User_photo`, `User_gender` , `User_Date`) VALUES ('"+m.getUser_id()+"','"+m.getUser_name()+"','"+m.getUser_lastname()+"','"+m.getUser_Email()+"','"+m.getUser_phone()+"','"+m.getPassword()+"','"+m.getUser_photo()+"','"+m.getUser_gender()+"','"+m.getUser_Date()+"')";
           st.executeUpdate(rec);
            System.err.println("add successful");
        } catch (SQLException ex)
        {
            Logger.getLogger(MembreServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    
    public List<Membre> getAll() throws SQLException
    {
    List<Membre> us = new ArrayList<>();
    PreparedStatement p;
    p = c.prepareStatement("select * from membres");
    ResultSet rs = p.executeQuery();
    while (rs.next()){
    Membre m = new Membre(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getDate(9));
    us.add(m);  
    }
    return us;
    }
    
    
    
    public void ReadMembre()
    {
    PreparedStatement pt;
    try 
    {
     pt = c.prepareStatement("select * from membres");
     ResultSet rs = pt.executeQuery();
     while (rs.next()) 
     {
     System.out.println("Membre [  id : " + rs.getInt(1) +   ",  name  " + rs.getString(2) +   ",  lastname " + rs.getString(3) +", email "  +rs.getString(4)+" phone "+rs.getInt(5)+" password "+rs.getString(6)+" photo "+ rs.getString(7)+" gender "+rs.getString(8)+" date "+rs.getDate(9));
     }
    } 
    catch (SQLException ex) 
    {
     Logger.getLogger(MembreServices.class.getName()).log(Level.SEVERE, null, ex);
    }
    } 
    public void UpdateMembre(int id, Membre m) {
        try {
            PreparedStatement pt = c.prepareStatement("UPDATE membres SET User_name=?,User_lastname=?,User_Email=?,User_phone=?,password=?,User_photo=?,User_gender=?,User_Date=? where User_id = ?");

            pt.setString(1, m.getUser_name());
            pt.setString(2, m.getUser_lastname());
            pt.setString(3,m.getUser_Email());
            pt.setInt(4, m.getUser_phone());
            pt.setString(5,m.getPassword());
            pt.setString(6, m.getUser_photo());
            pt.setString(7,m.getUser_gender());
            pt.setDate(8,m.getUser_Date());   
            pt.setInt(9, id);
            pt.executeUpdate();
            System.err.println("update successful");

        } catch (SQLException ex) {
            Logger.getLogger(MembreServices.class.getName()).log(Level.SEVERE, null, ex);
        }      
    }
    
    
    public void DeleteMembre(Membre m) {
        PreparedStatement pt;
        try {
            pt = c.prepareStatement("delete from membres where User_id=? ");
            pt.setInt(1, m.getUser_id());
            pt.executeUpdate();
            System.out.println("delete successful");

        } catch (SQLException ex) {
            Logger.getLogger(MembreServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Membre>  SearchMembre(String user_name,String user_lastname){
        List<Membre> us = new ArrayList<>();
            PreparedStatement pt;
        try {
            pt = c.prepareStatement("select * from membres where User_name ='"+user_name+"' and User_lastname = '"+user_lastname+"'");
            ResultSet rs = pt.executeQuery();
    while (rs.next()){
    Membre m = new Membre(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getDate(9));
    us.add(m);  
    }
         System.out.println(us);

        } catch (SQLException ex) {
            Logger.getLogger(MembreServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    return us;
    }
    
    
    // sort liste membre par alphabet
      public List<Membre>  SortListeMembresParUser_name(){
        List<Membre> us = new ArrayList<>();
            PreparedStatement pt;
        try {
            pt = c.prepareStatement("select * from membres order by User_name ASC");
            ResultSet rs = pt.executeQuery();
    while (rs.next()){
    Membre m = new Membre(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getDate(9));
    us.add(m);  
    }
         System.out.println(us);

        } catch (SQLException ex) {
            Logger.getLogger(MembreServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    return us;
    }
}
    

