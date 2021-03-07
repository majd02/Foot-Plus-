/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myapp;

import com.Suptech.Entite.produit;
import com.Suptech.Entite.promotion;
import com.Suptech.Service.Serviceproduit;
import com.Suptech.Service.Servicepromotion;
import java.sql.SQLException;


/**
 */
public class MyApp {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        System.out.print("hello world");
        
         Serviceproduit ser = new Serviceproduit();
          Servicepromotion serv = new Servicepromotion();
        
            /* ajouter produuit */    
      
        
      produit p =new produit("badis",500,"bhimmm","897997");
       ser.ajouter(p);
        
       promotion pr =new promotion("badis", "0212-2021","02-12-2021" ,"897997",500);
        serv.ajouter(pr);
        
            /* fin ajout*/
      
               /*afficher liste des produit*/
      
      //List<produit> pp =  new ArrayList<>();
    // pp=  ser.readAll();
     // System.out.print(pp);
    
              /*fin liste produit*/
              
      /*delete produit*/
      
     // ser.delete(11);
     

           


      /*fin delete produit*/
      
      /*update*/
     // produit p5 = new produit(2,"hhhh",88888,"hlj");
      
      //ser.update(p5);
      
      /*fin update*/
      // pp= ser.trier();
     
     //System.out.print(pp);
      /*trie*/
    
      
    
      
/*fin trie*/
    }
    
}
