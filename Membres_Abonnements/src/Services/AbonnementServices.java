/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Abonnement;
import Utils.DBConnexion;
import java.sql.Connection;
import java.sql.Date;
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
public class AbonnementServices {
    Connection c = DBConnexion.getInstance().getCnx();

    public void AddAbonnement(Abonnement a)throws SQLException
    {
    Statement st;
        try 
        {
         st = c.createStatement();
           String rec = " INSERT INTO `abonnements`(`num_abon`, `User_id`, `stade_id`, `date_debut`, `date_fin`, `prix`, `photo`) VALUES ('"+a.getNum_abon()+"','"+a.getUser_id()+"','"+a.getStade_id()+"','"+a.getDate_debut()+"','"+a.getDate_fin()+"','"+a.getPrix()+"','"+a.getPhoto()+"')";
           st.executeUpdate(rec);
            System.err.println("add successful");
        } catch (SQLException ex)
        {
            Logger.getLogger(AbonnementServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    
    public List<Abonnement> getAll() throws SQLException
    {
    List<Abonnement> us = new ArrayList<>();
    PreparedStatement p;
    p = c.prepareStatement("select * from abonnements");
    ResultSet rs = p.executeQuery();
    while (rs.next()){
    Abonnement a = new Abonnement(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getDate(4),rs.getDate(5),rs.getFloat(6),rs.getString(7));
    us.add(a);  
    }
    return us;
    }
    
    
    
    public void ReadAbonnement()
    {
    PreparedStatement pt;
    try 
    {
     pt = c.prepareStatement("select * from abonnements");
    
     ResultSet rs = pt.executeQuery();
     while (rs.next()) 
     {
     System.out.println("Abonnement [  num_abon : " + rs.getInt(1) +   ",  User_id  " + rs.getInt(2) +   ",  stade_id " + rs.getInt(3) +", date_debut "  +rs.getDate(4)+" date_fin "+rs.getDate(5)+" prix "+rs.getFloat(6)+" photo "+ rs.getString(7));
     }
    } 
    catch (SQLException ex) 
    {
     Logger.getLogger(AbonnementServices.class.getName()).log(Level.SEVERE, null, ex);
    }
    } 
    public void UpdateAbonnement(int id, Abonnement a) {
     
        try {
            PreparedStatement pt = c.prepareStatement("UPDATE abonnements SET num_abon=?,User_id=?,stade_id=?,date_debut=?,date_fin=?,prix=?,photo=? where num_abon = ?");

            pt.setInt(1,a.getNum_abon());
            pt.setInt(2,a.getUser_id());
            pt.setInt(3, a.getStade_id());
            pt.setDate(4,a.getDate_debut());
            pt.setDate(5, a.getDate_fin());
            pt.setFloat(6, a.getPrix());
            pt.setString(7, a.getPhoto());
            pt.setInt(8, id);
            
            
            
            
           
          
            pt.executeUpdate();
            System.err.println("update successful");

        } catch (SQLException ex) {
            Logger.getLogger(AbonnementServices.class.getName()).log(Level.SEVERE, null, ex);
        }      
    }
    
    
    public void DeleteAbonnement(Abonnement a) {
        PreparedStatement pt;
        try {
            pt = c.prepareStatement("delete from abonnements where num_abon=? ");
            pt.setInt(1, a.getNum_abon());
            pt.executeUpdate();
            System.out.println("delete successful");

        } catch (SQLException ex) {
            Logger.getLogger(AbonnementServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Abonnement>  SearchAbonnement(int num_abon){
        List<Abonnement> us = new ArrayList<>();
            PreparedStatement pt;
        try {
            pt = c.prepareStatement("select * from abonnements where(num_abon ="+num_abon+") ");
            ResultSet rs = pt.executeQuery();
    while (rs.next()){
    Abonnement a = new Abonnement(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getDate(4),rs.getDate(5),rs.getFloat(6),rs.getString(7));
    us.add(a);  
    }
         System.out.println(us);

        } catch (SQLException ex) {
            Logger.getLogger(AbonnementServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    return us;
    }
    
    
    // sort liste abonnement par alphabet
      public List<Abonnement>  SortListeAbonnementsParPrix(){
        List<Abonnement> us = new ArrayList<>();
            PreparedStatement pt;
        try {
            pt = c.prepareStatement("select * from abonnements order by prix ASC");
            ResultSet rs = pt.executeQuery();
    while (rs.next()){
    Abonnement a = new Abonnement(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getDate(4),rs.getDate(5),rs.getFloat(6),rs.getString(7));
    us.add(a);  
    }
         System.out.println(us);

        } catch (SQLException ex) {
            Logger.getLogger(AbonnementServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    return us;
    }
}
    

