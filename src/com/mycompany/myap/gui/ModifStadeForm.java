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
import com.mycompany.myap.Services.StadeService;
import com.mycompany.myap.entities.Stade;

/**
 *
 * @author asus
 */
public class ModifStadeForm extends Form{
    Form current;
    public ModifStadeForm(Stade stade){
         setTitle("Modifier le stade stade");
       
        TextField tfid = new TextField(stade.getId_stade());
        TextField tfnom = new TextField(stade.getNom(),"Stade Name");
        
        TextField tfadresse = new TextField(stade.getAdresse(),"Adresse");
        
        TextField tfprixph = new TextField(String.valueOf(stade.getPrixph()),"Prix");
        TextField tfcontact = new TextField(stade.getContact(),"Contact");
         
        TextField tfImage = new TextField(stade.getImage(),"Image");
        
        Button btnU = new Button("Update");
        btnU.setUIID("Button");
     btnU.addPointerPressedListener(l->{
         
         stade.setNom(tfnom.getText().toString());
         stade.setAdresse(tfadresse.getText().toString());
         stade.setPrixph((int)Integer.parseInt(tfprixph.getText().toString()));
         stade.setContact(tfcontact.getText().toString());
         stade.setImage(tfImage.getText().toString());
     
     
     if(StadeService.getInstance().modifierStade(stade)){
         Dialog.show("Succes", "Stade Updated", new Command("OK"));
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
                new FloatingHint(tfadresse),
                new FloatingHint(tfprixph),
                new FloatingHint(tfcontact),
                new FloatingHint(tfImage),
                btnU
        );
                
        
        
        
        add(content);
        show();
        
       
       
    }
    }
    

