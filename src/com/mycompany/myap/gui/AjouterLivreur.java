/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myap.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myap.Services.LivreurService;
import com.mycompany.myap.entities.Commande;
import com.mycompany.myap.entities.Livreur;





/**
 *
 * @author asus
 */
public class AjouterLivreur extends Form {

    public AjouterLivreur(Form previous) {
        setTitle("Ajouter un nouveau Livreur");
        
        
        
        TextField tfnomlivreur = new TextField("","Nom");
        TextField tfprenomlivreur = new TextField("","prenom");
        TextField tfemaillivreur = new TextField("","email");
        TextField tfadresselivreur = new TextField("","adresse");
        TextField tfnumTelephonelivreur = new TextField("","numtelephone");
   
        
        Button btnValider = new Button("Ajouter");
        Button retour = new Button("Retour");
                 retour.addActionListener(new ActionListener(){
       
         @Override
       public void actionPerformed(ActionEvent evt) {
             
            new HomeForm().show();
       }
       });
        btnValider.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                
                if ((tfnomlivreur.getText().length()==0)||(tfprenomlivreur.getText().length()==0)||(tfemaillivreur.getText().length()==0)||(tfadresselivreur.getText().length()==0)||(tfnumTelephonelivreur.getText().length()==0))
                { Dialog.show("Alert", "Please fill all the fields", new Command("OK"));}
                else{
            InfiniteProgress ip = new InfiniteProgress();
            final Dialog idialog = ip.showInfiniteBlocking();
            Livreur l = new Livreur(
                    String.valueOf(tfnomlivreur.getText()),
                    String.valueOf(tfprenomlivreur.getText()),
                    String.valueOf(tfemaillivreur.getText()),
                    String.valueOf(tfadresselivreur.getText()),
                    Integer.parseInt(tfnumTelephonelivreur.getText())
                  
                    );
                   
                    
                    System.err.println("data livreur=="+l);
                    LivreurService.getInstance().AjouterLivreur(l);
                    idialog.dispose();
                    Dialog.show("Succes", "Livreur Added", new Command("OK"));
                 
                  
         
            }
        }
        });
        addAll(tfnomlivreur,tfprenomlivreur,tfemaillivreur,tfadresselivreur,tfnumTelephonelivreur,btnValider,retour);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->previous.showBack());
    }

   
    
}