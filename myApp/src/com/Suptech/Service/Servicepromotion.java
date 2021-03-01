/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Suptech.Service;

import com.Suptech.Entite.promotion;
import com.Suptech.IService.IServicepromotion;
import com.Suptech.Utils.DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author moham
 */
public class  Servicepromotion implements IServicepromotion <promotion>  {
    private Connection con;
    private Statement ste;

    public Servicepromotion() {
        con = DataBase.getInstance().getConnection();

    
    
}
        @Override
    
     public void ajouter(promotion p) throws SQLException {
        
        PreparedStatement pre=con.prepareStatement("INSERT INTO `promotion` (`nom_promo`,`debut_date`,`fin_date`,`desc_promo`,`pourcentage` ) VALUES ( ?, ?, ?, ?, ?)");
    pre.setString(1, p.getNom_promo()); 
    pre.setString(2, p.getDebut_date());
    pre.setString(3, p.getFin_date());
    pre.setString(4, p.getDesc_promo());
    pre.setDouble(5, p.getPourcentage());
    
     pre.executeUpdate();

    
    
        System.out.println("promotion ajoutée !!");
    }

    @Override
    public boolean delete(int id_promo) throws SQLException {
        try{  
         ste = con.createStatement();
        String requesteDelete=" DELETE FROM promotion where id ='"+ id_promo +"'" ;
        ste.executeUpdate(requesteDelete);
        System.out.println("promotion supprimer");
      } catch(Exception ex){
          System.err.println("ex");
      
      }

        return true ;        
    }

    @Override
    public boolean update(promotion p) throws SQLException {
        String sql = "UPDATE promotion SET nom_promo=?, debut_date=?, fin_date=?, desc_promo=?, pourcentage=? WHERE id=?";
 
PreparedStatement statement = con.prepareStatement(sql);
statement.setString(1, p.getNom_promo());
statement.setString(2, p.getDebut_date());
statement.setString(3, p.getFin_date());
statement.setString(4, p.getDesc_promo());
statement.setDouble(4, p.getPourcentage());

//int rowsUpdated = statement.executeUpdate();
//if (rowsUpdated > 0) {
    System.out.println("Modification terminé!");
//}
        return true;
    }

    @Override
    public List<promotion> readAll() throws SQLException {
       List<promotion> arry=new ArrayList<>();
    ste=con.createStatement();
   
    ResultSet  rs=ste.executeQuery("select * from promotion");
     while (rs.next()) {                
               int id_promo=rs.getInt(1);
               String nom_promo=rs.getString(2);
               String debut_date=rs.getString(3);
               String fin_date=rs.getString(4);
                String desc_promo=rs.getString(5);
                double pourcentage =rs.getDouble(6);

                
                
               
               promotion p =new promotion(id_promo,nom_promo,debut_date,fin_date,desc_promo,pourcentage);
     arry.add(p);
     }
    return arry;        
    }
    
    }
