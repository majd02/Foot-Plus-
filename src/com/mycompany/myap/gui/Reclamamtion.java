/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myap.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myap.Services.ServiceReclamation;
import com.mycompany.myap.entities.Reclamation;


import java.io.IOException;
import java.util.ArrayList; 

/**
 *
 * @author bhk
 */


public class Reclamamtion extends Form{
Form current;
     public  Reclamamtion(Form previous)  {

      
       setTitle("List reclamations");
         
   
 
        
      
        ServiceReclamation es = new ServiceReclamation();
        ArrayList<Reclamation> list = es.getAllCours();

        {
           
            for (Reclamation r : list) {

         
 
        Button btnAddTask = new Button("Ajouter un patient!");
                Container c3 = new Container(BoxLayout.y());
                SpanLabel typereclamation= new SpanLabel("Type Reclamation:" + r.getType());
                
                SpanLabel id= new SpanLabel("Type:" + r.getType());
                    
                SpanLabel ids= new SpanLabel("Type:" + r.getType());
                

SpanLabel typereclamations= new SpanLabel("description:" + r.getDescription());
SpanLabel numerocl= new SpanLabel("Objet:" + r.getIdo());
SpanLabel etat= new SpanLabel("Objet:" + r.getEtat());


        SpanLabel nom= new SpanLabel("sujet:" + r.getSujet());        
               
                  Button Acc = new Button("Accept");
                  Button Rej = new Button("Reject");
                  
                  Button Don = new Button("Done");
                  
                  Button ajout = new Button("Patient");
        
        TextField tfemail = new TextField("","email");  
//        supp.addActionListener(new ActionListener(){
//        @Override
//        public void actionPerformed(ActionEvent evt) {
//            ServiceAbonnement.getInstance().deleteAbonnement(ab);
//            Dialog.show("Success", "Memory Deleted Successfully.", "OK", "Cancel");
//        }
//        });
 
Acc.addActionListener(new ActionListener(){
        @Override
       public void actionPerformed(ActionEvent evt) {
            ServiceReclamation.getInstance().accepter(r.getId()); 
            Dialog.show("Success", "Reclamation Accepted Successfully.", "OK", "Cancel");
      new Reclamamtion(current).show();
          
       }

            
       });

Rej.addActionListener(new ActionListener(){
        @Override
       public void actionPerformed(ActionEvent evt) {
            ServiceReclamation.getInstance().refuser(r.getId()); 
            Dialog.show("Success", "Reclamation Rejected Successfully.", "OK", "Cancel");
            new Reclamamtion(current).show();
       }

            
       }); 
Rej.addActionListener(new ActionListener(){
        @Override
       public void actionPerformed(ActionEvent evt) {
            ServiceReclamation.getInstance().mailc(r.getId()); 
            Dialog.show("Success", "un mail a ete envoye.", "OK", "Cancel");
            new Reclamamtion(current).show();
       }

            
       });
Acc.addActionListener(new ActionListener(){
        @Override
       public void actionPerformed(ActionEvent evt) {
            ServiceReclamation.getInstance().mail(r.getId()); 
            Dialog.show("Success", "un mail a ete envoye .", "OK", "Cancel");
            new Reclamamtion(current).show();
       }

            
       });
Don.addActionListener(new ActionListener(){
        @Override
       public void actionPerformed(ActionEvent evt) {
            ServiceReclamation.getInstance().mailcc(r.getId()); 
            Dialog.show("Success", "un mail a ete envoye .", "OK", "Cancel");
            new Reclamamtion(current).show();
       }

            
       });
Don.addActionListener(new ActionListener(){
        @Override
       public void actionPerformed(ActionEvent evt) {
            ServiceReclamation.getInstance().done(r.getId()); 
            Dialog.show("Success", "Reclamation Completed Successfully.", "OK", "Cancel");
            new Reclamamtion(current).show();
       }

            
       });
  c3.add(ids);       
c3.add(numerocl);
      
          c3.add(nom);
      
          c3.add(typereclamations);
           c3.add(etat);
           
          
         
           c3.add(Acc);
            c3.add(Rej);
            c3.add(Don);
          
          
          
                      
           
                        
           System.out.println("");
              
  add(c3);
              
            
          getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> previous.showBack()); // Revenir vers l'interface précédente
                
            }
          
        }
     
    }

 
}
