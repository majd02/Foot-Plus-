/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myap.gui;

import com.codename1.capture.Capture;
import com.codename1.components.InfiniteProgress;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myap.Services.StadeService;
import com.mycompany.myap.entities.Stade;
import java.io.IOException;

/**
 *
 * @author asus
 */
public class AddStadeForm extends Form {

    public AddStadeForm(Form previous) {
        setTitle("Ajouter un nouveau stade");
       
        
        TextField tfnom = new TextField("","Stade Name");
        
        TextField tfadresse = new TextField("","Adresse");
        
        TextField tfprixph = new TextField("","Prix");
        TextField tfcontact = new TextField("","Contact");
         
        TextField tfImage = new TextField("","Image");
        
        Button btnValider = new Button("Add");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                if ((tfnom.getText().length()==0)||(tfadresse.getText().length()==0)||(tfprixph.getText().length()==0)||(tfcontact.getText().length()==0))
                { Dialog.show("Alert", "Please fill all the fields", new Command("OK"));}
                else{
            InfiniteProgress ip = new InfiniteProgress();
            final Dialog idialog = ip.showInfiniteBlocking();
            Stade s = new Stade(String.valueOf(tfnom.getText()
                    .toString()).toString(),String.valueOf(tfadresse.getText().toString()).toString()
            ,Integer.parseInt(tfprixph.getText().toString()),
            String.valueOf(tfcontact.getText()
                    .toString()).toString(),String.valueOf(tfImage.getText()
                    .toString()).toString());
                    System.err.println("data stade=="+s);
                    StadeService.getInstance().addStade(s);
                    idialog.dispose();
                    Dialog.show("Succes", "Stade Added", new Command("OK"));
                    
                   
            }
                 
        }
            
        });
        Button anuuler = new Button("Cancel");
                 anuuler.addActionListener(new ActionListener(){
        @Override
       public void actionPerformed(ActionEvent evt) {
             
            new HomeForm().show();
       }
       });
        addAll(tfnom,tfadresse,tfprixph,tfcontact,tfImage,btnValider,anuuler);
        
    }
    
}
