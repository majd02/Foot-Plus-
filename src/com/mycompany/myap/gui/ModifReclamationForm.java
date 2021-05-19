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
import com.mycompany.myap.Services.ServiceReclamation;
import com.mycompany.myap.entities.Reclamation;


/**
 *
 * @author asus
 */
public class ModifReclamationForm extends Form{
    Form current;
    public ModifReclamationForm(Reclamation reclamation){
         setTitle("Modifier le stade stade");
       
        TextField tfid = new TextField(reclamation.getId());
        TextField tftype = new TextField(reclamation.getType(),"type");
        
        TextField tfido = new TextField(reclamation.getIdo(),"objet");
        
        TextField tfsujet = new TextField(reclamation.getSujet());
        TextField tfdescription = new TextField(reclamation.getDescription(),"description");
         
        TextField tfuser = new TextField(reclamation.getUser(),"user");
        
        Button btnU = new Button("Update");
        btnU.setUIID("Button");
     btnU.addPointerPressedListener(l->{
         
         reclamation.setType(tftype.getText().toString());
         reclamation.setIdo(tfido.getText().toString());
       reclamation.setSujet(tfsujet.getText().toString());
         reclamation.setDescription(tfdescription.getText().toString());
         reclamation.setUser(tfuser.getText().toString());
     
     
     if(ServiceReclamation.getInstance().modifierReclamation(reclamation)){
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
                
                new FloatingHint(tftype),
                new FloatingHint(tfido),
                new FloatingHint(tfsujet),
                new FloatingHint(tfdescription),
                new FloatingHint(tfuser),
                btnU
        );
                
        
        
        
        add(content);
        show();
        
       
       
    }
    }