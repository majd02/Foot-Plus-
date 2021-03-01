/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package membres_abonnements;

import Entities.Abonnement;
import Entities.Membre;
import Services.AbonnementServices;
import Services.MembreServices;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;

/**
 *
 * @author Youssef
 */
public class Membres_Abonnements {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException , SQLException {
        /*****Membres*************/
        
                // TODO code application logic here
        //Membre u1 = new Membre(5,"aklim","konan","youssefouali20@gmail.com",29265485,"youssef02","lien","homme",Date.valueOf("2019-02-01"));    
        
       // MembreServices su = new MembreServices();
     
        //su.AddMembre(u1);
        //su.ReadMembre();
        //su.UpdateMembre(3, u1);
        //su.DeleteMembre(u1);
        //su.SearchMembre("youssef", "ouali");
        //su.SortListeMembresParUser_name();
        
        /************Abonnements************/
        Abonnement a;   
        a = new Abonnement(2,8,14,Date.valueOf("2019-02-01"),Date.valueOf("2020-02-01"),10,"lien");
        AbonnementServices ab = new AbonnementServices();
        
       //ab.AddAbonnement(a);
       //ab.ReadAbonnement();
       //ab.UpdateAbonnement(1, a);
       //ab.DeleteAbonnement(a);
       //ab.SearchAbonnement(1);
       //ab.SortListeAbonnementsParPrix();
        
        
    }
    
}
