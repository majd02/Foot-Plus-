/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myap.gui;

import com.codename1.components.FloatingHint;
import com.codename1.components.InfiniteProgress;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myap.Services.LivreurService;
import com.mycompany.myap.entities.Livreur;


/**
 *
 * @author asus
 */
public class ModifierLivreur extends Form{
    Form current;
    public ModifierLivreur(Livreur livreur){
         setTitle("Modifier le livreur");
       
        TextField tfid = new TextField(livreur.getIdLivreur());
        TextField tfnom = new TextField(livreur.getNomLivreur(),"Nom");
        TextField tfprenom = new TextField(livreur.getPrenomLivreur(),"Prenom");
        TextField tfadresse = new TextField(livreur.getAdresseLivreur(),"Adresse");
        TextField tfemail = new TextField(livreur.getEmail(),"Email");
        TextField tfnumtelphone = new TextField(String.valueOf(livreur.getNumTelephoneLivreur()),"numero telephone");
        
        Button btnU = new Button("Update");
        btnU.setUIID("Button");
     btnU.addPointerPressedListener(l->{
         
         livreur.setNomLivreur(tfnom.getText().toString());
         livreur.setPrenomLivreur(tfprenom.getText().toString());
         livreur.setAdresseLivreur(tfadresse.getText().toString());
         livreur.setEmail(tfemail.getText().toString());
         livreur.setNumTelephoneLivreur((int)Integer.parseInt(tfnumtelphone.getText().toString()));
     
     if(LivreurService.getInstance().modifierLivreur(livreur)){
         Dialog.show("Succes", "Livreur Modifier", new Command("OK"));
         new HomeForm().show();
     }
     
     });
       
        Label l1= new Label();
        Label l2= new Label();
        Label l3= new Label();
        Label l4= new Label();
        Label l5= new Label();
        
        Container content = BoxLayout.encloseY(
                l1,l2,l3,l4,l5,
                
                new FloatingHint(tfnom),
                new FloatingHint(tfprenom),
                new FloatingHint(tfadresse),
                new FloatingHint(tfemail),
                new FloatingHint(tfnumtelphone),
                btnU
        );
                
        
        
        
        add(content);
        show();
        
       
       
    }
    }