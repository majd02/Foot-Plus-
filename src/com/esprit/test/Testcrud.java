
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.test;

import com.esprit.models.Feedback;
import com.esprit.models.Reclamation;
import com.esprit.service.ServiceFeedback;
import com.esprit.service.ServiceReclamation;
import com.esprit.util.DataSource;
import java.util.Scanner;


/**
 *
 * @author USER
 */
public class Testcrud {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
         Scanner rs = new Scanner(System.in);
       ServiceReclamation r = new ServiceReclamation();
       
       ServiceFeedback o = new ServiceFeedback();
       Reclamation reclamation = new Reclamation("prod","bbal","ggg","dddd","bon","ff");
       /*Reclamation reclamation = new Reclamation(231,"produit","ff","aaaaaaa","ffffffff","bon etat","ff","fffff");*/
       /*r.ajouter(reclamation);*/
       o.afficherdes().forEach(System.out::println);
              r.afficherdes().forEach(System.out::println);
            int  p=o.getNbrFeedback();
            System.out.println(p);
              o.getNbrFeedback();
        Feedback feedback = new Feedback("cd",55,"ff");
      System.out.println("Command Options: ");
            System.out.println("1: ajouter une reclamation");
            System.out.println("2: ajouter un feedback");
            System.out.println("3: afficher les reclamation");
            System.out.println("4: afficher les feedback");
            System.out.println("5: supprimer reclamation");
            System.out.println("6: supprimer feedback");
            System.out.println("7: modifier reclamation");
            System.out.println("8: modifier feedback");
            System.out.println("9: afficher trieé des reclamation");
            System.out.println("10: afficher trieé des feedback");
            System.out.println("11: rechercher  des reclamation");
            System.out.println("12: rechercher des reclamation");
            System.out.println("0: menu");
        int x = rs.nextInt();
        
        do{
            
   switch (x) {
            case 5:  
                int supp;
                 System.out.print( "Veuillez saisir un sujet entier : " );
             supp = rs.nextInt();
            
       r.supprimerr(supp);
                break;
            case 3: 
                r.afficher().forEach(System.out::println);
                     break;
                     
            case 1:  
                System.out.print( "Veuillez saisir un sujet entier : " );
            String rc = rs.nextLine();
            String ty = rs.nextLine();
             String objet = rs.nextLine();
              String sujet = rs.nextLine(); 
              String desc = rs.nextLine();
               String etat = rs.nextLine();
               String user = rs.nextLine();
               Reclamation r1 = new Reclamation(ty,objet,sujet,desc,etat,user);
           r.ajouter(r1);
                     break;
            case 2: 
                 System.out.print( "description: " );
            String qr = rs.nextLine();
            String qs = rs.nextLine();
             System.out.print( "rate sur 5: " );
             int sd = rs.nextInt();
              System.out.print( "user: " );
              int dt = rs.nextInt();
              String df = rs.nextLine();
             
               Feedback o1 = new Feedback(qs,sd,df);
           o.ajouter(o1);
                     break;
            case 8:
                int fm = rs.nextInt();
               
                int dfs = rs.nextInt();
               
                   String bj= rs.nextLine();     
                feedback.setRate(dfs);
                   
                feedback.setUser(bj);
                feedback.setId(fm);
                o.modifier(feedback);
                break;
                
            case 7:         
                
                System.out.print( "Veuillez saisir la reclamation avec l id : " );
                 String rd = rs.nextLine();
                 int qq = rs.nextInt();
                System.out.print( "Veuillez saisir le sujet : " );
                            String gtf = rs.nextLine();

             
               String ghb = rs.nextLine();
               System.out.print( "Veuillez saisir le type : " );
                            String rhf = rs.nextLine();

             
               String ss = rs.nextLine();
                System.out.print( "Veuillez saisir l objet  : " );
               String ccv = rs.nextLine();
                System.out.print( "Veuillez saisir la description: " );
               String fff = rs.nextLine();
        reclamation.setType(ss);
        reclamation.setIdo(ccv);
        reclamation.setSujet(ghb);
        reclamation.setDescription(fff);
        reclamation.setId(qq);
        r.modifier(reclamation);
                     break;
                     
            case 6: 
                int fvds;
                    System.out.print( "saisir le feedback a effacer : " );
             fvds = rs.nextInt();
            
       o.supprimerf(fvds);
                break;
        case 4: 
              o.afficher().forEach(System.out::println);
              
            break;
        case 9: 
              r.afficherdes().forEach(System.out::println);
              
            break;
            case 10: 
              o.afficherdes().forEach(System.out::println);
              
            break;
            
                     case 11: 
                         System.out.print( "saisir la reclamation  a rechercher : " );
            int jj = rs.nextInt();
       r.recherche(jj).forEach(System.out::println);;
                
                break;
                 case 12: 
                      System.out.print( "saisir le feedback a rechercher : " );
            int jjbk = rs.nextInt();    
       o.recherche(jjbk).forEach(System.out::println);;
                
                break;
        
        case 0 :  
            System.out.println("Command Options: ");
            System.out.println("1: ajouter une reclamation");
            System.out.println("2: ajouter un feedback");
            System.out.println("3: afficher les reclamation");
            System.out.println("4: afficher les feedback");
            System.out.println("5: supprimer reclamation");
            System.out.println("6: supprimer feedback");
            System.out.println("7: modifier reclamation");
            System.out.println("8: modifier feedback");
            System.out.println("9: afficher trieé des reclamation");
            System.out.println("10: afficher trieé des feedback");
            System.out.println("11: rechercher  des reclamation");
            System.out.println("12: rechercher des feedback");
            
            System.out.println("14: quitter");
            
            System.out.println("0: menu");
            break;
        } 
   x = rs.nextInt();
        
} while(x<13);
    } 
        
       
    
    
}
  /*ServiceReclamation r = new ServiceReclamation();
       
       ServiceFeedback o = new ServiceFeedback();
       Reclamation reclamation = new Reclamation(231,"produit","ff","aaaaaaa","ffffffff","bon etat","ff","fffff");
       r.ajouter(reclamation);
       reclamation.setSujet("majedsssss");
       reclamation.setDescription("aaaaa");
      r.modifier(reclamation);
       r.afficher().forEach(System.out::println);
       
       o.afficher().forEach(System.out::println);
       
       r.afficherdes().forEach(System.out::println);
        DataSource ds1 = new DataSource(); 
*/
