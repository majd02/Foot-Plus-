/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myapp;

import com.Suptech.Entite.produit;
import com.Suptech.Service.Serviceproduit;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 */
public class MyApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        System.out.print("hello world");
        
         Serviceproduit ser = new Serviceproduit();
        
            /* ajouter produuit */    
      
        
      // produit p =new produit("ballon",2500,"gjhhlh","80000");
       // ser.ajouter(p);
        
            /* fin ajout*/
      
               /*afficher liste des produit*/
      
     // List<produit> pp =  new ArrayList<>();
    // pp=  ser.readAll();
    //  System.out.print(pp);
    
              /*fin liste produit*/
              
      /*delete produit*/
      
      //ser.delete(3);
           


      /*fin delete produit*/
      
      /*update*/
     // produit p5 = new produit(2,"hhhh",88888,"hlj");
      
      //ser.update(p5);
      
      /*fin update*/
       //pp= ser.trier();
     
     //System.out.print(pp);
      /*trie*/
    
      
    
      
/*fin trie*/
    }
    
}
