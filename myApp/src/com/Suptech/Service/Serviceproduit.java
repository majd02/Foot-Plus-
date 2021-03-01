package com.Suptech.Service;


import com.Suptech.Entite.produit;
import com.Suptech.Entite.produit;
import com.Suptech.Utils.DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.Suptech.IService.IServiceproduit;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *

 */
public class Serviceproduit implements IServiceproduit<produit> {
    
     private Connection con;
    private Statement ste;

    public Serviceproduit() {
        con = DataBase.getInstance().getConnection();

    }

    @Override
    public void ajouter(produit p) throws SQLException {
        
        PreparedStatement pre=con.prepareStatement("INSERT INTO `produit` (`nom`,`prix`,`desc`,`image`) VALUES ( ?, ?, ?, ?)");
    pre.setString(1, p.getNom()); 
    pre.setDouble(2, p.getPrix());
    pre.setString(3, p.getDesc());
    pre.setString(4, p.getImage());
    
    pre.executeUpdate();
        System.out.println("produit ajoutée !!");
    }

    @Override
    public boolean delete(int id) throws SQLException {

try{  
         ste = con.createStatement();
        String requesteDelete=" DELETE FROM produit where id ='"+ id +"'" ;
        ste.executeUpdate(requesteDelete);
        System.out.println("produit supprimer");
      } catch(Exception ex){
          System.err.println("ex");
      
      }

        return true ;        
    }

    @Override
    public boolean update(produit p) throws SQLException {
        
        String sql = "UPDATE produit SET nom=?, prix=?, desc=?, image=?, WHERE id=?";
 
PreparedStatement statement = con.prepareStatement(sql);
statement.setString(1, p.getNom());
statement.setDouble(2, p.getPrix());
statement.setString(3, p.getDesc());
statement.setString(4, p.getImage());


 
//int rowsUpdated = statement.executeUpdate();
//if (rowsUpdated > 0) {
    System.out.println("Modification terminé!");
//}
        return true;
    }
    

    @Override
    public List readAll() throws SQLException {
List<produit> arr=new ArrayList<>();
    ste=con.createStatement();
   
    ResultSet rs=ste.executeQuery("select * from produit");
     while (rs.next()) {                
               int id=rs.getInt(1);
               String nom=rs.getString("nom");
               double prix=rs.getDouble("prix");
               String desc=rs.getString(4);
                String image=rs.getString(5);
               
               produit p =new produit(id,nom,prix,desc,image);
     arr.add(p);
     }
    return arr;        
    }   
            

    @Override
    public List<produit> trier() throws SQLException {
        
         
             List<produit> arr=new ArrayList<>();
        ste = con.createStatement();
         String sql="select * from produit order by prix desc";
          ResultSet rs=ste.executeQuery(sql);
           while (rs.next()) {                
               int id=rs.getInt(1);
               String nom=rs.getString(2);
               double prix=rs.getDouble(3);
               String desc=rs.getString(4);
                String image=rs.getString(5);
               
               produit p=new produit(id, nom, prix, desc,image);
     arr.add(p);
     }
    return arr;        
    }


}
